package jp.ac.hal.Model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import javax.naming.NamingException;

public class Dao {
	
	public static Dao instance;
	private final String URL =
			"jdbc:mysql:///bbs?user=jv34&password=jv34&useUnicode=true&charactorEncoding=utf8";
	
	/**
	 * シングルトン
	 * @return Dao
	 * @throws NamingException
	 */
	public static Dao getInstance() throws NamingException {
		
		if (instance == null) {
			instance = new Dao();
		}
		
		return instance;
	}
	
	/**
	 * コネクション
	 * @return Connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(URL);
		
		return conn;
	}
	
	/**
	 * ユーザ登録
	 * @param user
	 * @return int
	 * @throws SQLException
	 */
	public int execRegist(User user) throws SQLException {
		
		String sql = "insert into t_user(user_name, mail, passwd) values(?, ?, ?)";
		int rowNum = 0;
		
		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPasswd());
			rowNum = ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return rowNum;
	}
	
	/**
	 * ログイン
	 * @param user
	 * @return User
	 * @throws SQLException
	 */
	public User userLogin(User user) throws SQLException, ClassNotFoundException {
		
		String sql = "select user_name, mail, passwd from t_user where mail = ? && passwd = ?";
		User loginUser = new User();
		
		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPasswd());
			
			try (ResultSet rs = ps.executeQuery();) { 
				if (rs.next()) {
					loginUser.setUserName(rs.getString("user_name"));
					loginUser.setPasswd(rs.getString("passwd"));
					loginUser.setEmail(rs.getString("mail"));
				} else {
					loginUser = null;
				}
			}
		}
		
		return loginUser;
	}
	
	/**
	 * コメント挿入
	 * @param comment
	 * @return int
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int insertComment(Comment comment) throws SQLException, ClassNotFoundException {
		
		String sql = "insert into t_comment(user_name, comment) values(?, ?)";
		int rowNum;
		
		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
			ps.setString(1, comment.getUserName());
			ps.setString(2, comment.getComment());
			rowNum = ps.executeUpdate();
		}
		
		return rowNum;
	}
	
	/**
	 * コメント削除
	 * @param id
	 * @return int
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int deleteComment(int id) throws SQLException, ClassNotFoundException {
		
		String sql = "delete from t_comment where id = ?";
		int rowNum;
		
		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
			ps.setInt(1, id);
			rowNum = ps.executeUpdate();
		}
		
		return rowNum;
	}
	
	/**
	 * コメント編集
	 * @param comment
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int editComment(Comment comment) throws ClassNotFoundException, SQLException {
		
		String sql = "update t_comment set comment = ? where id = ?";
		int rowNum;
		
		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
			ps.setString(1, comment.getComment());
			ps.setInt(2, comment.getId());
			rowNum = ps.executeUpdate();
		}
		
		return rowNum;
	}
}
