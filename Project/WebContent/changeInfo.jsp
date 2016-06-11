<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>change info</title>
</head>
<body>

<div id = "logo">
		<img src="logo.png" id = "image-logo">
	</div>

	<form action="http://localhost:8080/IdeaCloud/hereWeGo" id = "form">
	
		<div id = "name-div">
		
			<div id = "name-text" class = "labels">
				name:
			</div>
			
			<input type="text" id="name" value = ::name:: name = "name">
			
		</div>
		
		<div id = "surname-div">
		
			<div id = "surname-text" class = "labels">
				surnname:
			</div>
			
			<input type="text" id="surname" value = ::surname:: name = "surname">
			
		</div>
		
		<div id = "username-div">
		
			<div id = "username-text" class = "labels">
				username:
			</div>	
			
			<input type="text" id="username" value = ::username:: name = "username">
			
		</div>
		
		<div id = "email-div">
		
			<div id = "email-text" class = "labels">
				e-mail:
			</div>
			
			<input type="text" id="mail" value = ::email:: name = "email">
			
		</div>
		
		<div id = "password-div">
			
			<div id = "password-text" class = "labels">
			new password:
			</div>
			
			<input type="password" id="password" placeholder="new-password" name = "password">
			
		</div>
		
		<div id = "password-again-div">
		
			<div id = "password-again-text" class = "labels">
			repeate password:
			</div>
		
			<input type="password" id="password-again" placeholder="repeate password" name = "again">
		</div>
		
		<div id = "submit-div">
			<input id="button" type=submit value="save changes" class="button">
		</div>
		
	</form>



<style>


body{
	background-color: #BCDBFE;
}
.labels{
position: absolute;
	top: 10%;
	left: -60%;
	font-size: large;
}



#name {
	font-size: 150%
}

#surname {
	font-size: 150%
}

#username {
	font-size: 150%
}

#mail {
	font-size: 150%
}

#password {
	font-size: 150%
}

#password-again {
	font-size: 150%
}

#button {
	font-size: 150%
}


#form{
	
}

#logo{
	position: absolute;
	left: 45%;
	top: 10%;
}
#image-logo{
	width: 40%;
}

#name-div{
	position: absolute; 
	left: 40%;
	top: 34%;
}
#surname-div{
	position: absolute; 
	left: 40%; 
	top: 42%;
}
#username-div{
	position: absolute; 
	left: 40%; 
	top: 50%;
}
#email-div{
	position: absolute; 
	left: 40%; 
	top: 58%;
}
#password-div{
	position: absolute; 
	left: 40%; 
	top: 66%;
}
#password-again-div{
	position: absolute; 
	left: 40%; 
	top: 74%;
}
#submit-div{
	position: absolute; 
	left: 44%; 
	top: 85%;
}
#post-div{
	position: absolute; 
	left: 80%; 
	top:5%;
}

input[type=button], input[type=submit], input[type=reset] {
    background-color: #5CA3F4;
    border: none;
    color: white;
    padding: 10px;
    text-decoration: none;
    cursor: pointer;
}


#change-info-div{
	position: absolute;
	top: 5%;
	left: 20%;
}


</style>








	

</body>
</html>