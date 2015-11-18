package jp.ac.hal.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import jp.ac.hal.Model.Dao;
import jp.ac.hal.Model.User;
import jp.ac.hal.Model.Utils;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		String msg = "";
		
		User user = new User();
		user.setEmail(email);
		user.setPasswd(pass);
		
		String err = Utils.loginValidator(email, pass);
		
		
		if (err == null || err.isEmpty()) {
			//セッションオブジェクト生成
			HttpSession session = request.getSession();
			
			try {
				
				User logUser = Dao.getInstance().userLogin(user);
				if (logUser != null) {
					//ユーザ情報をセッションに格納
					session.setAttribute("logUser", logUser);
					request.getRequestDispatcher("bbs.jsp").forward(request, response);
				}
				else {
					msg = "ログイン失敗しました";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				
			} catch (SQLException | NamingException | ClassNotFoundException e) {
				e.printStackTrace();
				
			}	
		}
		else {
			//utilsのエラーを登録画面に飛ばす
			request.setAttribute("msg", err);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
	}

}
