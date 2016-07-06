<%@page import="Core.User"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Date" %>
<%@page import="java.util.UUID"%>
<%@page import="Core.Database"%>
<%@page import="Core.Pool"%>
<%@page import="org.apache.catalina.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="userpage.css" />
</head>
<body onload="initf()">
<%
	HttpSession ses = request.getSession(false);
	String userIdAsString = ses.getAttribute("user_id").toString();
	int userId =  Integer.parseInt(userIdAsString);
	Pool p = Pool.getPool();
	Connection con = p.getConnection();
	Database db  = new Database(con);
	User user = db.getUser(userId);
	String imgSrc = user.getUSerImgSrc();
	if(imgSrc.equals("")){
		imgSrc = "unknown.png";
	}
	int rank  = user.getUserLevel();
	con.close();
	String tmp = request.getParameter("author_id");
	int author_id = Integer.parseInt(tmp.split("_")[2]);
%>
<img src=<%=imgSrc%> class = "profpic" alt="profile pic" type="image" />
<input id="editInfo" type="button" value="editInfo" onclick="editInfo();" />
<div id ="postsDiv">
</div>


<td><input type="text" id ="session_user_id"value="<%= session.getAttribute("user_id") %>" style="  visibility: hidden;"/></td>
<script>
function initf(){
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {	
	    	postsT = xhttp.responseText;
	    	document.getElementById("postsDiv").innerHTML = postsT;
	    }
	  };
	  xhttp.open("POST", "http://localhost:8080/IdeaCloud/getPosts", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("type="+"2" +"&walltype=" + "1"+"&searchTerm=" + "" +"&categories=" + ""); 
}

function editInfo(){
	window.location = "http://localhost:8080/IdeaCloud/editInfo.jsp";
}
function gotoUserpage(){
	window.location = "http://localhost:8080/IdeaCloud/userpage.jsp";
}

</script>
</body>
</html>