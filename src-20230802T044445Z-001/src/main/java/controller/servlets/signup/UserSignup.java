package controller.servlets.signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.dbconnection.DBConnection;
import model.User;
import resources.MyConstraints;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class UserSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		PrintWriter pw = response.getWriter();
//		pw.print(request.getParameter("role"));
//		
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phone");    
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String role = request.getParameter("role");
		Part imagePart = request.getPart("userImage");
		System.out.println(request.getParameter("role"));
		
		User newUser = new User(firstName, lastName, userName, password, role, email, dob, phoneNumber, gender, imagePart);
		
		System.out.println(newUser.getRole());
		
		String userImageLocation = MyConstraints.IMAGE_SAVE_PATH;
		String fileName = newUser.getImageURL();
		
		if(!fileName.isEmpty() && fileName != null)
    		imagePart.write(userImageLocation + fileName);
		
		DBConnection con = new DBConnection();
		int result = 0;
		try {
			result = con.userSignup(MyConstraints.USER_SIGNUP, newUser);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("test 1");
			e.printStackTrace();
		}
		if(result == 1) {
			request.setAttribute("registerMessage", "Successfully Registered");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}else if(result == -1) {
			request.setAttribute("registerMessage", "User Already Exists");
			request.getRequestDispatcher("/views/signup.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/views/signup.jsp").forward(request, response);
		}
		
	}
		

}

