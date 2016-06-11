package Core;

import java.util.ArrayList;

import javafx.util.Pair;
import java.lang.Math;
public class GetTimeline {

	
	
	public ArrayList<Integer> getPosts(int wallTyep, boolean questions, boolean ideas, ArrayList<String> categories, String searchTerm){
		if (wallTyep == 0){
			return getHomePagePosts(questions, ideas, categories, searchTerm);
		}
		else{
			return getProfilePosts(questions, ideas, categories, searchTerm);
		}
	}
	private Pair<Double, Double> getPostCount(ArrayList<String> categories, boolean questions, boolean ideas, String word){
		//mocemuli categoriebis da tipis mixedvit unda daabrunos ramden postshi gvxvdeba String word-i 
		return null;
	}
	
	private double getWordCount(int id, String word){
		//ak bazashi shenaxuli unda gvkondes koveli postistvis ra sitkvebi gvxvdeba ik da ramdenjer. tu ar gvxvdeba sitkva <id, 0> unda daabrunos
		return 0;
	}
	private ArrayList<Pair<Integer, Double>> getPosts(ArrayList<String> categories, boolean questions, boolean ideas){
		//ak abruneb kvela posts romelic mocemul categoriebs da tipebs akmakopilebs
		//mchirdeba pair-ebis masivis sadac pirveli elementi ikneba postis id da meore ikneba 0 (imena 0)
		return null;
	}
	
	private ArrayList<Integer> sortPosts(ArrayList<Pair<Integer, Double>> posts){
		return null;
	}
	
	
	private ArrayList<Integer> getHomePagePosts(boolean questions, boolean ideas, ArrayList<String> categories, String searchTerm){
		
		return null;
	}
	
	
	
	private ArrayList<Integer> getProfilePosts(boolean questions, boolean ideas, ArrayList<String> categories, String searchTerm){
		String[] words = searchTerm.split(" ");
		ArrayList<Pair<Integer, Double>> posts = getPosts(categories, questions, ideas);
		for (int i=0; i<words.length; i++){
			
			double idf = java.lang.Math.log(   getPostCount(categories, questions, ideas, words[i]).getKey()/getPostCount(categories, questions, ideas, words[i]).getValue()  ) /  java.lang.Math.log(2)  ;
			
			for(int j=0; j<posts.size();j++){
				Pair<Integer, Double> tmp = new Pair<Integer, Double>(posts.get(j).getKey(), (posts.get(j).getValue()+ getWordCount(posts.get(j).getKey(), words[i])/idf ));
				posts.set(j, tmp);
			}
		}
		
		
		return sortPosts(posts);
	}
	
	
	
	
}
