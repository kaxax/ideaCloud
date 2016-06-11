package Core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class changeInfo
 */
@WebServlet("/changeInfo")
public class changeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String page = getHtml("D:\\gela\\freeuni\\oop\\git-repo\\ideaCloud\\Project\\WebContent\\changeInfo.jsp");
		User user = new User(1, "Gela", "Magaltadze", "tvtgela", "ragaca", "ragaca", 21, 12, 1 , "avoee", "img.jpg");
		String result = fill_form(page, user);
		out.println(result);
		out.close();
		
		
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	
	private String fill_form(String template1, User user){
		String template = template1;
		String tmp1;
		tmp1=template.replace("::name::", user.getUserName());
		template=tmp1.replace("::surname::", user.getUserSurname());
		tmp1=template.replace("::username::", user.getUSerNickname());
		template=tmp1.replace("::email::", user.getUserEmail());

		return template;
	}
	
	
	

}
