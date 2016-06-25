
<%@page import="java.io.IOException"%>
<%
	HttpSession ses = request.getSession(false);
	String usr_id = null;
	try {
		usr_id = ses.getAttribute("username").toString();
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
	<form id="loginForm"
		 method="post">
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
	<div id="testResponse"></div>
</body>
<script>
	// This is called with the results from from FB.getLoginStatus().
	function statusChangeCallback(response) {
		console.log('statusChangeCallback');
		console.log(response);
		// The response object is returned with a status field that lets the
		// app know the current login status of the person.
		// Full docs on the response object can be found in the documentation
		// for FB.getLoginStatus().
		if (response.status === 'connected') {
			// Logged into your app and Facebook.
			testAPI();
		} else if (response.status === 'not_authorized') {
			// The person is logged into Facebook, but not your app.
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into this app.';
		} else {
			// The person is not logged into Facebook, so we're not sure if
			// they are logged into this app or not.
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into Facebook.';
		}
	}

	// This function is called when someone finishes with the Login
	// Button.  See the onlogin handler attached to it in the sample
	// code below.
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

		// Now that we've initialized the JavaScript SDK, we call 
		// FB.getLoginStatus().  This function gets the state of the
		// person visiting this page and can return one of three states to
		// the callback you provide.  They can be:
		//
		// 1. Logged into your app ('connected')
		// 2. Logged into Facebook, but not your app ('not_authorized')
		// 3. Not logged into Facebook and can't tell if they are logged into
		//    your app or not.
		//
		// These three cases are handled in the callback function.

		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
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

	// Here we run a very simple test of the Graph API after login is
	// successful.  See statusChangeCallback() for when this call is made.
	function testAPI() {
		console.log('Welcome!  Fetching your information.... ');
		FB
				.api(
						'/me',
						function(response) {
							console.log('Successful login for: '
									+ response.name);
							document.getElementById('status').innerHTML = 'Thanks for logging in, '
									+ response.name + '!';
						});
	}
	//test
	$(document).ready(function() {
		$('#loginForm').submit(function() {
			$.ajax({
				url : "homePage",
				type : "POST",
				dataType: "json",
				data : $("#loginForm").serialize(),
				success : function(data) {
					if (data.id != -1){
						window.location = "http://localhost:8080/IdeaCloud/homepage.jsp";
					}
					else{
						alert("Incorrect combination of: \n user: " + data.username + "\n password: " + data.password);
					}
				}
			});
			return false;
		});
	});
</script>
</html>