package controller.dbconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import resources.MyConstraints;


public class DBConnection {
	private static Connection conn = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3309/gurkhaclothing";
		String user = "root";
		String pass = "";
		conn = DriverManager.getConnection(url, user, pass);
		return conn;
	}
	
	public int userSignup(String Querry, User newUser) throws ClassNotFoundException, SQLException  {
		Connection dbConnection = getConnection();
		if(dbConnection != null) {
			
			PreparedStatement statement = dbConnection.prepareStatement(Querry);
			statement.setString(1, newUser.getFirstName());
			statement.setString(2, newUser.getLastName());
			statement.setString(3, newUser.getUserName());
			statement.setString(4, newUser.getPassword());
			statement.setString(5, newUser.getRole());
			statement.setString(6, newUser.getEmail());
			statement.setString(7, newUser.getDob());
			statement.setString(8, newUser.getPhoneNumber());
			statement.setString(9, newUser.getGender());
			statement.setString(10, newUser.getImageURL());
			
			int result = statement.executeUpdate();
			if(result>=0) return 1;
			else return 0;
			
			
		}else {
			return 0;
		}	
	}
	// checking weather the user is registered or not preventing duplication
	public boolean verifyRegisteredUser(String username) throws ClassNotFoundException, SQLException {
		Connection dbConnection = getConnection();
		if(dbConnection != null) {
			PreparedStatement statement = dbConnection.prepareStatement(MyConstraints.VERIFY_LOGIN_INFO);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				String userFromDb = result.getString("userName");
				String passFromDb = result.getString("password");
				if(userFromDb.equals(username)) {
					return true;
				}else return false;
			}else return false;
		}else return false;
	}
	
	// verifying login credentials
	public boolean verifyLoginCredentials(String querry, String userName, String password) throws ClassNotFoundException, SQLException {
		Connection dbConnection = getConnection();
		
		if(dbConnection != null) {
			PreparedStatement statement = dbConnection.prepareStatement(querry);
			statement.setString(1, userName);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				String userFromDb = result.getString("userName");
				String passFromDb = result.getString("password");
				if(userFromDb.equals(userName) && passFromDb.equals(password)) {
					return true;
				}else return false;
			}else return false;
		}else return false;	
	}
	
	//verifying admin credentials
//	public int verifyAdmin (String userName) throws ClassNotFoundException, SQLException{
//		Connection dbConnection = getConnection();
//		
//		if(dbConnection != null) {
//			PreparedStatement statement;
//			try {
//				statement = dbConnection.prepareStatement(MyConstraints.USER_ROLE);
//				statement.setString(1, userName);
//				ResultSet result = statement.executeQuery();
//				if(result.next()){
//					String userRole = result.getString("role");
//					System.out.println("test role");
//					System.out.println(userRole);
//					System.out.println(userRole.toLowerCase());
//					System.out.println(MyConstraints.ADMIN);
//					if(userRole != "admin") return 1;
//					else {
//						System.out.println("error a");
//						return 0;
//					}
//				}else {System.out.println("error b"); return 0;}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return -1;
//			}
//			
//		}else return -1;
//	}
	
	public int verifyAdmin(String username) throws ClassNotFoundException, SQLException {
		Connection dbConnection = getConnection();
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(MyConstraints.USER_ROLE);
				statement.setString(1, username);
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					String userRole = result.getString("role");
					if(userRole == "admin") {
						return 1;
					}else {
						return 0;
					}
				}else {
					return -1;
				}
			}catch(Exception e) {
				return -2;
			}
		}else {
			return -3;
		}
	}
	
	//delete functionn for user
	public boolean deregisterUser(String querry, String username) throws ClassNotFoundException, SQLException {
		Connection dbConnection = getConnection();
		if(dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(querry);
				statement.setString(1, username);
				int result = statement.executeUpdate();
				if(result >= 0) {
					return true;
				}else {
					return false;
				}
			}catch (Exception e){
				return false;
			}
		}else {
			return false;
		}
	}
	
}

//firstName, lastName, userName, password,
//role, email, dob, phoneNumber, gender, userImageURL
