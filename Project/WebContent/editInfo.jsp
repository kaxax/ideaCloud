<%@page import="Core.User"%>
<%@page import="java.sql.Connection"%>
<%@page import="Core.Database"%>
<%@page import="java.util.Date"%>
<%@page import="Core.Pool"%>
<%@page import="org.apache.catalina.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="editInfo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.0.0.min.js"
	integrity="sha256-JmvOoLtYsmqlsWxa7mDSLMwa6dZ9rrIdtrrVYRnDRH0="
	crossorigin="anonymous"></script>
</head>
<%
	
	HttpSession ses = request.getSession(false);
	Object stringAttr = ses.getAttribute("user_id");
	if (stringAttr == null){
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
	String userIdAsString = stringAttr.toString();
	int userId = Integer.parseInt(userIdAsString);
	Pool p = Pool.getPool();
	Connection con = p.getConnection();
	Database db = new Database(con);
	User user = db.getUser(userId);
	String firstName = user.getUserName();
	String lastName = user.getUserSurname();
	String nickName = user.getUSerNickname();
	String mail = user.getUserEmail();
	String imgSrc = user.getUSerImgSrc();
	if(imgSrc.equals("")){
		imgSrc = "unknown.png";
	}
	int sex = user.getUserSex();
	String gender = "male";
	if (sex == 0) {
		gender = "female";
	}
	con.close();
%>
<body>
	<div id="editDiv">
	<form id="editInfo" action="editInfo" method="post" enctype="multipart/form-data"  autocomplete="off">
		<img src=<%=imgSrc%> height="200" id="userImg" name="userImg" width="200" height="200"/><br>
		<input type="file" name="imgBrowse" id="imgBrowse"  onchange="previewFile()"  autocomplete="off"><br> 
		First name:<br>
		<input type="text" name="editF" id="editF" value=<%=firstName%>><br>
		Last name:<br>
		<input type="text" name="editN" id="editN" value=<%=lastName%>><br>
		<input type="hidden" name="editL" id="editL" value=<%=nickName%>><br>
		Sex:<br>
		<input type="radio" name="sex" id="male">Male 
		<input type="radio" name="sex" id="female">Female<br>
		Password:<br>
		<input type="text" name="editP" id="editP"><br>
		<input type="hidden" id="sendImg" name="imgSend">
		<input type="hidden" id="gnd" name="gnd" value=<%=gender%>>
		<input type="button" id="editSubmit" name="editSubmit" value="submit">
	</form>
	</div>
</body>
<script>
	function previewFile() {
		var preview = document.getElementById('userImg');
		var file = document.querySelector('input[type=file]').files[0]; //sames as here
		var reader = new FileReader();

		reader.onloadend = function() {
			preview.src = reader.result;
			var imgSource = document.getElementById('userImg').src;
			document.getElementById('sendImg').value = "changed";
		}
		if (file) {
			reader.readAsDataURL(file); //reads the data as a URL
		} else {
			preview.src = "";
		}
	}
	$(document).ready(function() {
		var gnd = document.getElementById("gnd").value;
		if(gnd == "male"){
			document.getElementById('male').checked = true;
		}
		else if(gnd == "female"){
			document.getElementById('female').checked = true;
		}
	});
	$(document).ready(function(){
		$('#editSubmit').click(function() {
			if (document.getElementById('male').checked) {
				document
						.getElementById('gnd').value = 'male';
			} else if(document.getElementById('female').checked) {
				document
						.getElementById('gnd').value = 'female';
			}
			document.getElementById("editInfo").submit();
		});
	});
</script>
</html>