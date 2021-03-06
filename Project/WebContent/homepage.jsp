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
<link rel="stylesheet" type="text/css" href="homepage.css" />
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
	String nickname = user.getUSerNickname();
	String imgSrc = user.getUSerImgSrc();
	if(imgSrc.equals("")){
		imgSrc = "unknown.png";
	}
	int rank  = user.getUserLevel();
	con.close();
%>

<img src=<%=imgSrc%> class = "profpic" alt="profile pic" type="image" id = <%="user_id_"+userId%> onclick="goToUser(this.id)" style = "cursor: pointer;"/>

<div style="position: absolute; top: 200px; font-size: xx-large; left: 120px;">
<%=nickname%>
</div>
<img src="add.jpg" style="width: 200px; position: absolute; top: 300px; left: 80px;"/>

<div id=main3button>
<input id="homepage" type="button" value="Home" onclick="goHomepage();" />
<input id="editProfile" type="button" value="Edit Profile" onclick="editInfo();" />
<input id="logout" type="button" value="Logout" onclick="doLogout();" />
</div>
<form action="logout" id="logoutForm" method="POST"></form>
<div class="dropdown" id = "search-dropdown">
  <button class="dropbtn" onclick="showSearch() ">search</button>
  <div class="dropdown-content">
   <div id ="search_container" style="position:relative;">
	<button class="dropbtn" onclick="searchit() " style = " width:70px; height:48px;float:right;margin-top:3px">Go</button>		
  	<textarea placeholder="type words to search" rows="1" 
  	name="comment[text]" id="search_post_text" cols="1" class="ui-autocomplete-input" 
  	autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true">
  	</textarea>
  	</div>
    <a onclick="reply_click(this.id)" id = "0" href="#">computer science</a>
    <a onclick="reply_click(this.id)" id = "1" href="#">biology</a>
    <a onclick="reply_click(this.id)" id = "2" href="#">art</a>
    <a onclick="reply_click(this.id)" id = "3" href="#">engineering</a>
    <a onclick="reply_click(this.id)" id = "4" href="#">AI</a>
    <a onclick="reply_click(this.id)" id = "5" href="#">Novels</a>
    <a onclick="reply_click(this.id)" id = "6" href="#">social</a>
    <a onclick="reply_click(this.id)" id = "7" href="#">fight</a>
    <a onclick="reply_click(this.id)" id = "8" href="#">chemistry </a>
    <a onclick="reply_click(this.id)" id = "9" href="#">film</a>
    <a onclick="reply_click(this.id)" id = "10" href="#">poetry</a>    
  </div>
</div>

<div class="post-text-area">
<select id="blue">
  <optgroup label="Choose I or Q">
    <option>Idea</option>
    <option>Question</option>
  </optgroup>
</select>

<div class="dropdown" id = "post_dropdown">
  <button class="dropbtn" id = "post_dropbtn" >Choose Categories</button>
  <div class="dropdown-content" id = "post_dropdown-content ">
    <a onclick="reply_post_click(this.id)" id = "p1" href="#">computer science</a>
    <a onclick="reply_post_click(this.id)" id = "p2" href="#">biology</a>
    <a onclick="reply_post_click(this.id)" id = "p3" href="#">art</a>
    <a onclick="reply_post_click(this.id)" id = "p4" href="#">engineering</a>
    <a onclick="reply_post_click(this.id)" id = "p5" href="#">AI</a>
    <a onclick="reply_post_click(this.id)" id = "p6" href="#">Novels</a>
    <a onclick="reply_post_click(this.id)" id = "p7" href="#">social</a>
    <a onclick="reply_post_click(this.id)" id = "p8" href="#">fight</a>
    <a onclick="reply_post_click(this.id)" id = "p9" href="#">chemistry </a>
    <a onclick="reply_post_click(this.id)" id = "p10" href="#">film</a>
    <a onclick="reply_post_click(this.id)" id = "p11" href="#">poetry</a>    
  </div>
</div>
</div>
<div id="post_push_area">
<textarea placeholder="type here for Title" rows="1" 
  	name="comment[text]" id="search_title_text" cols="1" class="ui-autocomplete-input" 
  	autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true">
</textarea>
<textarea placeholder = "whats on your mind" rows="2" 
  name=comment[text]" id="post_text" cols="1" class="ui-autocomplete-input" 
  autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true">
  </textarea>
  <button  id = "post_push_btn" onclick="post_it()">post</button>
</div>


<div id="QorA-area">
<div>

    <input id ="i_box" type="checkbox" name="system_type3" onclick="iClick(this)" value="ok" checked/>
    <span style="width:100px;display:inline-block;">Idea</span>
    <input id ="q_box" type="checkbox" name="system_type3" onclick="qClick(this)" value="ok" checked/>
    <span style="width:100px;display:inline-block;">Question</span>
</div>
</div>

<div id ="postsDiv">
</div>

<td><input type="text" id ="session_user_id"value="<%= session.getAttribute("user_id") %>" style="  visibility: hidden;"/></td>
<script>
function doLogout(){
	document.getElementById("logoutForm").submit();
}
function goHomepage(){
	window.location = "http://localhost:8080/IdeaCloud/homepage.jsp";
}
function goToUser(author_id){
	window.location = "http://localhost:8080/IdeaCloud/userpage.jsp?author_id=" + author_id;
}
function editInfo(){
	window.location = "http://localhost:8080/IdeaCloud/editInfo.jsp";
}
function post_share(id){
	var postId = id.split("_")[2];
	var ttl = document.getElementById("post_id_"+postId).innerHTML;
	var text = document.getElementById("post_text_"+postId).innerHTML;
	var win = open("share.jsp?text="+text+"&title="+ttl, "MsgWindow", 'width=500,height=300');
}
function gotoUserpage(){
	window.location = "http://localhost:8080/IdeaCloud/userpage.jsp";
}
var user_id=document.getElementById("session_user_id").value;	
function post_it(){
	title = document.getElementById("search_title_text").value;
	text = document.getElementById("post_text").value;
	addPosts(title,text);
}
marked_categories = [false,false,false,false,false,false,false,false,false,false,false];
post_marked_categories = [false,false,false,false,false,false,false,false,false,false,false];
function showSearch(){
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }	
  function goToPost(id){
	  var postId = id.split("_")[2];
	  var url = "http://localhost:8080/IdeaCloud/postPage?post_id="+postId;
	  window.location.href = url;	  
 	  return false;
 }
  function iClick(idea){
		if (document.getElementById('i_box').checked && document.getElementById('q_box').checked){
			type = 2;
		}else if(document.getElementById('i_box').checked){
			type = 0;
		}else if(document.getElementById('q_box').checked){
			type = 1;
		}else{
			type =2;
		}
		categories = JSON.stringify(marked_categories_backup);
		searchTerm = last_search;
		getPostcheckboxes(0, type, searchTerm,categories);
	}
	function qClick(question){
		if (document.getElementById('i_box').checked && document.getElementById('q_box').checked){
			type = 2;
		}else if(document.getElementById('i_box').checked){
			type = 0;
		}else if(document.getElementById('q_box').checked){
			type = 1;
		}else{
			type =2;
		}
		categories = JSON.stringify(marked_categories_backup);
		searchTerm = last_search;
		getPostcheckboxes(0, type, searchTerm,categories);
	}
	marked_categories = [false,false,false,false,false,false,false,false,false,false,false];
	marked_categories_backup = [false,false,false,false,false,false,false,false,false,false,false];
	post_marked_categories = [false,false,false,false,false,false,false,false,false,false,false];
	function showSearch(){
	    var dropdowns = document.getElementsByClassName("dropdown-content");
	    var i;
	    for (i = 0; i < dropdowns.length; i++) {
	      var openDropdown = dropdowns[i];
	      if (openDropdown.classList.contains('show')) {
	        openDropdown.classList.remove('show');
	      }
	    }
	  }	
	  function goToPost(id){
		  var postId = id.split("_")[2];
		  var url = "http://localhost:8080/IdeaCloud/postPage?post_id="+postId;
		  window.location.href = url;	  
	 	  return false;
	 }
	 function voteUp(id){
		 var vote_result = 0;
		 var vote_but = document.getElementById(id);
		 var postId = id.split("_")[1];
		 var vote_but1 = document.getElementById("down-arrow_"+postId);
		 clr = vote_but.style.backgroundColor;
			clr1 = vote_but1.style.backgroundColor;
		 if(clr == 'blue'){
				vote_result = 1;
			}else if (clr1 == 'red'){
				vote_result =-1;
			}else{
				vote_result = 0;
			}
			
		if(vote_result == 1){
			
			var vote_num = document.getElementById("up-count_"+postId);
			vote_num.innerHTML =  parseInt(vote_num.innerHTML, 10)- 1;
			vote_result =0
			vote_but.style.backgroundColor = '';
		}else if(vote_result == -1){
			
			var vote_num = document.getElementById("down-count_"+postId);
			vote_num.innerHTML = parseInt(vote_num.innerHTML, 10)+ 1;
			var vote_but1 = document.getElementById("down-arrow_"+postId);
			vote_but1.style.backgroundColor = '';
			vote_result =0;
			vote_but.style.backgroundColor = '';
			voteUp(id);
			return;
		}else if(vote_result == 0){
			
			var vote_num = document.getElementById("up-count_"+postId);
			vote_num.innerHTML = parseInt(vote_num.innerHTML, 10)+ 1;
			vote_but.style.backgroundColor = 'blue';
			vote_result =1;
		}
		  var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {	
		    	postsT = xhttp.responseText;	
		    	 
		    }
		  };
		  
		  vote_down_value = document.getElementById("down-count_"+postId).innerHTML;
		  vote_up_value = document.getElementById("up-count_"+postId).innerHTML;
		  xhttp.open("POST", "http://localhost:8080/IdeaCloud/changeCloud", true);
		  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		  xhttp.send("userId="+ user_id +"&postId="+postId +"&result=" + vote_result+"&result1=" + "1"+"&voteup=" + vote_up_value+"&votedown=" + vote_down_value); 
	 }
	function voteDown(id){
		 var vote_result = 0;

		var vote_but = document.getElementById(id);
		var postId = id.split("_")[1];
		var vote_but1 = document.getElementById("up-arrow_"+postId);
		clr = vote_but.style.backgroundColor;
		clr1 = vote_but1.style.backgroundColor;
		
		if(clr == 'red'){	
			vote_result = -1;
		}else if (clr1 == 'blue'){
			vote_result = 1;
		}else{
			vote_result = 0;
		}

		if(vote_result == 1){

			var vote_num = document.getElementById("up-count_"+postId);
			vote_num.innerHTML =  parseInt(vote_num.innerHTML, 10)- 1;
			vote_result =0;
			vote_but.style.backgroundColor = '';
			vote_but1 = document.getElementById("up-arrow_"+postId);
			vote_but1.style.backgroundColor = '';
			voteDown(id);
			return;
		}else if(vote_result == -1){

			var vote_num = document.getElementById("down-count_"+postId);
			vote_num.innerHTML =  parseInt(vote_num.innerHTML, 10)+ 1;
			vote_result =0;
			vote_but.style.backgroundColor = '';
		}else if(vote_result == 0){
			var vote_num = document.getElementById("down-count_"+postId);
			vote_num.innerHTML = parseInt(vote_num.innerHTML, 10)- 1;
			vote_but.style.backgroundColor = 'red';
			vote_result =-1;
		}
			  var xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
			    if (xhttp.readyState == 4 && xhttp.status == 200) {	
			    	postsT = xhttp.responseText;	 
			    }
			  };
				vote_down_value = document.getElementById("down-count_"+postId).innerHTML;
				vote_up_value = document.getElementById("up-count_"+postId).innerHTML;
				xhttp.open("POST", "http://localhost:8080/IdeaCloud/changeCloud", true);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send("userId="+ user_id +"&postId="+postId +"&result=" + vote_result+"&result1=" + "-1"+"&voteup=" + vote_up_value+"&votedown=" + vote_down_value); 
	 }
	function addPosts(title,text){
		  var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {	
		    	postsT = xhttp.responseText;	
		    	 var shadow = document.getElementById("postsDiv");
		    	 shadow.innerHTML = postsT + shadow.innerHTML;
		    }
		  };
		  xhttp.open("POST", "http://localhost:8080/IdeaCloud/addPosts", true);
		  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		  var type = document.getElementById("blue").selectedIndex;
		  //es mere shecvale user id 
		  //user_id=0;
		  topic = JSON.stringify(post_marked_categories);
		  xhttp.send("userId="+ user_id +"&title="+title +"&text="+text+"&type="+type+"&topic="+topic); 
	}
	//boolean status, int userId, int wallType, boolean questions, boolean ideas, ArrayList<String> categories, String searchTerm
	function getPosts(walltype,type,searchTerm){

		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {	
		    	last_search=searchTerm;
		    	marked_categories_backup =marked_categories;
		    	postsT = xhttp.responseText;		    	
		    	var shadow = document.getElementById("postsDiv");
		    	shadow.innerHTML = postsT;
		    }
		  };
		  
		  xhttp.open("POST", "http://localhost:8080/IdeaCloud/getPosts", true);
		  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		 // user_id = 0;
		
		  categories = JSON.stringify(marked_categories);
		
		  xhttp.send("id="+user_id +"&type="+type+"&walltype="+walltype+"&searchTerm="+searchTerm+"&categories="+categories); // 0 anu homepage
		 
		
	}
	function getPostcheckboxes(walltype,type,searchTerm,categories){

		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {	
		    	last_search=searchTerm;
		    	postsT = xhttp.responseText;		    	
		    	var shadow = document.getElementById("postsDiv");
		    	shadow.innerHTML = postsT;
		    }
		  };
		  xhttp.open("POST", "http://localhost:8080/IdeaCloud/getPosts", true);
		  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		 // user_id = 0;
		  xhttp.send("id="+user_id +"&type="+type+"&walltype="+walltype+"&searchTerm="+searchTerm+"&categories="+categories); // 0 anu homepage
		 
		
	}
	function goOnProfile(){
		getPosts(1);
	}
	function initf(){

		document.getElementById("search_container").classList.toggle("show");
		var type=0;

		if (document.getElementById('i_box').checked && document.getElementById('q_box').checked){
			type = 2;
		}else if(document.getElementById('i_box').checked){
			type = 0;
		}else if(document.getElementById('q_box').checked){
			type = 1;
		}else{
			type =2;
		}

		getPosts(0,type,"");
	} 
	function searchit(){	
		document.getElementById("search_container").classList.toggle("show");
		var type=0;
		
		if (document.getElementById('i_box').checked && document.getElementById('q_box').checked){
			type = 2;
		}else if(document.getElementById('i_box').checked){
			type = 0;
		}else if(document.getElementById('q_box').checked){
			type = 1;
		}else{
			type =2;
		}
		searchTerm = document.getElementById('search_post_text').value;
		getPosts(0, type, searchTerm);
	}
	function reply_click(clicked_id)
	{
		var button = document.getElementById(clicked_id);
		if (marked_categories[clicked_id] == false){
			button.style.background='lightgreen';		
			marked_categories[clicked_id] = true;
		}else{
			button.style.background='#fff';		
			marked_categories[clicked_id] = false;
		}
	}
	function reply_post_click(clicked_id)
	{
		clk_id = parseInt(clicked_id.substring(1));
		var button = document.getElementById(clicked_id);
		if (post_marked_categories[clk_id ] == false){
			button.style.background='lightgreen';		
			post_marked_categories[clk_id ] = true;
		}else{
			button.style.background='#fff';		
			post_marked_categories[clk_id ] = false;
		}
	}
</script>
<style>

.usr{
	
}
</style>
</body>
</html>