package controller.servelets.deregister;

import java.io.IOException;
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
 * Servlet implementation class DeregisterUser
 */
@WebServlet("/DeregisterUser")
public class DeregisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeregisterUser() {
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
		String userName = null;
		String sessionId = null;
		Cookie[] cookieList = request.getCookies();
		if(cookieList != null){
			for(Cookie cookie : cookieList){
				if(cookie.getName().equals("user")) userName = cookie.getValue();
				if(cookie.getName().equals("JSESSIONID")) sessionId = cookie.getValue();
			}
		}
		System.out.println(userName);
		
		DBConnection dbConnection = new DBConnection();
		try {
			boolean deregisterStatus = dbConnection.deregisterUser(MyConstraints.DELETE_USER, userName);
			System.out.println(deregisterStatus);
			if(deregisterStatus) {
				HttpSession session = request.getSession(false);
				if(session != null) {
					session.invalidate();
				}
				response.sendRedirect(request.getContextPath()+ "/index.jsp");

			}
		}catch(Exception e) {
			System.out.println("error in delette user");
		}
	}

}
