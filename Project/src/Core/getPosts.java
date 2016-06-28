package Core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import Core.Post;
import Core.User;

/**
 * Servlet implementation class getPosts
 */
@WebServlet("/getPosts")
public class getPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPosts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//int id = Integer.parseInt(request.getParameter("id"));
		//int type = Integer.parseInt(request.getParameter("type"));
		//int start = Integer.parseInt(request.getParameter("start"));
		//int end = Integer.parseInt(request.getParameter("end"));
		
		
		
		
		
		
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		ArrayList<Post> posts = new ArrayList<Post>();
		Post tmp = new Post( "musha posti", "es aris musha posti, romelic dagenerirebulia html-is chascorebit", 1, 0, "science", 22, 11);
		tmp.setId(12);
		posts.add(tmp);
		User user = new User(1, "Gela", "Magaltadze", "tvtgela", "ragaca", "ragaca", 21, 12, 1 , "avoee", "img.jpg");
		String result = "";
		String style = getHtml("D:\\gela\\freeuni\\oop\\git-repo\\ideaCloud\\Project\\WebContent\\postStyle.html");
		String template = getHtml("D:\\gela\\freeuni\\oop\\git-repo\\ideaCloud\\Project\\WebContent\\postTamplate.html");
		for (int i=0;i<posts.size();i++){	
			String temp=generate_template(template, posts.get(i), user);
			
			result = result + temp;
			
		}
		result = result + style;
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.append(result);

		out.close();
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
	private static String generate_template(String template1, Post post, User user){
		String template = template1;
		String tmp1;
		tmp1=template.replace("::img-src::", user.getUSerImgSrc());
		template=tmp1.replace("::user-name::", user.getUSerNickname());
		tmp1=template.replace("::lvl::", Integer.toString(user.getUserLevel()));
		template=tmp1.replace("::post-title::", post.getPostTitle());
		tmp1=template.replace("::post-text::", post.getPostText());
		template=tmp1.replace("::vote-up::", Integer.toString(post.getPostCloud()));
		tmp1=template.replace("::vote-down::", Integer.toString(post.getPostUncloud()));
		return tmp1;
	}

}
