<%@page import="java.util.ArrayList"%>
<%@page import="Core.helper"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
body{
	font-family: "Comic Sans MS", Helvetica, Arial, sans-serif;
	font-size: 14px;
	line-height: 32px;
	color: #333333;
	font-weight: normal;
}
.container{
	position: absolute;
	top: 25px;
	left:350px;
	margin: 0 auto;
	width: 600px;
}
.logo{
	text-align: center;
}
.chat-zone{
	padding: 20px;
	height: 400px;
}
.chatmsg {
margin: 0 5px;
}
.chatmsg b{
	text-transform: uppercase;
	color: orange;
}
.chat{
	margin: 0 auto;
}
.chat #chatZone{
width: 500px;
height: 400px;
border: 1px solid #ddd;
border-radius: 7px;
overflow-y: scroll;
background: #333;
color: #fff;
}
.chat form{
	margin: 10px 0 0 0;
}
input[type="text"]{
border: 1px solid #cccccc;
box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
outline: none;
padding: 4px 6px;
font-size: 14px;
line-height: 20px;
color: #555555;
border-radius: 3px;
width: 425px;
}
input[type="submit"]{
display: block;
color: #fff;
font-weight: bold;
cursor: pointer;
margin: 5px 0 0 60px;
padding: 6px 10px;
border: 1px solid #777;
background: #333537;
border-radius: 5px;
}





.myButton {
	-moz-box-shadow:inset 0px 1px 3px 0px #91b8b3;
	-webkit-box-shadow:inset 0px 1px 3px 0px #91b8b3;
	box-shadow:inset 0px 1px 3px 0px #91b8b3;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #768d87), color-stop(1, #6c7c7c));
	background:-moz-linear-gradient(top, #768d87 5%, #6c7c7c 100%);
	background:-webkit-linear-gradient(top, #768d87 5%, #6c7c7c 100%);
	background:-o-linear-gradient(top, #768d87 5%, #6c7c7c 100%);
	background:-ms-linear-gradient(top, #768d87 5%, #6c7c7c 100%);
	background:linear-gradient(to bottom, #768d87 5%, #6c7c7c 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#768d87', endColorstr='#6c7c7c',GradientType=0);
	background-color:#768d87;
	-moz-border-radius:5px;
	-webkit-border-radius:5px;
	border-radius:5px;
	border:1px solid #566963;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:13px;
	font-weight:bold;
	padding:11px 42px;
	text-decoration:none;
	text-shadow:0px -1px 0px #2b665e;
}
.myButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #6c7c7c), color-stop(1, #768d87));
	background:-moz-linear-gradient(top, #6c7c7c 5%, #768d87 100%);
	background:-webkit-linear-gradient(top, #6c7c7c 5%, #768d87 100%);
	background:-o-linear-gradient(top, #6c7c7c 5%, #768d87 100%);
	background:-ms-linear-gradient(top, #6c7c7c 5%, #768d87 100%);
	background:linear-gradient(to bottom, #6c7c7c 5%, #768d87 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#6c7c7c', endColorstr='#768d87',GradientType=0);
	background-color:#6c7c7c;
}


       


 .peers{
 overflow-y: scroll;
	width: 250px;
	height:600px;
}       


.users{
text-transform: uppercase;
	color: orange;
	position:absolute;
	top: 25px;
}
#host{
	position: absolute;
	left: 350px;
}

#second{
	position: absolute;
	left: 750px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% %>
</head>
<body>
<%String user = request.getParameter("myUser");
String second = request.getParameter("hisUser");%>
<div class = "users" id = "host"><%= user %></div>
<div class = "users" id = "second"><%= second %></div>
<div class="peers" id = "peers">
<%
helper h1 = new helper();
ArrayList<String> pr = h1.getList(user);
System.out.println(pr.size());
for(int i=0; i<pr.size(); i++){
	String name = pr.get(i);
%>
<input type="submit" class="myButton" id= <%=name %> value = <%=name %> onclick = "peer(<%=name %>)"/>

<%} %>
</div>
<div class="container">
		
		<div class="chat">
		<input type="submit" id = "old" value = "show messages" onclick = "show()"/>
		<script type="text/javascript">
		var jsArray = [];
		var num =0;
		if(document.getElementById("second").innerHTML.length>0 && document.getElementById("host").innerHTML.length >0){
			alert("egaa");
		var zone=document.getElementById("chatZone");
		
		
		<%
			helper h = new helper();
			ArrayList<String> strList =h.getMessages(user, second);
			
			StringBuffer values = new StringBuffer();
			for (int i = 0; i < strList.size(); ++i) {
			    if (values.length() > 0) {
			        values.append(',');
			    }
			    values.append(strList.get(i));
			
			    
			}
			%>
			
			jsArray = <%= values.toString() %>.split(",");
			
			
		}
		</script>
		

			<div id="chatZone" name="chatZone"></div>
				<form onsubmit="chat.sendMsg(); return false;">
					<label for="msg" style="float:left">Message:</label>
					<input type="text" id="msg" name="msg" autofocus="true" placeholder="Type Your Meassage Here" />
					<input type="submit"  value = "send"/>
				</form>
			</div>
	</div>
	<script type="text/javascript" src="chat.js"></script>
</body>
</html>