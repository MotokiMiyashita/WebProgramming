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
 * Servlet implementation class UserUpdate
 */
@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdate() {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		UserBeans ub = (UserBeans)session.getAttribute("ub");

		String login_id = request.getParameter("login_id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confPassword = request.getParameter("confPassword");
		String birth_date = request.getParameter("birth_date");
		String update_date = request.getParameter("update_date");


	    UserDao usrDao = new UserDao();
		boolean isCollectResister = true;
		isCollectResister = usrDao.updateUser(login_id, password, confPassword, name, birth_date, update_date);
		if(!isCollectResister) {

			String error_message = "入力された内容は正しくありません。";
			request.setAttribute("error_message", error_message);
			if( !password.equals(confPassword) ) {
				UserBeans formValue = new UserBeans();
				formValue.setLogin_id(login_id);
				formValue.setName(name);
				formValue.setBirth_date(birth_date);
				request.setAttribute("du", formValue);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
				dispatcher.forward(request, response);
			//	response.sendRedirect("UserUpdate");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);

		}else {
			String message = "ユーザー情報の登録に成功しました。";
			request.setAttribute("resister_success_message", message);
			response.sendRedirect("UserList");
		}
	}
}
