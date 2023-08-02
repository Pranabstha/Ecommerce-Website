package model;

import java.io.File;
import java.sql.Date;

import javax.servlet.http.Part;

import resources.MyConstraints;

//creating the user model with following properties and methods
public class User {
	
	//properties of users
	private int userID;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private String dob;
	private String userImageURL; 
	private String phoneNumber;
	private String gender;
	private String role;
	
	//User Constructor
	public User(String firstName, String lastName, String userName, String password, String role, String email,
		String dob, String phoneNumber, String gender, Part partImageURL  ){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.email = email;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.userImageURL = getImageUrl(partImageURL);
	}
	

	//getters and setters 
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	//Image getter and setter 
	
	public String getImageURL() {
		return userImageURL;
	}
	public void setImageURL(Part imageURL) {
		this.userImageURL = getImageUrl(imageURL);
	}
	
	// end of image getter setter
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	private String getImageUrl(Part part) {
		String savePath = MyConstraints.IMAGE_SAVE_PATH;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if(imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart= "download.png";
		}
		return imageUrlFromPart;
	}
	
}
