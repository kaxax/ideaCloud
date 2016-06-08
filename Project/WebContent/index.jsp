<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="login.css">
<title>login</title>
</head>
<body>
	
	<form action="http://localhost:8080/IdeaCloud/getPosts" class = "form">
		
		<a href="http://fb.com"> <img src="fb.png" width="3%" id = "image-fb"></a>
		
		<div id = "logo">
			<img  src="logo.png" id = "image-logo">
		</div>
		
		<div id = share>
			<img  src="share.png" id = "image-share">
		</div>
		
		<div id = "help">
			<img  src="help.gif" id = "image-help">
		</div>
		
		<div id="username-div">
			<input type="text" placeholder="username" id="username" name = "username">
		</div>
		
		<div id = "password-div">
			<input type="password" placeholder="password" id="password" name = "password">
		</div>
		
		<div id = "login-div">
			
			<input type=submit value="log in" class="button" id="login">
			
			
		
		</div>
	
	</form>
	
	<form action="http://localhost:8080/IdeaCloud/joinNow" id = "join">
				<input type=submit value="Join Now" class="button" id="register">
	</form>

</body>
</html>