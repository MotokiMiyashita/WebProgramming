package Controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.UserBeans;


/**
 * Servlet implementation class UserList
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath()).append(" @UserList.java");
		HttpSession session = request.getSession(false);
		if (session.getAttribute("ub") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("Login");
		}else {
			//ユーザー一覧表示
			UserDao usrDao = new UserDao();
			request.setAttribute("showHitUser", usrDao.findAll());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession(false);
		//UserBeans ub = (UserBeans) session.getAttribute("ub");
		request.setCharacterEncoding("UTF-8");
		System.out.println("サーブレット");
		String query_login_id = request.getParameter("login_id");
		String query_name = request.getParameter("name");
		String query_startBirth = request.getParameter("startBirth");
		String query_endBirth = request.getParameter("endBirth");


		//ユーザー検索機能
	    UserDao usrDao = new UserDao();
	    List<UserBeans> searchUserList = new ArrayList<UserBeans>();

	    System.out.println("UserList post");
	    //何も入力されていない場合
		if(query_login_id.isEmpty() && query_name.isEmpty() && query_startBirth.isEmpty()) {
			request.setAttribute("showHitUser", usrDao.findAll());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
			dispatcher.forward(request, response);
		}

		//ユーザーID検索された場合
		if(!query_login_id.isEmpty()) {
			searchUserList = usrDao.searchByID(query_login_id);
		}

		//ユーザー名で検索された場合
		if(!query_name.isEmpty()) {
			searchUserList = usrDao.searchByName(query_name);
		}

		//生年月日が検索された場合
		if(!query_startBirth.isEmpty() && !query_endBirth.isEmpty()) {
			searchUserList = usrDao.searchByBirthdate(query_startBirth,query_endBirth);
		}

		//ユーザー名と生年月日が検索された場合
		//if(query_login_id!=null) searchUserList = usrDao.searchByID(query_login_id);

		request.setAttribute("showHitUser",searchUserList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
		dispatcher.forward(request, response);
	}

}
