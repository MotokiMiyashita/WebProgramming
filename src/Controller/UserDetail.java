package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class UserDetail
 */
@WebServlet("/UserDetail")
public class UserDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		if (session.getAttribute("ub") == null) {
			response.sendRedirect("Login");
		}else {
			response.sendRedirect("Login");
//			String select_login_id = (String)request.getAttribute("selectedUserID");
////			String select_login_id = request.getParameter("login_id");
//		    UserDao usrDao = new UserDao();
//			UserBeans detailUser = usrDao.searchDetail(select_login_id);
//			request.setAttribute("du", detailUser);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDetail.jsp");
//			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String selectID = request.getParameter("selectedID");
	    UserDao usrDao = new UserDao();
		UserBeans detailUser = usrDao.searchDetail(selectID);

		try {
			String strDate = detailUser.getCreate_date();
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = sdFormat.parse(strDate);
			String str = new SimpleDateFormat("yyyy年MM月dd hh:mm").format(date);
			detailUser.setCreate_date(str);

			strDate = detailUser.getUpdate_date();
			sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			date = sdFormat.parse(strDate);
			str = new SimpleDateFormat("yyyy年MM月dd hh:mm").format(date);
			detailUser.setUpdate_date(str);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		request.setAttribute("du", detailUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDetail.jsp");
		dispatcher.forward(request, response);

	}
}


//String tmp = detailUser.getCreate_date();
//String[] value1 = tmp.split(" ");
//String[] value2 = value1[0].split("-");
//for(String str : value2) System.out.println(str);
//detailUser.setCreate_date(value2[0]+"月"+value2[1]+"年"+value2[2]+"日");