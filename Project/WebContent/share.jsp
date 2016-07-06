<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<link rel="stylesheet" type="text/css" href="share.css" />
   <title>share </title>	
</head>
<body onload="init()">

<script>
var txt="<%= request.getParameter("text") %>";
var titl="<%= request.getParameter("title") %>";
function init(){
	var title_area = document.getElementById("p_ttl");
	var text_area = document.getElementById("p_txt");
	title_area.value =titl;
	text_area.value = txt;
}
post_marked_categories = [false,false,false,false,false,false,false,false,false,false,false];


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
function closeit(){
	var daddy = window.self;
	daddy.opener = window.self;
	daddy.close();	
}
function addPosts(title,text)
{
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	   
		  if (xhttp.readyState == 4 && xhttp.status == 200) {	  
			postsT = xhttp.responseText;	
	  		closeit();
	    }
	  };
	  xhttp.open("POST", "http://localhost:8080/IdeaCloud/addPosts", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  var type = "0";
	  topic = JSON.stringify(post_marked_categories);
	  xhttp.send("&title="+title +"&text="+text+"&type="+type+"&topic="+topic); 
	   
}
function post_it(){
	var title = document.getElementById("p_ttl").value;
	var text = document.getElementById("p_txt").value;
	addPosts(title, text);
}
</script>
<div>
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
<div id="title-text">white your title here</div>
<textarea rows="2" cols="50"  id = "p_ttl"></textarea>
<p id="text-text">white your text here</p>
<textarea rows="6" cols="50"  id = "p_txt"></textarea>
<button id="btn-ok" onclick="post_it()">OK</button>
<button id="btn-c" onclick="closeit()">Cansel</button>
</div>
</body>
</html>