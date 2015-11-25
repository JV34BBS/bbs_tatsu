package jp.ac.hal.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
public class BBS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BBS() {
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
		} 
		
		//ユーザのdeleteボタンが押された時
		else if(request.getParameter("deleteBtn") != null){
			
			int id = Integer.parseInt(request.getParameter("comment_id"));
			Dao dao = new Dao();
			try {
					int rowNum = dao.deleteComment(id);
					request.setAttribute("msg", "コメントを削除しました");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
			RequestDispatcher disp = request.getRequestDispatcher("bbs.jsp");
			response.setContentType("text/html; charset=UTF-8");
			disp.forward(request, response);

		}
		
		
		//管理者側のdelete処理（チェックボックス）
		else if(request.getParameter("deleteSeleBtn") != null){
			
			String[] delete = request.getParameterValues("deleName");
			ArrayList<Integer> deleteNo = new ArrayList<Integer>();
		
			if(delete == null)
			{
				request.setAttribute("msg", "選択されていません。");
			}
			else
			{
				for(int i=0;i<delete.length;i++){
					deleteNo.add(Integer.parseInt(delete[i]));
				}	
				Dao dao = new Dao();
				try {
					boolean fl = dao.deleteSelect(deleteNo);
					request.setAttribute("msg", "コメントを削除しました");	
				} catch (ClassNotFoundException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}			
			}
			RequestDispatcher disp = request.getRequestDispatcher("bbs.jsp");
			response.setContentType("text/html; charset=UTF-8");
			disp.forward(request, response);	
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
