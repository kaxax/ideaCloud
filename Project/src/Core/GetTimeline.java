package Core;

import java.util.ArrayList;

import javafx.util.Pair;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.lang.Math;

import Core.Pool;
import Core.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class GetTimeline {

	
	
	public ArrayList<Integer> getPosts(int start, int end, boolean status, int userId, int wallType, boolean questions, boolean ideas, ArrayList<String> categories, String searchTerm){
		
		if (status){
			return getHomePagePosts(questions, ideas, categories, searchTerm);
		}
		
		else {
			return getStandartTimeline(userId, start, end, wallType, ideas, questions);	
		}
	}
	
	
	
	private ArrayList<Integer> getStandartTimeline(int userId, int start, int end, int wallType, boolean ideas, boolean questions){
		Pool pl;
		try {
			pl = Pool.getPool();
			Connection conn = pl.getConnection();
			Database db = new Database(conn);
			
			ArrayList<Integer> tmp;
			if(wallType == 0){
				tmp = db.getLatestPosts(start, end, ideas, questions);
				conn.close();
				return tmp;
			}
			else{
				tmp = db.getLatestProfilePosts(userId, start, end, ideas, questions);
				conn.close();
				return tmp;
			}
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	private ArrayList<Integer> getHomePagePosts(boolean questions, boolean ideas, ArrayList<String> categories, String searchTerm){
		ArrayList< Pair<Integer, Boolean> > postIds = new ArrayList< Pair<Integer, Boolean> >();
		try {
			Pool pl = Pool.getPool();
			Connection conn = pl.getConnection();
			Database db = new Database(conn);
			int q = 0, i = 0;
			if(questions)
				q = 1;	
			if(ideas)
				i = 1;
			ArrayList<Post> posts = db.getCategoryPosts(categories, i, q);
			conn.close();
			for(int j = 0; j < posts.size(); j++){
				postIds.add(posts.get(j).getPostId());
			}
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return postIds;
		
	}
	
	private ArrayList<Integer> sortPosts(ArrayList<Pair<Integer, Double>> posts){
		
		return null;
	}
	
	
	
	
}
