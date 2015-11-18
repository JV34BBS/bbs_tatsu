package jp.ac.hal.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.server.Dispatcher;
import jp.ac.hal.Model.*;

/**
 * Servlet implementation class Regist
 */
@WebServlet("/Regist")
public class Regist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		User user = new User();
		user.setUserName("a");
		user.setEmail("a@a");
		user.setPasswd("bbbb");
		try {
			Dao.getInstance().execRegist(user);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			System.out.println("重複");
		}
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		//登録内容取得
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		String msg = "";
		
		User user = new User();
		user.setUserName(name);
		user.setEmail(email);
		user.setPasswd(pass);
		
		//validation呼び出し
		Utils utils = new Utils();
		@SuppressWarnings("static-access")
		String err = utils.registValidator(name, pass, email);
		
		
		if (err == null || err.isEmpty()) {
			
			try {
				Dao.getInstance().execRegist(user);
				
				//登録完了msgをresultへ
				msg = "登録完了しました。";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("result.jsp").forward(request, response);
				
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				
				msg = "ユーザ名かEmailアドレスが重複しています。";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("regist.jsp").forward(request, response);
			}	
		}
		else {
			
			//utilsのエラーを登録画面に飛ばす
			request.setAttribute("msg", err);
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			
		}
		
	}

}
