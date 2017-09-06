package Controller;

import java.io.IOException;

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
 * Servlet implementation class UserRemove
 */
@WebServlet("/UserRemove")
public class UserRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRemove() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("ub") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}else {
			String login_id = request.getParameter("login_id");
		    UserDao usrDao = new UserDao();
			UserBeans detailUser = usrDao.searchDetail(login_id);
			request.setAttribute("du", detailUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userRemove.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String ok = request.getParameter("ok");
		String cancel = request.getParameter("cancel");
		String login_id = request.getParameter("login_id");

	    UserDao usrDao = new UserDao();
		boolean isCollectResister = true;
		isCollectResister = usrDao.removeUser(login_id);
		if(!isCollectResister) {

		}else {
			String message = "ユーザー情報の削除に成功しました。";
			request.setAttribute("resister_success_message", message);
			response.sendRedirect("UserList");
		}
	}

}
