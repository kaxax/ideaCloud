package Core;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import Core.Database;
import Core.Pool;

/**
 * Servlet implementation class homePage
 */
@WebServlet("/homePage")
public class checkUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// TODO 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map =  new HashMap<String, Object>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int id;
		/*
		 * try {
			Connection con = Pool.getPool().getConnection();
			Database db = new Database(con);
			int id = db.getId(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
		id = 15; 
		if (id != -1){
			saveSession(request);
		}
		map.put("id", id);
		map.put("username", username);
		map.put("password", password);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
		
	}

	private void saveSession(HttpServletRequest request) {
		HttpSession ses = request.getSession(false);
		String username = null;
		try {
			username = (String) request.getParameter("username");
		}
		catch(Exception e){
		}
		if (username != null){
			String password = (String) request.getParameter("test");
			ses.setAttribute("username", username);
			ses.setAttribute("password", password);
		}
		
	}

}
