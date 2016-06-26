<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="registration.css">
<title>register</title>
<script src="http://code.jquery.com/jquery-3.0.0.min.js"
	integrity="sha256-JmvOoLtYsmqlsWxa7mDSLMwa6dZ9rrIdtrrVYRnDRH0="
	crossorigin="anonymous"></script>
</head>
<body>

	<div id = "logo">
		<img src="logo.png" id = "image-logo">
	</div>

	<form id = "form" method="post">
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
			<input id="button" type="submit" value="here we go!" class="button">
		</div>
		
	</form>
	
	<div id = "post-div">
	
		<form action="homepage.jsp">
			<input type=submit value="see post" class="button" id="register">
		</form>
		
	</div>
	
	<div id = change-info-div>
	
		<form action="http://localhost:8080/IdeaCloud/checkPassword">
			<input type=submit value="change info" class="button" id="change-info">
		</form>
	
	</div>


</body>
<script>
$(document).ready(function() {
	$('#form').submit(function() {
		$.ajax({
			url : "register",
			type : "POST",
			dataType: "json",
			data : $("#form").serialize(),
			success : function(data) {
				if (data.errorCase == 0){
					window.location = "http://localhost:8080/IdeaCloud/index.jsp";
				}
				else if (data.errorCase == 123) {
					alert("Not everything is filled, password doesnt match, mail used");
				}
				else if (data.errorCase == 12) {
					alert("Not everything is filled, password doesnt match");
				}
				else if (data.errorCase == 23) {
					alert("Password doesnt match, mail used");
				}
				else if (data.errorCase == 13) {
					alert("Not everything is filled, mail used");
				}
				else if (data.errorCase == 1) {
					alert("Not everything is filled");
				}
				else if (data.errorCase == 2) {
					alert("Password doesnt match");
				}
				else if (data.errorCase == 3) {
					alert("Mail used");
				}
			}
		});
		return false;
	});
});
</script>
</html>
