<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="samplePost.css">
<title>sample post</title>
</head>
<body>
	<div id = "user_info_div">
		<a href="http://localhost:8080/IdeaCloud/hereWeGo"> <img src="img.jpg" id = "user_img" width="100px"> </a>
		
		<div class = "user_text_div">
		<a href="http://localhost:8080/IdeaCloud/hereWeGo" id = "post_author_link">
		nickname
		</a>
		</div>
		<div class = "user_text_div">
		age: 69
		</div>
		<div class = "user_text_div">
		lvl: 12
		</div>
	</div>
	<div id = "post_div">
	
			<div class="type">
				<div calss = "QA">
					<img src="Q.jpg" height="50" width="50"/>
				</div>
			</div>
			
			<div id = "post_title_div">
				this is a sample title
			</div>
			<div id = "post_text_div">
			<br>
			
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok
				<br>
				<br>
			</div>
			
			<div class="meta_info">
				<div class="votes upvote">
					<div class="vote_name"> 
						<div class="arrow" id = "up">
							<img  src="up-arrow.png" id = "up-arrow">
						</div>
					</div>
					<div class="vote-count"> 
						98 
					</div>
				</div>
				<div class="votes downvote">
					<div class="vote_name" > 
						<div class="arrow" id = "down">
							<img  src="down-arrow.png" id = "down-arrow">
						</div> 
					</div>
					<div class="vote-count">22</div>
				</div>
				<div class="share">
					<a href = "http://share-now">
						<div class="vote-name"> 
							<img  src="share-now.png" id = "iamge-share">
						</div>
						</a>
				</div>
				
				<div class="share">
					<a href = "http://share">
						<div class="vote-name">
							<img  src="share-later.png" id = "iamge-share">
						</div>
						</a>
				</div>
			</div>
		</div>
		
		
		
		<div id = "comments">
			
			<div class = "comment">
				<div class="author">
					
					<div class="image" id = "user_id_::user_id::" onclick="goToUser(self.id)"> 
						<a  class = "link"><img src="::img-src::" height="80" width="80"/></a>
					</div>
					
					<a  class = "link"><div class="user-info" id = "user_id_::user_id::" onclick="goToUser(self.id)"> ::user-name:: </div></a>
					
					<div class="user-info" >
						lvl: ::lvl:: 
					</div>
				</div>
				
				<div class = "comment_text_div">
					this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok 
				this is a sample post and it should be big so I'm just writing it dow like this, hope it's ok
				</div>
				
			<div class="comment_meta_info">
				<div class="comment_votes upvote">
					<div class="comment_vote_name"> 
						<div class="comment_arrow" id = "up">
							<img  src="up-arrow.png" style = "width: 100%; height: 100%;" id = "up-arrow_::post_id::" onclick="voteUp(this.id)">
						</div>
					</div>
					<div class="comment_vote-count"> 
						::vote-up::
					</div>
				</div>
				<div class="comment_votes downvote">
					<div class="comment_vote_name" > 
						<div class="comment_arrow" id = "down">
							<img  src="down-arrow.png" style = "width: 100%; height: 100%;"  id = "down-arrow_::post_id::" onclick="voteDown(this.id)">
						</div> 
					</div>
					<div class="comment_vote-count">
						::vote-down::
					</div>
				</div>
				<div class="comment_share">
					<a href = "http://share-now">
						<div class="comment_vote-name"> 
							<img  src="share-now.png" id = "iamge-share">
						</div>
						</a>
				</div>
				
				<div class="comment_share">
					<a href = "http://share">
						<div class="comment_vote-name">
							<img  src="share-later.png" id = "iamge-share">
						</div>
						</a>
				</div>
			</div>
				
			</div>
		
		</div>
 		<div style="height: 500px;"></div>

</body>
</html>