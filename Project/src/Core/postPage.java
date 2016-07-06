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

import com.mysql.fabric.xmlrpc.base.Data;

import java.sql.Connection;








import java.util.ArrayList;

import Core.Pool;
import Core.Post;
import Core.User;
import Core.Comment;


/**
 * Servlet implementation class postPage
 */
@WebServlet("/postPage")
public class postPage extends HttpServlet {
	

	private String cwd = "D:\\oop\\cl\\ideaCloud\\Project";
	
	
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
		int post_id = 0;
		String  iddd = request.getParameter("post_id");
		String postHtml = getHtml(cwd+"\\WebContent\\wholePostTamplate.html");
		post_id = Integer.parseInt(iddd);
		int user_id = (int) request.getSession().getAttribute("user_id");
		try {
			Pool pl = Pool.getPool();
			Connection conn = pl.getConnection();
			Database db = new Database(conn);
			Post post = db.getPost(post_id);
			User user = db.getUser(post.getPostUSerId());
			Cloud cld = db.getCloudByIds(user_id, post.getPostId());
			String result = generate_post_template(postHtml, post, user,cld);
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
		
		if (request.getSession().getAttribute("user_id") == null){
			return;
		}

		int post_id = Integer.parseInt(request.getParameter("post_id"));
		int start = Integer.parseInt( request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
		String commentHtml = getHtml(cwd+"\\WebContent\\commentTamplate.html"); 
		try {
			Pool pl = Pool.getPool();
			Connection conn = pl.getConnection();
			Database db = new Database(conn);
			
			ArrayList<Comment> comments = db.getCommentByIds(post_id, start, end);
			String result = "";
			for (int i=0;i<comments.size();i++){
				Comment comment = comments.get(i);
				User user = db.getUser(comment.getCommentUSerId());
				Cloud cl = db.getCommentCloud(comment.getCommentId());
				result = result + generate_comment_template(commentHtml, comment, user,cl);
			}
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
	
	
	private static String generate_post_template(String template1, Post post, User user, Cloud cll){
		String up_color = "";
		String down_color = "";
		if(cll != null){
			if(cll.getcloud() == 0){
				down_color = "red";
				up_color = "";
			}else{
				down_color = "";
				up_color = "blue";
			}
		}
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
		template=tmp1.replace("::up_color::", up_color);
		tmp1=template.replace("::down_color::",down_color);
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
		return tmp1;
	}
	
	
	private static String generate_comment_template(String template1, Comment comment, User user,Cloud cll){
		String up_color = "";
		String down_color = "";
		if(cll != null){
			if(cll.getcloud() == 0){
				down_color = "red";
				up_color = "";
			}else{
				down_color = "";
				up_color = "blue";
			}
		}
		String template = template1;
		String tmp1;
		if (user.getUSerImgSrc().length()>0){
			tmp1=template.replace("::img-src::", user.getUSerImgSrc());
		}
		else{
			tmp1=template.replace("::img-src::", "unknown.png");
		}
		template=tmp1.replace("::comment_id::", Integer.toString(comment.getCommentId()));
		tmp1=template.replace("::user_id::", Integer.toString(user.getUserId()));
		template=tmp1.replace("::user-name::", user.getUSerNickname());
		tmp1=template.replace("::comment_text::", comment.getCommentText());
		template=tmp1.replace("::cup_color::", up_color);
		tmp1=template.replace("::cdown_color::",down_color);
		template=tmp1.replace("::comment_cloud::", Integer.toString(comment.getCommentCloud()));
		tmp1=template.replace("::comment_uncloud::", Integer.toString(comment.getCommentUncloud()));
		template = tmp1.replace("::lvl:: ", Integer.toString(user.getUserLevel()));
		
		return template;
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
