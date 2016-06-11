<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>check password</title>
</head>
<body>
	<form action="http://localhost:8080/IdeaCloud/changeInfo">
		<div id = "title-div">
			Please confirm your password
		</div>
		<div id = "password-div">
			<input type=text placeholder="password" class="button" id="password">
		</div>
		<div id =  "confirm-div">
			<input type = submit value = "confirm" id = "confirm">
		</div>
	</form>
</body>

<style>


body{
	background-color: #BCDBFE;
}
#title-div{
	position: absolute;
	left: 39%;
	top: 30%;
	font-size: x-large;
	font-style: oblique;
	font-weight: bold;
}
#password-div{
	position:  absolute;
	left: 40%;
	top: 45%;

}
#confirm-div{
	position: absolute;
	left: 47%;
	top: 55%;
}
#password{
	font-size: 150%;
}

#confirm{
    background-color: #5CA3F4;
    border: none;
    color: white;
    padding: 10px;
    text-decoration: none;
    cursor: pointer;
    font-size: 120%;
}

</style>

</html>
