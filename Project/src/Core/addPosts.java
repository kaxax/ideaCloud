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
			pl = new Pool();
			conn = pl.getConnection();
			Database db = new Database(conn);	
			
			int postId = Integer.parseInt(request.getParameter("postId"));
			String postTitle = request.getParameter("title");
			String postText = request.getParameter("text");
			int postUserId = Integer.parseInt(request.getParameter("userId"));
			int postType = Integer.parseInt(request.getParameter("type"));
			String postTopic = request.getParameter("topic");
			int postCloud = Integer.parseInt(request.getParameter("cloud"));
			int postUncloud = Integer.parseInt(request.getParameter("uncloud"));
			
			post = new Post(postId, postTitle, postText, postUserId, postType, postTopic, postCloud, postUncloud);
			db.addPost(post);
			user = db.getUser(postUserId);
			
			//tmp user is to test the servlet before corresponding method in Database.java is imlemented
			
			User tmpUser = new User(1, "Gela", "Magaltadze", "tvtgela", "ragaca", "ragaca", 21, 12, 1 , "avoee", "img.jpg");
			
			String style = getHtml("D:\\gela\\freeuni\\oop\\git-repo\\ideaCloud\\Project\\WebContent\\postStyle.html");
			String template = getHtml("D:\\gela\\freeuni\\oop\\git-repo\\ideaCloud\\Project\\WebContent\\postTamplate.html");
			
			String result = generate_template(template, post, tmpUser);
			
			PrintWriter out = response.getWriter();
			out.append(result);
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
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
		tmp1=template.replace("::img-src::", user.getUSerImgSrc());
		template=tmp1.replace("::user-name::", user.getUSerNickname());
		tmp1=template.replace("::lvl::", Integer.toString(user.getUserLevel()));
		template=tmp1.replace("::post-title::", post.getPostTitle());
		tmp1=template.replace("::post-text::", post.getPostText());
		template=tmp1.replace("::vote-up::", Integer.toString(post.getPostCloud()));
		tmp1=template.replace("::vote-down::", Integer.toString(post.getPostUncloud()));
		return tmp1;
	}
	
	

}
