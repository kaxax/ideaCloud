<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="registration.css">
	<title>register</title>
</head>
<body>

<form action="registration.html">
	<div style="position: absolute; left: 40%; top: 20%">
		<input type = "text" id="name" placeholder="name">
	</div>
	<div style="position: absolute; left: 40%; top: 30%">
	<input type = "text" id="surname" placeholder="surname">
	</div>
	<div style="position: absolute; left: 40%; top: 40%">		
	<input type = "text" id="username" placeholder="username">
	</div>	
	<div style="position: absolute; left: 40%; top: 50%">	
	<input type = "text" id="mail" placeholder="e-mail">
	</div>
	<div style="position: absolute; left: 40%; top: 60%">		
	<input type = "password" id="password" placeholder="password">
	</div>
	<div style="position: absolute; left: 40%; top: 70%">		
	<input type = "password" id="password-again" placeholder="repeate password">
	</div>
	<div style="position: absolute; left: 45%; top: 80%">		
	<input id="button"  type=submit value = "here we go!" class="button" onclick="myFunction()">
	</div>
</form>


</body>
</html>
