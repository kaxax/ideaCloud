package Core;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;






import Core.Pool;
import Core.Post;
import Core.User;
import Core.Comment;


/**
 * Servlet implementation class postPage
 */
@WebServlet("/postPage")
public class postPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public postPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String postHtml = getHtml("D:\\gela\\freeuni\\oop\\git-repo\\ideaCloud\\Project\\WebContent\\wholePostTamplate.html");
		int post_id = Integer.parseInt((String) request.getAttribute("post_id"));
		try {
			Pool pl = Pool.getPool();
			Connection conn = pl.getConnection();
			Database db = new Database(conn);
			Post post = db.getPost(post_id);
			User user = db.getUser(post.getPostUSerId());
			String result = generate_post_template(postHtml, post, user);
			PrintWriter out = response.getWriter();
			out.append(result);
			out.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	private static String generate_post_template(String template1, Post post, User user){
		String template = template1;
		String tmp1;
		if (user.getUSerImgSrc().length()>0){
			tmp1=template.replace("::user_img::", user.getUSerImgSrc());
		}
		else{
			tmp1=template.replace("::user_img::", "unknown.png");
		}
		template=tmp1.replace("::post_user_nickname::", user.getUSerNickname());
		tmp1=template.replace("::post_user_lvl::", Integer.toString(user.getUserLevel()));
		template=tmp1.replace("::post_title::", post.getPostTitle());
		tmp1=template.replace("::post_text::", post.getPostText());
		template=tmp1.replace("::post_cloud::", Integer.toString(post.getPostCloud()));
		tmp1=template.replace("::post_uncloud::", Integer.toString(post.getPostUncloud()));
		template = tmp1.replace("::user_id::", Integer.toString(user.getUserId()));
		tmp1 = template.replace("::post_id::", Integer.toString(post.getPostId()));
		if (post.getPostType()==1){
			template=tmp1.replace("::post_type::", "Q.jpg");
		}
		else{
			template=tmp1.replace("::post_type::", "A.jpg");
		}
		tmp1 = template.replace("::post_user_age::", Integer.toString(user.getUSerAge()));
		System.out.println("post type:\t"+post.getPostType());
		return tmp1;
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
	
	
	

}
