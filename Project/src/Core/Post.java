package Core;

public class Post {
	
	private int postId;
	private String postTitle;
	private String postText;
	private int postUserId;
	private int postType;	//1-question	0-answer
	private String postTopic;
	private int postCloud;
	private int postUncloud;
	
	public Post( String postTitle, String postText, int postUserId, int postType,
			String postTopic, int postCloud, int postUncloud){
		
		this.postTitle=postTitle;
		this.postText=postText;
		this.postUserId=postUserId;
		this.postType=postType;
		this.postTopic=postTopic;
		this.postCloud=postCloud;
		this.postUncloud=postUncloud;
	}
	public void setId(int id){
		this.postId = id;
	}
	public int getPostId(){
		return this.postId;
	}
	public String getPostTitle(){
		return this.postTitle;
	}
	public String getPostText(){
		return this.postText;
	}
	public int getPostUSerId(){
		return this.postUserId;
	}
	public int getPostType(){
		return this.postType;
	}
	public String getPostTopic(){
		return this.postTopic;
	}
	public int getPostCloud(){
		return this.postCloud;
	}
	public void setPostCloud(int k){
		 this.postCloud = k;
	}
	public void setPostunCloud(int k){
		this.postUncloud =k;
	}
	public int getPostUncloud(){
		return this.postUncloud;
	}
}
