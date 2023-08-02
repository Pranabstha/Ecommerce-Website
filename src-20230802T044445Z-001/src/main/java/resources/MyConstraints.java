package resources;

import java.io.File;

public class MyConstraints {
	// Database Configuration
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3309/GurkhaClothing";
	public static final String DB_USER_NAME = "root"; 		
	public static final String DB_USER_PASSWORD= "";
	
	
	public static final String IMAGE_DIR = "xampp\\tomcat\\webapps\\userImages\\";
	public static final String IMAGE_SAVE_PATH = "C:" + File.separator + IMAGE_DIR;

	
	// End of Database Configuration
	
	//admin credentails
	public static final String ADMIN = "admin";
	
	// Start of SELECT Querry
	
	public static final String GET_ALL_INFO = "SELECT * FROM User";
	public static final String GET_ALL_INFO_BY_ID = "SELECT * FROM User WHERE userID = ?";
	public static final String VERIFY_LOGIN_INFO = "SELECT username, password FROM User WHERE userName = ?";
	public static final String USER_ROLE = "SELECT role FROM User WHERE userName = ?";
	
	// End of SELECT Querry
	
	//Start of Insert Querry
	public static final String USER_SIGNUP = "INSERT INTO User"
			+ "(firstName, lastName, userName, password, role, email, dob, phoneNumber, gender, userImageURL)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
	// End of Insert Query
	
	// Start of Update Querry
	public static final String UPDATE_USER_INFO = "UPDATE User SET firstName=?, "
			+ "lastName=?, userName=?, role=?, email=?, dob=?, phoneNumber=?, gender=? WHERE userName=?";
	// End of Update Querry
	
	//Start of Delete Querry
	public static final String DELETE_USER = "DELETE FROM User WHERE userName=?";
	//End of of Delete Querry
}
