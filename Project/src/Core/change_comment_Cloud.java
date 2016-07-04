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
		if(result == 0){
			Cloud cl = db.getCloudByIds(userId, postId);
			db.removeCloud(cl);
			if(result1==-1){
				
				cm.setCommentUnCloud(1);

			}else{
				cm.setCommentCloud(-1);
			}
		}else{
			if(result1==-1){
				cm.setCommentUnCloud(-1);

			}else{
				cm.setCommentCloud(1);
			}
		
			Cloud cl = db.getCloudByIds(userId, postId);
			if (cl==null){
				Cloud newcl = new Cloud(userId, postId, rs);
				db.insertCloud(newcl);
			}else{
				cl.setcloud(rs);
				db.updateCloud(cl);
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