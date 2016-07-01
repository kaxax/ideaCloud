
<%@page import="java.io.IOException"%>
<%
	HttpSession ses = request.getSession(false);
	String usr_id = null;
	try {
		usr_id = ses.getAttribute("user_id").toString();
	} catch (Exception e) {
	}
	if (usr_id != null) {
		response.sendRedirect("http://localhost:8080/IdeaCloud/homepage.jsp");
	}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="login.css">
<title>login</title>
<script src="http://code.jquery.com/jquery-3.0.0.min.js"
	integrity="sha256-JmvOoLtYsmqlsWxa7mDSLMwa6dZ9rrIdtrrVYRnDRH0="
	crossorigin="anonymous"></script>
</head>
<body>
	<input type="text" id="hiddenUser" style="visibility: hidden;"
		name="myId" />
	<input type="text" id="hiddenPassword" style="visibility: hidden;"
		name="myPass" />
	<form action="" method="post" class="form">

		<fb:login-button scope="public_profile,email"
			onlogin="checkLoginState();" id="image-fb">
		</fb:login-button>
		<div id="status"></div>
		<div id="logo">
			<img src="logo.png" id="image-logo">
		</div>

		<div id=share>
			<img src="share.png" id="image-share">
		</div>

		<div id="help">
			<img src="help.gif" id="image-help">
		</div>

	</form>
	<form id="loginForm" method="post">
		<div id="username-div">
			<input type="text" placeholder="username" id="username"
				name="username">
		</div>

		<div id="password-div">
			<input type="password" placeholder="password" id="password"
				name="password">
		</div>

		<div id="login-div">
			<input type="submit" name="loginSubmit" value="log in" class="button"
				id="login">
		</div>
	</form>
	<form action="http://localhost:8080/IdeaCloud/joinNow" id="join">
		<input type=submit value="Join Now" class="button" id="register">
	</form>
	<form id="fbLoginForm" method="post" style="display:none">
		<input type="text" id="fbFirstName" name="fbFirstName"> <input
			type="text" id="imgSrc" name="imgSrc"> <input type="text"
			id="fbLastName" name="fbLastName"> <input type="text"
			id="fbMail" name="fbMail"> <input type="submit"
			name="fbSubmit" id="fbSubmit">
	</form>
</body>
<script>
	function statusChangeCallback(response) {
		if (response.status === 'connected') {
			// Logged into your app and Facebook.
			doMagic();
		} else if (response.status === 'not_authorized') {
			// The person is logged into Facebook, but not your app.

		} else {
			// The person is not logged into Facebook, so we're not sure if
			// they are logged into this app or not.
		}
	}

	
	function checkLoginState() {
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
		
	}

	window.fbAsyncInit = function() {
		FB.init({
			appId : '494945434033230',
			cookie : true, // enable cookies to allow the server to access 
			// the session
			xfbml : true, // parse social plugins on this page
			version : 'v2.5' // use graph api version 2.5
		});

	};

	// Load the SDK asynchronously
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

	
	function doMagic() {
		FB.api('/me', {
			fields : 'first_name,last_name, email, id'
		}, function(response) {
			var imgSrc = 'http://graph.facebook.com/' + response.id
					+ '/picture?type=normal';
			$("#fbFirstName").val(response.first_name);
			$("#fbLastName").val(response.last_name);
			$("#fbMail").val(response.email);
			$("#imgSrc").val(imgSrc);
			$("#fbLoginForm").submit();
		});
	}
	//test
	$(document)
			.ready(
					function() {
						$('#loginForm')
								.submit(
										function() {
											$
													.ajax({
														url : "homePage",
														type : "POST",
														dataType : "json",
														data : $("#loginForm")
																.serialize(),
														success : function(data) {
															if (data.id != -1) {
																window.location = "http://localhost:8080/IdeaCloud/homepage.jsp";
															} else {
																alert("Incorrect combination of: \n user: "
																		+ data.username
																		+ "\n password: "
																		+ data.password);
															}
														}
													});
											return false;
										});
					});
	$(document)
			.ready(
					function() {
						$('#fbLoginForm')
								.submit(
										function() {
											$
													.ajax({
														url : "fbLogin",
														type : "POST",
														dataType : "json",
														data : $("#fbLoginForm")
																.serialize(),
														success : function(data) {
															window.location = "http://localhost:8080/IdeaCloud/homepage.jsp";
														}
													});
											return false;
										});
					});
</script>
</html>