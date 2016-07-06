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
 * Servlet implementation class ChatServer
 */
@WebServlet("/ChatServer")
public class ChatServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ChatServer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String message = request.getParameter("msg");
		String name = request.getParameter("name");
		String second = request.getParameter("second");

		Message m = new Message(name, second, message, 0);
		Notification n = new Notification(name, second, 0);

		try {

			Connection con = Pool.getPool().getConnection();

			Database db = new Database(con);
			if (db.wasNotification(name, second)) {
				db.newNotification(name, second);
			} else {
				db.insertNotification(n);
			}
			db.insertMessage(m);
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
		String message = "";
		String name = request.getParameter("sender");
		String second = request.getParameter("reciever");

		if (name != null && second != null) {
			Connection con = null;

			try {
				con = Pool.getPool().getConnection();
				Database db = new Database(con);
				if (db.peerNotification(name, second)) {
					db.notificationSeen(name, second);
					ArrayList<String> a = db.checkMessage(name, second);
					if (a.size() > 0)
						db.seen(name, second);
					if (con != null)
						try {
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					if (a.size() > 0) {
						String mg = "";
						for (int i = 0; i <a.size() ; i++) {
							message = a.get(i);
							mg += "<div class=\"chatmsg\"><b>" + name + "</b>: " + message + "<br/></div>";
						}

						PrintWriter out = response.getWriter();
						out.println(mg);
						out.close();
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				if (con != null)
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}

		}
	}

}
