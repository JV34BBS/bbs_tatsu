package jp.ac.hal.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

public class Dao<Users> {

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
	 * ユーザ削除
	 * @param user
	 * @return int
	 * @throws SQLException
	 */

	public int Delete(String user) throws SQLException {

		String sql = "delete from t_user where user_name = ?";
		int rowNum = 0;

		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
			ps.setString(1, user);
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
<<<<<<< HEAD

		String sql = "select user_name, mail, passwd from t_user where mail = ? && passwd = ?";
=======
		
		String sql = "select user_name, mail, passwd, admin from t_user where mail = ? && passwd = ?";
>>>>>>> 2da7db00eb0b75d37920104d01e964a667d9869a
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
					loginUser.setAdmin(rs.getInt("admin"));
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
<<<<<<< HEAD

=======
	
	
	//選択削除
	/**
	 * 
	 * @param deleteNo
	 * @return ret
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean deleteSelect(ArrayList<Integer> deleteNo) throws SQLException, ClassNotFoundException
	{
		boolean ret = false;
		String sql = "delete from t_comment where id = ?";
		try(
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
		){	
			
			for(int i: deleteNo){
				ps.setInt(1, i);
				ps.executeUpdate();
			}
			ret = true;
		}
		
		return ret;
	}
	
	
>>>>>>> 2da7db00eb0b75d37920104d01e964a667d9869a
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


	/**
	 * コメント全件取得
	 * @return List<Comment>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Comment> fetchAllComment() throws ClassNotFoundException, SQLException {

		String sql = "select * from t_comment";
		List<Comment> commentList = new ArrayList<Comment>();

		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Comment comment = new Comment();
					comment.setId(rs.getInt("id"));
					comment.setUserName(rs.getString("user_name"));
					comment.setComment(rs.getString("comment"));
					comment.setCreatedAt(rs.getString("created_at"));
					commentList.add(comment);
				}
			}
		}

		return commentList;
	}

	//ユーザ一覧
	public List<User> fetchAllUser() throws ClassNotFoundException, SQLException {

		String sql = "select * from t_user";
		List<User> UserList = new ArrayList<User>();

		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					User user = new User();
					user.setUserName(rs.getString("user_name"));
					UserList.add(user);
				}
			}
		}

		return UserList;
	}

}
