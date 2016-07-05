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
 * Servlet implementation class changeCloud
 */
@WebServlet("/changeCloud")
public class changeCloud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeCloud() {
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
		Post post = db.getPost(postId);
		
		if(result == 0){
			
			Cloud cl = db.getCloudByIds(userId, postId);

			db.removeCloud(cl);
			if(result1==-1){
				post.setPostunCloud(1);
			}else{
				post.setPostCloud(-1);
			}
			
		}else{
			if(result1==-1){
				
				post.setPostunCloud(-1);
			}else{
				
				post.setPostCloud(1);
			}
			Cloud cl = db.getCloudByIds(userId, postId);
			if (cl==null){
				Cloud newcl = new Cloud(postId, userId, rs);
				db.insertCloud(newcl);
				db.updateCloud(newcl);
			}else{
				cl.setcloud(rs);
				db.updateCloud(cl);
			}			
		}
		db.updatePost(post);
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
