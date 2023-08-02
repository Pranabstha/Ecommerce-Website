package controller.servlets.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dbconnection.DBConnection;
import resources.MyConstraints;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		
		DBConnection dbConnection = new DBConnection();
		
		try {
			boolean verifyUser = dbConnection.verifyLoginCredentials(MyConstraints.VERIFY_LOGIN_INFO, user, password);
			System.out.println(verifyUser);
			if(verifyUser) {
				PrintWriter pw = response.getWriter();
				pw.print(user);
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(30*60);
				
				Cookie userName = new Cookie("user", user);
				userName.setMaxAge(30*60);
				response.addCookie(userName);
				
				int verifyRole = dbConnection.verifyAdmin(user);
				System.out.println(verifyRole);
				if(verifyRole == 1) {
					response.sendRedirect(request.getContextPath()+"/views/admin.jsp");
				}else {
					response.sendRedirect(request.getContextPath()+"/views/home.jsp");
				}
					
				//response.sendRedirect(request.getContextPath()+"/views/home.jsp");
				
			}else {
				//set error message
				request.setAttribute("errorMessage", "Invalid username or password");
				//redirect to the login page
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
				dispatcher.include(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			}

}
