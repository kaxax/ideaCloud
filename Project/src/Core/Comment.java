package Core;

public class Comment {
	
	private int commentId;
	private int commentPostId;
	private int commentUserId;
	private int commentCloud;
	private int commentUncloud;
	private String commentText;
	
	public Comment(int commentId, int commentPostId , int commentUserId, int commentCloud,
			int commentUncloud, String commentText){
		this.commentId=commentId;
		this.commentPostId=commentPostId;
		this.commentUserId=commentUserId;
		this.commentCloud=commentCloud;
		this.commentUncloud=commentUncloud;
		this.commentText=commentText;
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
	public int getCommentUncloud(){
		return this.commentUncloud;
	}
	public String getCommentText(){
		return this.commentText;
	}
	
	
}
