package Core;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class fbLogin
 */
@WebServlet("/fbLogin")
public class fbLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fbLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		String mail = request.getParameter("mail");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String imgSrc = request.getParameter("imgSrc");
		System.out.println(mail);
		System.out.println(first_name);
		System.out.println(last_name);
		System.out.println(imgSrc);
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("fbMail");
		String first_name = request.getParameter("fbFirstName");
		String last_name = request.getParameter("fbLastName");
		String imgSrc = request.getParameter("imgSrc");
		Map<String, Object> map =  new HashMap<String, Object>();
		Connection con;
		try {
			con = Pool.getPool().getConnection();
			Database db = new Database(con);
			int id  = db.getUserId(mail);
			if (id == -1){
				User user = new User(-1, first_name, last_name, first_name, mail, "", -1, 0, -1, "", imgSrc);
				db.insertUser(user);
				id = db.getUserId(mail);
			}
			saveSession(request, id);
			map.put("id", id);
		} catch (SQLException | PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}
	
	private void saveSession(HttpServletRequest request, int id) {
		HttpSession ses = request.getSession(false);
		ses.setAttribute("user_id", id);
	}

}
