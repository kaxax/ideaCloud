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
	Object stringAttr = ses.getAttribute("user_id");
	if (stringAttr == null){
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
	String userIdAsString = stringAttr.toString();
	int userId =  Integer.parseInt(userIdAsString);
	Pool p = Pool.getPool();
	Connection con = p.getConnection();
	Database db  = new Database(con);
	User user = db.getUser(userId);
	String myUsername = user.getUSerNickname();
	System.out.println(myUsername);
	String imgSrc = user.getUSerImgSrc();
	if(imgSrc.equals("")){
		imgSrc = "unknown.png";
	}
	int rank  = user.getUserLevel();
	String tmp = request.getParameter("author_id");
	int author_id = Integer.parseInt(tmp.split("_")[2]);
	User he = db.getUser(author_id);
	String hisUsername = he.getUSerNickname();
	String hisImgSrc = he.getUSerImgSrc();
	String hisName = he.getUserName();
	String hisSurname = he.getUserSurname();
	con.close();
%>
<img src=<%=hisImgSrc%> class = "profpic" alt="profile pic" type="image" />
<div id="text">
<p class="justP">Nick: <%=hisUsername%></p></br>
<p class="justP">Name: <%=hisName%></p></br>
<p class="justP">Surname: <%=hisSurname%></p></br>
</div>
<div id=main3button>
<input id="homepage" type="button" value="Home" onclick="goHomepage();" />
<input id="editProfile" type="button" value="Edit Profile" onclick="editInfo();" />
<input id="logout" type="button" value="Logout" onclick="doLogout();" />
</div>
<input id="chat" type="button" value="chat" onclick="goChat();" >
<input id="tmp1" type="hidden" value=<%=myUsername%>>
<input id="tmp2" type="hidden" value=<%=hisUsername%>>
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
	  xhttp.send("type="+"2" +"&walltype=" + "1"+"&searchTerm=" + "" +"&categories=" + "" + "&id=" + <%=author_id%>); 
}

function editInfo(){
	window.location = "http://localhost:8080/IdeaCloud/editInfo.jsp";
}
function gotoUserpage(){
	window.location = "http://localhost:8080/IdeaCloud/userpage.jsp";
}
function goChat(){
	myUser = document.getElementById("tmp1").value;
	hisUser = document.getElementById("tmp2").value;
	window.location = "http://localhost:8080/IdeaCloud/chat.jsp?myUser=" + myUser + "&hisUser=" + hisUser;
}
function doLogout(){
	document.getElementById("logoutForm").submit();
}
function goHomepage(){
	window.location = "http://localhost:8080/IdeaCloud/homepage.jsp";
}
function editInfo(){
	window.location = "http://localhost:8080/IdeaCloud/editInfo.jsp";
}


</script>
</body>
</html>