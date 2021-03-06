package Core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Core.Cloud;
import Core.Post;
import Core.Comment;
import Core.User;
import Core.Message;
import Core.Notification;

public class Database {
	private Connection con;

	public Database(Connection conn) {
		this.con = conn;
	}
	public void insertCategory(int postId, String cat){
		try {
			PreparedStatement stmt = con
					.prepareStatement("insert into category "
							+ "(postId, category) "
							+ "values (?, ?)");
			stmt.setInt(1, postId);
			stmt.setString(2, cat);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertUser(User u) {

		try {
			PreparedStatement stmt = con
					.prepareStatement("insert into user "
							+ "(nickname, name, surname, email, password, age, sex, level, moto, imgSrc,cdate) "
							+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)");
			stmt.setString(1, u.getUSerNickname());
			stmt.setString(2, u.getUserName());
			stmt.setString(3, u.getUserSurname());
			stmt.setString(4, u.getUserEmail());
			stmt.setString(5, u.getUserPasswrod());
			stmt.setInt(6, u.getUSerAge());
			stmt.setInt(7, u.getUserSex());
			stmt.setInt(8, u.getUserLevel());
			stmt.setString(9, u.getUserMoto());
			stmt.setString(10, u.getUSerImgSrc());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			stmt.setString(11, dateFormat.format(date));
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertCloud(Cloud c) {
		try {
			PreparedStatement stmt = con.prepareStatement("insert into clouds "
					+ "(postId, userId, cloud) " + "values (?, ?, ?)");
			stmt.setInt(1, c.getPostId());
			stmt.setInt(2, c.getUserId());
			stmt.setInt(3, c.status());

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertcommentCloud(Cloud c) {
		try {
			PreparedStatement stmt = con.prepareStatement("insert into commentClouds "
					+ "(commentId, userId, cloud) " + "values (?, ?, ?)");
			System.out.println(c.getPostId() + "   " + c.getUserId() + "   " + c.status());
			stmt.setInt(1, c.getPostId());
			stmt.setInt(2, c.getUserId());
			stmt.setInt(3, c.status());				
			stmt.execute();
			System.out.println(c.getPostId());
			System.out.println(c.getUserId());
			System.out.println(c.status());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Cloud getCloud(int id) {
		Cloud c;
		try {

			PreparedStatement stmt = con
					.prepareStatement("select * from clouds where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {

				int postId = rs.getInt("postId");
				int authorId = rs.getInt("userId");
				int cloud = rs.getInt("cloud");
				c = new Cloud(postId, authorId, cloud);
				c.setId(id);
				return c;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Cloud getCommentCloud(int id) {
		Cloud c;
		try {

			PreparedStatement stmt = con
					.prepareStatement("select * from commentClouds where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {

				int cId = rs.getInt("commentId");
				int authorId = rs.getInt("userId");
				int cloud = rs.getInt("cloud");
				c = new Cloud(cId, authorId, cloud);
				c.setId(id);
				return c;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Cloud getCloudByIds(int uId, int pId) {
		Cloud c;
		try {

			PreparedStatement stmt = con
					.prepareStatement("select * from clouds where postId = ? and userId = ?");
			stmt.setInt(1, pId);
			stmt.setInt(2, uId);

			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				int id = rs.getInt("id");
				int postId = rs.getInt("postId");
				int authorId = rs.getInt("userId");
				int cloud = rs.getInt("cloud");
				c = new Cloud(postId, authorId, cloud);
				c.setId(id);
				return c;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Cloud getCommentCloudByIds(int uId, int cId) {
		Cloud c;
		try {

			PreparedStatement stmt = con
					.prepareStatement("select * from commentClouds where commentId = ? and userId = ?");
			stmt.setInt(1, cId);
			stmt.setInt(2, uId);

			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				int id = rs.getInt("id");
				int commentId = rs.getInt("commentId");
				int authorId = rs.getInt("userId");
				int cloud = rs.getInt("cloud");
				c = new Cloud(commentId, authorId, cloud);
				c.setId(id);
				return c;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateCloud(Cloud c) {

		try (PreparedStatement stmt = con
				.prepareStatement("update clouds set postId = ?, userId = ?, cloud = ? where id = ?")) {
			
			stmt.setInt(1, c.getPostId());
			stmt.setInt(2, c.getUserId());
			stmt.setInt(3, c.status());
			stmt.setInt(4, c.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void updateCommentCloud(Cloud c) {

		try (PreparedStatement stmt = con
				.prepareStatement("update commentClouds set commentId = ?, userId = ?, cloud = ? where id = ?")) {
			
			stmt.setInt(1, c.getPostId());
			stmt.setInt(2, c.getUserId());
			stmt.setInt(3, c.status());
			stmt.setInt(4, c.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void removeCloud(Cloud c) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("delete from clouds where id = ? ");
			stmt.setInt(1, c.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeCommentCloud(Cloud c) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("delete from commentClouds where id = ? ");
			stmt.setInt(1, c.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Comment> getCommentByIds(int pId, int start, int end) {
		ArrayList<Comment> result = new ArrayList<Comment>();
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from comments where postId = ? order by cdate desc");
			stmt.setInt(1, pId);

			ResultSet rs = stmt.executeQuery();
			for (int i = 0; rs != null && rs.next() && i <= end; i++) {
				if (i >= start) {
					int cId = rs.getInt("id");
					Comment c = getComment(cId);
					result.add(c);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public int addPost(Post p) {
		int id = 0;
		try {
			PreparedStatement stmt = con
					.prepareStatement("SHOW TABLE  STATUS LIKE 'post'");
			

			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				id = rs.getInt("Auto_increment");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO post "
							+ "(title, text, type, authorId, topic, clouds, unclouds, cdate) "
							+ "values (?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, p.getPostTitle());
			stmt.setString(2, p.getPostText());
			stmt.setInt(3, p.getPostType());
			stmt.setInt(4, p.getPostUSerId());
			stmt.setString(5, p.getPostTopic());
			stmt.setInt(6, p.getPostCloud());
			stmt.setInt(7, p.getPostUncloud());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			stmt.setString(8, dateFormat.format(date));
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}

	public Post getPost(int id) {
		Post p;

		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from post where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				String title = rs.getString("title");
				String text = rs.getString("text");
				int type = Integer.parseInt(rs.getString("type"));
				int authorId = rs.getInt("authorId");
				String topic = rs.getString("topic");
				int clouds = rs.getInt("clouds");
				int unclouds = rs.getInt("unclouds");
				p = new Post(title, text, authorId, type, topic, clouds,
						unclouds);
				p.setId(id);
				return p;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public User getUser(int id) {
		User u;

		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from user where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				String nickname = rs.getString("nickname");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int age = rs.getInt("age");
				int sex = rs.getInt("sex");
				int level = rs.getInt("level");
				String moto = rs.getString("moto");
				String imgSrc = rs.getString("imgSrc");
				u = new User(id, name, surname, nickname, email, password, age,
						level, sex, moto, imgSrc);
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public Comment getComment(int id) {
		Comment c;
		try {

			PreparedStatement stmt = con
					.prepareStatement("select * from comments where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {

				int postId = rs.getInt("postId");
				int authorId = rs.getInt("authorId");
				String text = rs.getString("text");
				int clouds = rs.getInt("clouds");
				int unclouds = rs.getInt("unclouds");
				c = new Comment(postId, authorId, clouds, unclouds, text);
				c.setId(id);
				return c;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ak unda amoigos userId-s mkone useris droit dalagebuli postebi
	// da makedan daabrunos start idexidan end indexamde mdgomi postebis id-ebis
	// arrayListi
	// (to ideas == true ideebi sachiroa da questions == true qyestionebic
	// sachiroa)
	// type 1 idea
	public ArrayList<Integer> getLatestProfilePosts(int userId, int start,
			int end, boolean ideas, boolean questions) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int type1;
		int type2;
		if (ideas && questions) {
			type2 = 2;
			type1 = -1;
		} else if (ideas) {
			type1 = 0;
			type2 = 2;
		} else {
			type1 = -1;
			type2 = 1;
		}
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from post where authorId = ? and type<? and type >? order by cdate desc");
			stmt.setInt(1, userId);
			stmt.setInt(2, type2);
			stmt.setInt(3, type1);

			ResultSet rs = stmt.executeQuery();
			for (int i = 0; rs != null && rs.next() && i <= end; i++) {
				int postId = rs.getInt("id");
				if (i >= start)
					result.add(postId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// ak ubralod unda amoigos droit dalagebuli postebi (kvela postis masivida)
	// da makedan daabrunos start indexidan end indexamde racaa magati id-ebis
	// arraylisti unda
	// (to ideas == true ideebi sachiroa da questions == true qyestionebic
	// sachiroa)
	public ArrayList<Integer> getLatestPosts(int start, int end, boolean ideas,
			boolean questions) {

		ArrayList<Integer> result = new ArrayList<Integer>();
		int type1;
		int type2;
		if (ideas && questions) {
			type2 = 2;
			type1 = -1;
		} else if (ideas) {
			type1 = 0;
			type2 = 2;
		} else {
			type1 = -1;
			type2 = 1;
		}
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from post where type<? and type >? order by cdate desc");

			stmt.setInt(1, type2);
			stmt.setInt(2, type1);

			ResultSet rs = stmt.executeQuery();
			for (int i = 0; rs != null && rs.next() && i <= end; i++) {
				int postId = rs.getInt("id");
				if (i >= start)
					result.add(postId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void addComment(Comment c) {

		try {
			PreparedStatement stmt = con
					.prepareStatement("insert into comments "
							+ "(postId, authorId, text, clouds, unclouds, cdate) "
							+ "values (?, ?, ?, ?, ?, ?)");

			stmt.setInt(1, c.getCommentPostId());
			stmt.setInt(2, c.getCommentUSerId());
			stmt.setString(3, c.getCommentText());
			stmt.setInt(4, c.getCommentCloud());
			stmt.setInt(5, c.getCommentUncloud());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			stmt.setString(6, dateFormat.format(date));
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getId(String email, String password) {
		int result = -1;

		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from user where email = ? and password = ? ");
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				result = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int getUserId(String email) {
		int result = -1;

		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from user where email = ? ");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				result = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	
	public boolean contains(ArrayList<Post> posts, Post p){
		for(int i=0; i<posts.size(); i++){
			if(posts.get(i).getPostId() == p.getPostId())
				return true;
		}
		return false;
	}
	
	public ArrayList<Post> getCategoryPosts(ArrayList<String> categories, int idea, int question) {
		ArrayList<Post> posts = new ArrayList<Post>();
		try {
			String st = "select * from post ";
			if (categories.size() != 0) {
				st = "select * from category where ";
				for (int i = 0; i < categories.size() - 1; i++) {
					st += "category =? or ";
				}
				st+= "category =?";
			}
			PreparedStatement stmt = con
					.prepareStatement(st);

			for(int i =0 ; i<categories.size(); i++){
				stmt.setString(i+1, categories.get(i));
			}
			System.out.println(st);
			ResultSet rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				int pid =(categories.size() != 0)? rs.getInt("postId"):rs.getInt("id");
				Post p = getPost(pid);
				if((p.getPostType()==1 && idea==1) || (p.getPostType()==0 && question==1) && !contains(posts,p))
					posts.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;


	}

	public ArrayList<Comment> getPostComments(int id) {
		ArrayList<Comment> result = new ArrayList<Comment>();
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from comments where postId = ? order by cdate");

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				int cId = rs.getInt("id");
				Comment c = getComment(cId);
				result.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void updateUser(User u) {
		try (PreparedStatement stmt = con
				.prepareStatement("update user set nickname = ?, name = ?, surname= ?, email = ?, password = ?, age = ?, sex = ?, level = ?, moto = ?, imgSrc = ? "
						+ "where id = ?")) {
			stmt.setString(1, u.getUSerNickname());
			stmt.setString(2, u.getUserName());
			stmt.setString(3, u.getUserSurname());
			stmt.setString(4, u.getUserEmail());
			stmt.setString(5, u.getUserPasswrod());
			stmt.setInt(6, u.getUSerAge());
			stmt.setInt(7, u.getUserSex());
			stmt.setInt(8, u.getUserLevel());
			stmt.setString(9, u.getUserMoto());
			stmt.setString(10, u.getUSerImgSrc());
			stmt.setInt(11, u.getUserId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    
	public void updatePost(Post p){
		try (PreparedStatement stmt = con
				.prepareStatement("update post set title = ?, text = ?, type= ?, authorId = ?, topic = ?, clouds = ?, unclouds = ? where id = ?")) {
			stmt.setString(1, p.getPostTitle());
			stmt.setString(2, p.getPostText());
			stmt.setInt(3, p.getPostType());
			stmt.setInt(4,p.getPostUSerId());
			stmt.setString(5, p.getPostTopic());
			stmt.setInt(6, p.getPostCloud());
			stmt.setInt(7, p.getPostUncloud());
			stmt.setInt(8, p.getPostId());
			
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateComment(Comment c){
		try (PreparedStatement stmt = con
				.prepareStatement("update comments set postId = ?, authorId = ?, text= ?,clouds = ?, unclouds = ? where id = ?")) {
		
			stmt.setInt(1, c.getCommentPostId());
			stmt.setInt(2, c.getCommentUSerId());
			stmt.setString(3, c.getCommentText());
			stmt.setInt(4, c.getCommentCloud());
			stmt.setInt(5, c.getCommentUncloud());
			stmt.setInt(6, c.getCommentId());
			
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void removeUser(User u) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("delete from user where id = ? ");
			stmt.setInt(1, u.getUserId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertMessage(Message m) {

		try {
			PreparedStatement stmt = con.prepareStatement(
					"insert into chat " + "(sender, reciever, message, seen, cdate) " + "values (?, ?,? ,?, ?)");
			stmt.setString(1, m.getSender());
			stmt.setString(2, m.getReciever());
			stmt.setString(3, m.getMessage());
			stmt.setInt(4, m.getStatus());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			stmt.setString(5, dateFormat.format(date));
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertNotification(Notification n) {

		try {
			PreparedStatement stmt = con
					.prepareStatement("insert into notification " + "(sender, reciever, seen, cdate) " + "values (?, ?,? , ?)");
			stmt.setString(1, n.getSender());
			stmt.setString(2, n.getReciever());
			stmt.setInt(3, n.getStatus());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			stmt.setString(4, dateFormat.format(date));
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// aqvs tu ara users notificationi romelic ar unaxavs
	public boolean isNotification(String user) {
		try {

			PreparedStatement stmt = con.prepareStatement("select * from notification where reciever = ? and seen =0");
			stmt.setString(1, user);

			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {

				return true;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// aqvs tu ara unaxavi notificationi konkretuli userisgan
	public boolean peerNotification(String sender, String reciever) {
		try {

			PreparedStatement stmt = con
					.prepareStatement("select * from notification where sender =? and reciever = ? and seen =0");
			stmt.setString(1, sender);
			stmt.setString(2, reciever);

			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {

				return true;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
//abrunebs yvela users vistanac qonia chati
	public ArrayList<String> getPeers(String user) {
		ArrayList<String> peers = new ArrayList<String>();
		try {

			PreparedStatement stmt = con
					.prepareStatement("select * from notification where sender =? or reciever = ? order by cdate desc");
			stmt.setString(1, user);
			stmt.setString(2, user);

			ResultSet rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				
				String first = rs.getString("sender");
				String second = rs.getString("reciever");
				String peer = (first.equals(user)) ? second : first;
				if(!peers.contains(peer))
					peers.add(peer);
			}
			
			return peers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// qonia tu ara notificationi
	public boolean wasNotification(String sender, String reciever) {
		try {

			PreparedStatement stmt = con
					.prepareStatement("select * from notification where sender =? and reciever = ?");
			stmt.setString(1, sender);
			stmt.setString(2, reciever);

			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {

				return true;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// naxa konkretuli notificationi
	public void notificationSeen(String sid, String rid) {

		try (PreparedStatement stmt = con
				.prepareStatement("update notification set seen = ?  where sender = ? and reciever = ?")) {
			stmt.setInt(1, 1);
			stmt.setString(2, sid);
			stmt.setString(3, rid);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// axali notificationi
	public void newNotification(String sid, String rid) {
		try (PreparedStatement stmt = con
				.prepareStatement("update notification set seen = ? , cdate = ? where sender = ? and reciever = ?")) {
			stmt.setInt(1, 0);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			stmt.setString(2, dateFormat.format(date));
			stmt.setString(3, sid);
			stmt.setString(4, rid);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> checkMessage(String sid, String rid) {
		ArrayList<String> result = new ArrayList<String>();
		try {

			PreparedStatement stmt = con.prepareStatement(
					"select * from chat where sender = ? and reciever = ? and seen =? order by cdate");
			stmt.setString(1, sid);
			stmt.setString(2, rid);
			stmt.setInt(3, 0);

			ResultSet rs = stmt.executeQuery();
			while (rs != null && rs.next()) {

				String text = rs.getString("message");
				result.add(text);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> getOldMessages(String sid, String rid) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"select * from chat where (sender = ? and reciever = ?) or (sender = ? and reciever = ?)  order by cdate desc");
			stmt.setString(1, sid);
			stmt.setString(2, rid);
			stmt.setString(3, rid);
			stmt.setString(4, sid);
			
			
			ResultSet rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				
				String sender = rs.getString("sender");
				String reciever = rs.getString("reciever");
				String text = rs.getString("message");
				result.add(sender);
				result.add(text);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void seen(String sid, String rid) {

		try (PreparedStatement stmt = con
				.prepareStatement("update chat set seen = ?  where sender = ? and reciever = ?")) {
			stmt.setInt(1, 1);
			stmt.setString(2, sid);
			stmt.setString(3, rid);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	
}
