<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style type="text/css">
		.post {
			width: 500px;
			height: 200px;
			padding: 15px;
			border: 1px solid black;
			position: relative;
		}
		.author {
			float: left;
			width: 100px;
			height: 100px;
		}
		.type {
			float: right;
			width: 30px;
			height: 30px;
		}
		.image {
			width: 100px;
			height: 100px;
			
		}
		.author_name, .date {
			width: 100px;
			height: 25px;
			line-height: 25px;
		}
		.body {
			position: absolute;
			width: 300px;
			height: 150px;
			left: 150px;
		}
		.meta_info {
			position: absolute;
			left: 20px;
			top: 180px;
		}
		.votes {
			float: left;
			margin-right: 20px;
		}
		.share{
		float: right;
		margin-right: 20px;
		font-size: 120%;
		}
		</style>
	</head>
	<body>
		[:post_item:]
		<div class="post">
			<div class="author">
				<div class="image"> 
					<img src="img.jpg" height="100" width="100"/>
				</div>
				<div class="author_name"> {{:username:}} </div>
				<div class="date">lvl: {{:lvl:}} </div>
			</div>
			<div class="body">
				<div class = "title">{{:title:}}</div>
				<p></p>
				<div lass = "text">{{:text:}}</div>
			</div>
			<div class="type">
				<div calss = "QA">
					<img src="Q.jpg" height="30" width="30"/>
				</div>
			</div>
			<div class="meta_info">
				<div class="votes upvote">
					<div class="vote_name"> UP </div>
					<div class="vote_count"> 357 </div>
				</div>
				<div class="votes downvote">
					<div class="vote_name"> DOWN </div>
					<div class="vote_count">    22 </div>
				</div>
				<div class="share now">
					<a href = "http://share-now">
						<div class="vote_name"> share now </div>
						</a>
				</div>
				
				<div class="share later">
					<a href = "http://share">
						<div class="vote_name"> share  </div>
						</a>
				</div>
			</div>
		</div>
		[:post_item:]
	</body>
</html>