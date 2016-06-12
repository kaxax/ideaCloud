

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="homepage.css" />
</head>
<body onload="initf()">
	
<img src="smiley.gif" class = "profpic" onclick="goOnProfile()" alt="Smiley face" ">
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

<div class="post-text-area">
<select id="blue">
  <optgroup label="Choose I or Q">
    <option>Idea</option>
    <option>Question</option>
  </optgroup>
</select>

<div class="dropdown" id = "post_dropdown">
  <button class="dropbtn" id = "post_dropbtn" onclick="searchit()">Choose Categories</button>
  <div class="dropdown-content" id = "post_dropdown-content ">
    <a onclick="reply_click(this.id)" id = "p1" href="#">computer science</a>
    <a onclick="reply_click(this.id)" id = "p2" href="#">biology</a>
    <a onclick="reply_click(this.id)" id = "p3" href="#">art</a>
    <a onclick="reply_click(this.id)" id = "p4" href="#">engineering</a>
    <a onclick="reply_click(this.id)" id = "p5" href="#">AI</a>
    <a onclick="reply_click(this.id)" id = "p6" href="#">Novels</a>
    <a onclick="reply_click(this.id)" id = "p7" href="#">social</a>
    <a onclick="reply_click(this.id)" id = "p8" href="#">fight</a>
    <a onclick="reply_click(this.id)" id = "p9" href="#">chemistry </a>
    <a onclick="reply_click(this.id)" id = "p10" href="#">film</a>
    <a onclick="reply_click(this.id)" id = "p11" href="#">poetry</a>    
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
<select id="blue1" >
  <optgroup label="Choose I or Q">
    <option>Idea</option>
    <option>Question</option>
  </optgroup>
</select>
</div>

<div id ="postsDiv">
</div>

<script>
marked_categories = [false,false,false,false,false,false,false,false,false,false,false];
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

function getPosts(id,type){
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {	
	    	postsT = xhttp.responseText;	
	    	 var shadow = document.getElementById("postsDiv");
	    	 shadow.innerHTML = postsT;

	    }
	  };
	  xhttp.open("POST", "http://localhost:8080/IdeaCloud/getPosts", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("id="+id +"&type="+type); // 0 anu homepage
}
function goOnProfile(){
	getPosts(0, 1);
}
function initf(){

	 document.getElementById("search_container").classList.toggle("show");
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