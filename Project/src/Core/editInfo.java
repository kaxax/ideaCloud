package Core;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.sun.javafx.iio.ImageStorage;

/**
 * Servlet implementation class editInfo
 */
@WebServlet("/editInfo")
public class editInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String uploadDir = "D:\\gela\\freeuni\\oop\\git-repo\\ideaCloud\\Project\\WebContent";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String first_name = "";
		String last_name = "";
		String nickname = "";
		String password = "";
		String imgStatus = "";
		String imgSrc = "";
		String sexString = "";
		HttpSession ses = request.getSession(false);
		int userId = (int) ses.getAttribute("user_id");
		String fileName = "";
		FileItem tmp = null;
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> ls = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				int counter = 0;
				for (FileItem cur : ls) {
					if (!cur.isFormField()) {
						tmp=cur;
					}
					String str = cur.getString();
					switch (counter) {
					case 1:
						first_name = str;
						//System.out.println("first_name set to: " + str + "counteria: " + counter);
						break;
					case 2:
						last_name = str;
						//System.out.println("last_name set to: " + str + "counteria: " + counter);
						break;
					case 3:
						nickname = str;
						//System.out.println("nickname set to: " + str + "counteria: " + counter);
						break;
					case 5:
						password = str;
						//System.out.println("password set to: " + str + "counteria: " + counter);
						break;
					case 6:
						imgStatus = str;
						//System.out.println("imgStatus set to: " + str + "counteria: " + counter);
						break;
					case 7:
						sexString = str;
						//System.out.println("sexString set to: " + str + "counteria: " + counter);
						if(sexString.equals("female")){
							throw new WomenOutOfKitchenException("Go Back To Kitchen Woman!");
						}
						break;
					default:
						break;
					}
					counter++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(sexString.equals("female")){
			ServletContext sc = this.getServletContext();
			ses.invalidate();
			RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			return;
		}
		Pool p;
		try {
			p = Pool.getPool();
			Connection con = p.getConnection();
			Database db = new Database(con);
			User user = db.getUser(userId);
			user.setUserName(first_name);
			user.setUserSurname(last_name);
			if (!password.equals("")) {
				user.setUserPassword(password);
			}
			user.setUserNickname(nickname);
			if (sexString.equals("male")) {
				user.setUserSex(1);
			} else if (sexString.equals("female")) {
				user.setUserSex(0);
			}
			if (imgStatus.equals("changed")) {
				fileName = new File(tmp.getName(), userId + ".jpg").getName();
				tmp.write(new File(uploadDir + File.separator + fileName));
				imgSrc = getImgSrc(userId);
				int lengthCounter = 4;
				lengthCounter += ("" + userId).length();
				imgSrc = imgSrc.substring(imgSrc.length() - lengthCounter);
				user.setUserImgSrc(imgSrc);
			}
			db.updateUser(user);
			con.close();

		} catch (SQLException | PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/homepage.jsp");
		rd.forward(request, response);
	}

	private String getImgSrc(int userId) {
		String src = "";
		src = uploadDir + "/" + userId + ".jpg";
		return src;
	}

}
