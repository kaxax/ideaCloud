package Core;

public class Comment {
	
	private int commentId;
	private int commentPostId;
	private int commentUserId;
	private int commentCloud;
	private int commentUncloud;
	private String commentText;
	
	public Comment( int commentPostId , int commentUserId, int commentCloud,
			int commentUncloud, String commentText){
		
		this.commentPostId=commentPostId;
		this.commentUserId=commentUserId;
		this.commentCloud=commentCloud;
		this.commentUncloud=commentUncloud;
		this.commentText=commentText;
	}
	public void setId(int id){
		this.commentId = id;
	}
	public int getCommentId(){
		return this.commentId;
	}
	public int getCommentPostId(){
		return this.commentPostId;
	}
	public int getCommentUSerId(){
		return this.commentUserId;
	}
	public int getCommentCloud(){
		return this.commentCloud;
	}
	public void setCommentCloud(int k){
		this.commentCloud =k ;
	}
	public void setCommentUnCloud(int k){
		this.commentUncloud =k ;
	}
	public int getCommentUncloud(){
		return this.commentUncloud;
	}
	public String getCommentText(){
		return this.commentText;
	}

	
}
