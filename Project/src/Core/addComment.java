package Core;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Core.Comment;
/**
 * Servlet implementation class addComment
 */
@WebServlet("/addComment")
public class addComment extends HttpServlet {
	

	private String cwd = "D:\\gela\\freeuni\\oop\\git-repo\\ideaCloud\\Project";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addComment() {
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
		int user_id = 0;
		if(request.getSession().getAttribute("user_id") != null){
			user_id = (int) request.getSession().getAttribute("user_id");
		}else{
			PrintWriter out = response.getWriter();
			out.append("");
			out.close();
			return;
		}
		Pool pl;
		Connection conn;
		Post post;
		User user;
		try {
			pl = Pool.getPool();
			conn = pl.getConnection();
			Database db = new Database(conn);	
			
			int postId = 0;
			int postid = Integer.parseInt(request.getParameter("post_id"));
			String commentText = request.getParameter("text");
			int commentCloud = 0;
			int commentUncloud = 0;
			Comment cmt = new Comment(postid, user_id, 0, 0, commentText);
			db.addComment(cmt);
			String template = getHtml(cwd+"\\WebContent\\commentTamplate.html");
			
			User usr = db.getUser(user_id);
			String result = generate_comment_template(template, cmt, usr);
			PrintWriter out = response.getWriter();
			out.append(result);
			out.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	private static String generate_comment_template(String template1, Comment comment, User user){
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
