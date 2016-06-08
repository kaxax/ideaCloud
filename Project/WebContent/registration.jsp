<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="registration.css">
<title>register</title>
</head>
<body>

	<div id = "logo">
		<img src="logo.png" id = "image-logo">
	</div>

	<form action="http://localhost:8080/IdeaCloud/hereWeGo" id = "form">
	
		<div id = "name-div">
			<input type="text" id="name" placeholder="name" name = "name">
		</div>
		
		<div id = "surname-div">
			<input type="text" id="surname" placeholder="surname" name = "surname">
		</div>
		
		<div id = "username-div">
			<input type="text" id="username" placeholder="username" name = "username">
		</div>
		
		<div id = "email-div">
			<input type="text" id="mail" placeholder="e-mail" name = "email">
		</div>
		
		<div id = "password-div">
			<input type="password" id="password" placeholder="password" name = "password">
		</div>
		
		<div id = "password-again-div">
			<input type="password" id="password-again" placeholder="repeate password" name = "again">
		</div>
		
		<div id = "submit-div">
			<input id="button" type=submit value="here we go!" class="button" onclick="myFunction()">
		</div>
		
	</form>
	
	<div id = "post-div">
	
		<form action="homepage.jsp">
			<input type=submit value="see post" class="button" id="register">
		</form>
	
	</div>


</body>
</html>
