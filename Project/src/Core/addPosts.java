package Core;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Core.Database;
import Core.Pool;
import Core.Post;

import java.sql.Connection;
import java.sql.SQLException;

import Core.User;
import Core.getPosts;

/**
 * Servlet implementation class addPosts
 */
@WebServlet("/addPosts")
public class addPosts extends HttpServlet {
	
	private String cwd = "D:\\gela\\freeuni\\oop\\git-repo\\ideaCloud\\Project";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addPosts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Pool pl;
		Connection conn;
		Post post;
		User user;
		try {
			pl = Pool.getPool();
			conn = pl.getConnection();
			Database db = new Database(conn);	
			
			int postId = 0;
			String postTitle = request.getParameter("title");
			String postText = request.getParameter("text");
			int postUserId = Integer.parseInt(request.getParameter("userId"));
			int postType = Integer.parseInt(request.getParameter("type"));
			String postTopic = request.getParameter("topic");
			int postCloud = 0;
			int postUncloud = 0;
			
			
			post = new Post( postTitle, postText, postUserId, postType, postTopic, postCloud, postUncloud);
			post.setId(postId);
			db.addPost(post);
			user = db.getUser(postUserId);
			
			
			
			String style = getHtml(cwd+"\\WebContent\\postStyle.html");
			String template = getHtml(cwd+"\\WebContent\\postTamplate.html");
			
			String result = generate_template(template, post, user);
			
			PrintWriter out = response.getWriter();
			out.append(result+style);
			out.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private String getHtml(String file){
		StringBuilder contentBuilder = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new FileReader(file));
		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str);
		    }
		    in.close();
		} catch (IOException e) {
		}
		String content = contentBuilder.toString();
		return content;
	}
	private static String generate_template(String template1, Post post, User user){
		String template = template1;
		String tmp1;
		if (user.getUSerImgSrc().length()>0){
			tmp1=template.replace("::img-src::", user.getUSerImgSrc());
		}
		else{
			tmp1=template.replace("::img-src::", "unknown.png");
		}
		template=tmp1.replace("::user-name::", user.getUSerNickname());
		tmp1=template.replace("::lvl::", Integer.toString(user.getUserLevel()));
		template=tmp1.replace("::post-title::", post.getPostTitle());
		tmp1=template.replace("::post-text::", post.getPostText());
		template=tmp1.replace("::vote-up::", Integer.toString(post.getPostCloud()));
		tmp1=template.replace("::vote-down::", Integer.toString(post.getPostUncloud()));
		template = tmp1.replace("::user_id::", Integer.toString(user.getUserId()));
		tmp1 = template.replace("::post_id::", Integer.toString(post.getPostId()));
		if (post.getPostType()==1){
			template=tmp1.replace("::post_type_img::", "Q.jpg");
		}
		else{
			template=tmp1.replace("::post_type_img::", "A.jpg");
		}
		return template;
	}
	
	

}
