package Core;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Core.Pool;
import java.sql.Connection;
import Core.Database;
import Core.User;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\" />");
		out.println("<title>username</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>A your username is: \t    "+username+" and your password is:\t  "+password+"</h1>");
		out.println("</body>");
		out.println("</html>");
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String username = request.getParameter("username");
		String mail = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordAgain = request.getParameter("again");
		int errorCase = 0;
		boolean everythingFilled = (!name.equals("") && !surname.equals("") && !username.equals("") && !mail.equals("") && !password.equals("") && !passwordAgain.equals(""));
		boolean passwordSame = password.equals(passwordAgain);
		boolean mailFree = true;
		try {
			Pool pl = Pool.getPool();
			Connection conn = pl.getConnection();
			Database db = new Database(conn);
			if(db.getUserId(mail)!=-1){
				mailFree = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(!everythingFilled && !passwordSame && !mailFree){
			errorCase = 123;
		}
		else if(!everythingFilled && !passwordSame){
			errorCase = 12;
		}
		else if(!passwordSame && !mailFree){
			errorCase = 23;
		}
		else if(!everythingFilled  && !mailFree){
			errorCase = 13;
		}
		else if(!everythingFilled  ){
			errorCase = 1;
		}
		else if(!passwordSame){
			errorCase = 2;
		}
		else if(!mailFree){
			errorCase = 3;
		}
		if (errorCase == 0){
			User user = new User(-1, name, surname, username, mail, password, -1, 0, -1, "", "");
			try {
				
				Pool pl =Pool.getPool();
				Connection conn = pl.getConnection();
				Database db = new Database(conn);
				db.insertUser(user);
				System.out.println("aaaaaeeee");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorCase", errorCase);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}

}
