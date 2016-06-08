<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body onload="initf()">
	
<img src="smiley.gif" class = "profpic" onclick="goOnProfile()" alt="Smiley face" ">
<div class="dropdown">
  <button class="dropbtn" onclick="searchit()">search</button>
  <div class="dropdown-content">
  <textarea placeholder="This is an awesome comment box" rows="1" 
  name="comment[text]" id="comment_text" cols="1" class="ui-autocomplete-input" 
  autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true"></textarea>
    <a onclick="reply_click(this.id)" id = "1" href="#">computer science</a>
    <a onclick="reply_click(this.id)" id = "2" href="#">biology</a>
    <a onclick="reply_click(this.id)" id = "3" href="#">art</a>
    <a onclick="reply_click(this.id)" id = "4" href="#">engineering</a>
    <a onclick="reply_click(this.id)" id = "5" href="#">AI</a>
    <a onclick="reply_click(this.id)" id = "6" href="#">Novels</a>
    <a onclick="reply_click(this.id)" id = "7" href="#">social</a>
    <a onclick="reply_click(this.id)" id = "8" href="#">fight</a>
    <a onclick="reply_click(this.id)" id = "9" href="#">chemistry </a>
    <a onclick="reply_click(this.id)" id = "10" href="#">film</a>
    <a onclick="reply_click(this.id)" id = "11" href="#">poetry</a>    
  </div>
</div>
<script>
marked_categories = [false,false,false,false,false,false,false,false,false,false,false];
function getPosts(id,type){
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {	
	    	postsT = xhttp.responseText;	
	    	alert(PostsT);
	    }
	  };
	  xhttp.open("POST", "http://localhost:8080/ideaCloud/getPosts", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("id="+id +"&type="+type); // 0 anu homepage
}
function goOnProfile(){
	getPosts(0, 1);
	alert("semded");
}
function initf(){
	getPosts(0, 0);
}
function searchit(){	
	alert("ss");
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
</script>
</body>
</html>