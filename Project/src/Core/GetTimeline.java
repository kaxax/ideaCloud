package Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.collections.transformation.SortedList;
import javafx.util.Pair;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.lang.Math;

import Core.Pool;
import Core.Database;

import java.sql.Connection;
import java.sql.SQLException;

import com.sun.org.apache.xpath.internal.operations.Bool;

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
		ArrayList< Pair<Integer, Double> > postIds = new ArrayList< Pair<Integer, Double> >();
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
			System.out.println("postsLen:\t"+posts.size());
			conn.close();
			
			for(int j = 0; j < posts.size(); j++){
				Pair<Integer, Double> tmp = new Pair<Integer, Double>(posts.get(j).getPostId(), 0.0);
				postIds.add(tmp);
			}
			String[] terms = searchTerm.split(" ");
			for(int j = 0; j < terms.length; j++){
				postIds = applyTFIDF(postIds, terms[j], posts);
			}
			return sortPosts(postIds);
			
			
		
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
	
	private ArrayList< Pair<Integer, Double> > applyTFIDF(ArrayList< Pair<Integer,Double> > postIds, String term, ArrayList<Post> posts){
		
		Double idf = callIDF(term, posts);
		for(int i = 0; i < posts.size(); i++){
			Pair<Integer, Double> tmp = new Pair(postIds.get(i).getKey(), callTF(posts.get(i), term)*idf);
			postIds.set(i, tmp);
		}
		System.out.println("idf:\t"+idf);
		
		return postIds;
	}
	
	private Double callTF(Post post, String term){
		Double answer = 0.0;
		String lower = term.toLowerCase();
		String[] title = post.getPostTitle().split(" ");
		String[] text = post.getPostText().split(" ");
		
		for(int i = 0; i < title.length; i++){
			if (title[i].toLowerCase().equals(lower))
				answer += 1;
		}
		
		for(int i = 0; i < text.length; i++){
			if (text[i].toLowerCase().equals(lower))
				answer += 1;
		}
		return answer;
	}
	
	private Double callIDF(String term, ArrayList<Post> posts){
		
		
		Double num = 0.0;
		Double answer = 0.0;
		for(int i = 0; i < posts.size(); i++){	
			if(isInPost(posts.get(i), term))
				num += 1;
		}
		Double numForLog = 1.0;
		if(num > 0)
			numForLog = posts.size()/num;
		return Math.log(numForLog);
	}
	
	
	private boolean isInPost(Post post, String term){
		
		String title = post.getPostTitle().toLowerCase();
		String text = post.getPostText().toLowerCase();
		String lower = term.toLowerCase();
		
		if(title.contains(lower) || text.contains(lower)){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	private ArrayList<Integer> sortPosts(ArrayList<Pair<Integer, Double>> posts){
		
		ArrayList<Integer> answer = new ArrayList<Integer>();
		while(posts.size()>0){
			int maxInd = getMaxInd(posts);
			answer.add(posts.get(maxInd).getKey());
			posts.remove(maxInd);
		}
		
		return answer;
	}
	
	private int getMaxInd(ArrayList <Pair<Integer, Double>> posts){
		
		int maxInd = 0;
		Double maxvalue = 0.0;
		for(int i = 0; i < posts.size(); i++){
			if (posts.get(i).getValue() > maxvalue){
				maxvalue = posts.get(i).getValue();
				maxInd = i;
			}
		}
		return maxInd;
	}
	
	
	
}
