package jp.ac.hal.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ac.hal.Model.Comment;
import jp.ac.hal.Model.Dao;
import jp.ac.hal.Model.Utils;

/**
 * Servlet implementation class BBS
 */
@WebServlet("/Bbs")
public class Bbs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bbs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		if (request.getParameter("submit") != null) {
			System.out.println(request.getParameter("comment"));
			String err = Utils.commentValidator(request.getParameter("comment").toString());
			System.out.println(err);
			if (err == null || err.isEmpty()) {
				Comment comment = new Comment();
				comment.setUserName(request.getParameter("userName"));
				comment.setComment(request.getParameter("comment"));
				try {
					int ret = Dao.getInstance().insertComment(comment);
					request.setAttribute("msg", "コメントを登録しました");
				} catch (ClassNotFoundException | SQLException | NamingException e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("msg", err);
			}
			RequestDispatcher disp = request.getRequestDispatcher("bbs.jsp");
			response.setContentType("text/html; charset=UTF-8");
			disp.forward(request, response);
		} else {
			response.sendRedirect("login.jsp");
		}
		
		// ログアウト
		if (request.getParameter("logout") != null) {
			HttpSession session = request.getSession();
			if (session != null) {
				session.removeAttribute("logUser");
				session.invalidate();
				response.sendRedirect("login.jsp");
			}
		}
	}
}
