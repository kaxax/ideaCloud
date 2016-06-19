package Core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
	
		
	
	public void addPost(Post p){
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
}
