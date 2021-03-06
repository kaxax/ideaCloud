package Core;

public class User {

	private int userId;
	private String userName;
	private String userSurname;
	private String userNickName;
	private String userEmail;
	private String userPassword;
	private int userAge;
	private int userLevel;
	private int userSex;
	private String userMoto;
	private String userImgSrc;

	public User(int userId, String userName, String userSurname, String userNickName, String userEmail,
			String userPassword, int userAge, int userLevel, int userSex, String userMoto, String userImgSrc) {
		this.userId = userId;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userNickName = userNickName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userAge = userAge;
		this.userLevel = userLevel;
		this.userSex = userSex;
		this.userMoto = userMoto;
		this.userImgSrc = userImgSrc;

	}

	public int getUserId() {
		return this.userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getUserSurname() {
		return this.userSurname;
	}

	public String getUSerNickname() {
		return this.userNickName;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public String getUserPasswrod() {
		return this.userPassword;
	}

	public int getUSerAge() {
		return this.userAge;
	}

	public int getUserLevel() {
		return this.userLevel;
	}

	public int getUserSex() {
		return this.userSex;
	}

	public String getUserMoto() {
		return this.userMoto;
	}

	public String getUSerImgSrc() {
		return this.userImgSrc;
	}

	public void setUserImgSrc(String src) {
		this.userImgSrc = src;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	public void setUserSex(int sex) {
		this.userSex = sex;
	}

	public void setUserPassword(String pass) {
		this.userPassword = pass;
	}

	public void setUserSurname(String surname) {
		this.userSurname = surname;
	}

	public void setUserNickname(String nick) {
		this.userNickName = nick;
	}

}
