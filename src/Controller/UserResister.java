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
 * Servlet implementation class UserResister
 */
@WebServlet("/UserResister")
public class UserResister extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserResister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("ub") == null) {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
//			dispatcher.forward(request, response);
			response.sendRedirect("Login");
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userResister.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String login_id = request.getParameter("login_id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confPassword = request.getParameter("confPassword");
		String birth_date = request.getParameter("birth_date");
		String create_date = request.getParameter("create_date");
		String update_date = request.getParameter("update_date");
	    if(login_id==null || name==null || password==null || confPassword ==null
	    		|| birth_date==null || create_date==null || update_date==null) response.sendRedirect("Login");

	    UserDao usrDao = new UserDao();
		if(!usrDao.resisterUser(login_id, password, confPassword, name, birth_date, create_date, update_date)) {

			String error_message = "入力された内容は正しくありません。";
			request.setAttribute("error_message", error_message);
				UserBeans formValue = new UserBeans();
				formValue.setLogin_id(login_id);
				formValue.setName(name);
				formValue.setBirth_date(birth_date);
				request.setAttribute("value", formValue);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userResister.jsp");
				dispatcher.forward(request, response);

		}else {
			String message = "ユーザー情報の登録に成功しました。";
			request.setAttribute("success_message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserList");
			dispatcher.forward(request, response);
		}
	}

}
