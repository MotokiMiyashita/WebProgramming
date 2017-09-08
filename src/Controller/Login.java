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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public HttpSession session;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(false);
		if (session ==null || session.getAttribute("ub") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}else {
			System.out.println("has session");
			response.sendRedirect("UserList");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String input_id = request.getParameter("login_id");
		String input_password = request.getParameter("login_password");

		UserDao usrDao = new UserDao();
		//ログイン失敗
		if(usrDao.existUser(input_id,input_password) == false) {
		    String message = "ログインIDまたはパスワードが異なります。";
		    request.setAttribute("error_message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
		//ログイン成功
		else {
			session = request.getSession();
			UserBeans ub = new UserBeans();
//			ub = usrDao.setUserBeans(input_id);
			ub = usrDao.searchDetail(input_id);
			session.setAttribute("ub", ub);
			response.sendRedirect("UserList");
		}
	}
}
