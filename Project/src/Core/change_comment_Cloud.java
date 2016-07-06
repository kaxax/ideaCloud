package Core;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class change_comment_Cloud
 */
@WebServlet("/change_comment_Cloud")
public class change_comment_Cloud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public change_comment_Cloud() {
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
		int userId = (int) request.getSession().getAttribute("user_id");
		int postId =  Integer.parseInt(request.getParameter("postId"));
		int result = Integer.parseInt(request.getParameter("result"));
		int result1 = Integer.parseInt(request.getParameter("result1"));
		int vote_down_result =0;
		int vote_up_result = 0;
		try{
			vote_down_result = Integer.parseInt(request.getParameter("votedown"));
		}catch(Exception e){
			vote_down_result = 0;
		}
		try{
			vote_up_result = Integer.parseInt(request.getParameter("voteup"));
		}catch(Exception e){
			vote_up_result = 0;
		}
		int rs = 0;
		if(result==-1){
			rs = 0;
		}else{
			rs = 1;
		}
		Pool pl;
		try {
		pl = Pool.getPool();
		Connection conn = pl.getConnection();
		Database db = new Database(conn);
		Comment cm = db.getComment(postId);
		cm.setCommentCloud(vote_up_result); 
		cm.setCommentUnCloud(vote_down_result);		
		db.updateComment(cm);
		if(result == 0){
			Cloud cl = db.getCommentCloud(postId);
			db.removeCommentCloud(cl);
			//if(result1==-1){
				
			//	cm.setCommentUnCloud(1);

	//		}else{
		//		cm.setCommentCloud(-1);
			//}
		}else{
			//if(result1==-1){
				//cm.setCommentUnCloud(-1);

//			}else{
	//			cm.setCommentCloud(1);
		//	}
			Cloud cl = db.getCommentCloud(postId);
			if (cl==null){
				Cloud newcl = new Cloud(postId,userId, rs);
				db.insertcommentCloud(newcl);
 				db.updateCommentCloud(newcl);
			}else{
				cl.setcloud(rs);
				db.updateCommentCloud(cl);
			}			
		}
		
		conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}