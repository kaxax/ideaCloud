package Core;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class notificationServer
 */
@WebServlet("/notificationServer")
public class notificationServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public notificationServer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		
		try {
			Connection con = Pool.getPool().getConnection();
			Database db = new Database(con);

			helper h = new helper();

			String mg = h.getPeers(name);
			
			if (mg.length() > 0) {
				
				PrintWriter out = response.getWriter();
				out.println(mg);
				out.close();
			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String second = request.getParameter("second");
		try {
			Connection con = Pool.getPool().getConnection();
			Database db = new Database(con);
			helper h = new helper();
			ArrayList<String> strList =h.getMessages(name, second);
			StringBuffer values = new StringBuffer();
			for (int i = 0; i < strList.size(); ++i) {
			    if (values.length() > 0) {
			        values.append(',');
			    }
			    values.append(strList.get(i));
			
			    
			}
			String mg = values.toString();
			
			if (mg.length() > 0) {
				PrintWriter out = response.getWriter();
				out.println(mg);
				out.close();
			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
