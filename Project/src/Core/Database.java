package Core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Core.Post;
import Core.Comment;
import Core.User;



public class Database {
	private Connection con;
	public Database(Connection conn){
		this.con = conn;
	}
	public void insertUser(User u){
	    
	    try {
	    	PreparedStatement stmt = con.prepareStatement("INSERT INTO user "
					+ "(nickname, name, surname, email, password, age, sex, level, moto, imgSrc) " 
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
	    	stmt.setString(1, u.getUSerNickname());
	    	stmt.setString(2, u.getUserName());
	    	stmt.setString(3, u.getUserSurname());
	    	stmt.setString(4, u.getUserEmail());
	    	stmt.setString(5, u.getUserPasswrod());
	    	stmt.setInt(6, u.getUSerAge());
		    stmt.setInt(7, u.getUserSex()) ;
		    stmt.setInt(8,u.getUserLevel());
		    stmt.setString(9, u.getUserMoto()) ;
		    stmt.setString(10, u.getUSerImgSrc());
		    stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
		
	
	public int addPost(Post p){
		try {
	    	PreparedStatement stmt = con.prepareStatement("INSERT INTO post "
					+ "(title, text, type, authorId, topic, clouds, unclouds) " 
					+ "values (?, ?, ?, ?, ?, ?, ?)");
	    	stmt.setString(1, p.getPostTitle());
	    	stmt.setString(2, p.getPostText());
	    	stmt.setInt(3, p.getPostType());
	    	stmt.setInt(4, p.getPostUSerId());
	    	stmt.setString(5, p.getPostTopic());
	    	stmt.setInt(6, p.getPostCloud());
		    stmt.setInt(7, p.getPostUncloud()) ;
		    stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//gogi yleo unda daabruno is id roml id zec am post chaamateb 
		return 0;
	}
	
	public Post getPost(int id){
		//TODO
		return null;
	}
	public User getUser(int id){
		//TODO
		return null;
	}
	public Comment getComment(int id){
		//TODO
		return null;
	}
	//ak unda amoigos userId-s mkone useris droit dalagebuli postebi
	//da makedan daabrunos start idexidan end indexamde mdgomi postebis id-ebis arrayListi
	//(to ideas == true ideebi sachiroa da questions  == true qyestionebic sachiroa)
	public ArrayList<Integer> getLatestProfilePosts(int userId, int start, int end, boolean ideas, boolean questions){
		//TODO
		return null;
	}
	//ak ubralod unda amoigos droit dalagebuli postebi (kvela postis masivida)
	//da makedan daabrunos start indexidan end indexamde racaa magati id-ebis arraylisti unda
	//(to ideas == true ideebi sachiroa da questions  == true qyestionebic sachiroa)
	public ArrayList<Integer> getLatestPosts(int start, int end, boolean ideas, boolean questions){
		//TODO
		return null;
	}
	
	
	public void addComment(Comment c){
		
		try {
	    	PreparedStatement stmt = con.prepareStatement("INSERT INTO comment "
					+ "(postId, authorId, text, clouds, unclouds) " 
					+ "values (?, ?, ?, ?, ?)");

	    	
	    	stmt.setInt(1, c.getCommentPostId());
	    	stmt.setInt(2, c.getCommentUSerId());
	    	stmt.setString(3, c.getCommentText());
	    	stmt.setInt(4, c.getCommentCloud());
	    	stmt.setInt(5, c.getCommentUncloud());
		    stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int getId(String email, String password){
		return 0;
	}

	public int getUserId(String email) {
		int result = -1;
		
		try {
			PreparedStatement stmt = con.prepareStatement("select * from user where email = ? ");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public void updateUser(User u) {
		try (PreparedStatement stmt = con
				.prepareStatement("update user set nickname = ?, name = ?, surname= ?, email = ?, password = ?, age = ?, sex = ?, level = ?, moto = ?, imgSrc = ? "
						+ "where id = ?")) {
			stmt.setString(1, u.getUSerNickname());
			stmt.setString(2, u.getUserName());
			stmt.setString(3, u.getUserSurname());
			stmt.setString(4, u.getUserEmail());
			stmt.setString(5, u.getUserPasswrod());
			stmt.setInt(6, u.getUSerAge());
			stmt.setInt(7, u.getUserSex());
			stmt.setInt(8, u.getUserLevel());
			stmt.setString(9, u.getUserMoto());
			stmt.setString(10, u.getUSerImgSrc());
			stmt.setInt(11, u.getUserId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void removeUser(User u){
		try {
			PreparedStatement stmt = con.prepareStatement("delete from user where id = ? ");
			stmt.setInt(1, u.getUserId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
