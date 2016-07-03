package Core;

public class Cloud {
	private int id;
	private int userId;
	private int postId;
	private int cloud;
	public Cloud(int postId, int userId, int cloud){
		this.postId = postId;
		this.userId = userId;
		this.cloud = cloud;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public int getUserId(){
		return userId;
	}
	public int getPostId(){
		return postId;
	}
	public void setcloud(int ccl){
		cloud = ccl;
	}
	public int getcloud(){
		return cloud;
	}
	public int status(){
		return cloud;
	}
	
}
