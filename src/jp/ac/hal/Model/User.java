package jp.ac.hal.Model;

public class User {
	
	private String userName;
	private String email;
	private String passwd;
	private int admin;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public int getAdmin() {
		return admin;
	}
	
	public void setAdmin(int admin) {
		this.admin = admin;
	}
}
