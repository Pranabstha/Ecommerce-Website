<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% 
		String user = (String)session.getAttribute("user");
		String userName = null;
		String sessionId = null;
		Cookie[] cookieList = request.getCookies();
		if(cookieList != null){
			for(Cookie cookie : cookieList){
				if(cookie.getName().equals("user")) userName = cookie.getValue();
				if(cookie.getName().equals("JSESSIONID")) sessionId = cookie.getValue();
			}
		}
	%>
	
	<h3>HI <%=userName %>, Login Sucessful. Your Session <%=sessionId %></h3>
	User=<%= user %>
	<br>
	<form action="../LogoutServlet" method="post">
		<input type="submit" value="Logout">
	</form>
	
	<form action="../DeregisterUser" method="post">
		<input type="submit" value="Delete">
	</form>
	<button name="Update"><a href="views/updateUser.jsp"></a></button>
	
</body>
</html>