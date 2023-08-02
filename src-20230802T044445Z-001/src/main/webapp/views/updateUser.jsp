<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/SignupServlet" method="post" enctype="multipart/form-data">
		FirstName: <input type="text" name="fname" placeholder="aaa"><br><br>
		LastName: <input type="text" name="lname"><br><br>
		Email: <input type="email" name="email"><br><br>
		PhoneNumber: <input type="text" name="phone"><br><br>
		Gender: <input type="text" name="gender"><br><br>
		Date of Birth: <input type="date" name="dob"><br><br>
		Username: <input type="text" name="userName"><br><br>
		Password: <input type="password" name="password"><br><br>
		<input type="file" id="userImage" name="userImage">
		<input type="text" name="role" value="customer" hidden=""><br><br>
		<input type="submit" value="Signup">
	</form>
</body>
</html>