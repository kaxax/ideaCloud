<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="login.css">
	<title>login</title>
</head>
<body>
	<a href="http://fb.com">
		<img src="fb.png" width="5%" style="position: absolute; left: 48%; 
		top: 10%;">
	</a>

	



	
		<form action="logButton">
		<div id="inp" style="position: absolute; left: 40%; top: 30%;">
			<input  type = "text" placeholder="username" id="username">
		</div>
		<div style="position: absolute; left: 40%; top: 45%;">
			<input type = "password" placeholder="password" id="password">
		</div >
		<div style="position: absolute; left: 48%; top: 57%;">
			<input type=submit value = "log in" class = "button" 
			id = "login" >
		</div>
		</form>
	

	<div style="position: absolute; left: 47%; top: 75%;">
		<form action="registration.jsp">
			<input type=submit value = "Join Now" class = "button" 
			id = "register" >
		</form>
	</div>


</body>
</html>