package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Controller.Util;
import model.UserBeans;

public class UserDao {

	//ログイン-ユーザー存在判定
	public boolean existUser(String query_id, String query_password) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE login_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, query_id);
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			String searched = rs.getString("password");
			return searched.toLowerCase().equals(Util.hashPass(query_password).toLowerCase())?true:false;

		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	//ユーザー登録時 ログインIDが使用されているかどうか
	public boolean existUser(String query_id) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE login_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, query_id);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()==false) return false;
			else return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	//ログイン-セッション情報保存
	public UserBeans setUserBeans(String query_id){
		Connection conn = null;
		UserBeans ub = new UserBeans();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE login_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, query_id);
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			String searched_name = rs.getString("name");
			ub.setLogin_id(query_id);
			ub.setName(searched_name);
			return ub;

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}



	//ユーザー検索(一覧)
	public List<UserBeans> findAll() {
		Connection conn = null;
		List<UserBeans> usrList = new ArrayList<UserBeans>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user ORDER BY login_id ASC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
		      while (rs.next()) {
		    	  	String login_id = rs.getString("login_id");
		    	  	String name = rs.getString("name");
		    	  	String birth_date = rs.getString("birth_date");
		    	  	UserBeans usr = new UserBeans();
		    		if(login_id.equals("admin"))continue;
		    	  	usr.setLogin_id(login_id);
		    	  	usr.setName(name);
		    	  	usr.setBirth_date(birth_date);
		    	  	usrList.add(usr);
		    	  }
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return usrList;
	}


	//ユーザー検索(ログイン名)
	public List<UserBeans> searchByID(String query) {
		Connection conn = null;
		List<UserBeans> usrList = new ArrayList<UserBeans>();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE login_id = ? ORDER BY login_id ASC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, query);
			ResultSet rs = pStmt.executeQuery();
		      while (rs.next()) {
		    	  	String login_id = rs.getString("login_id");
		    	  	String name = rs.getString("name");
		    	  	String birth_date = rs.getString("birth_date");
		    	  	UserBeans usr = new UserBeans();
		    	  	if(login_id.equals("admin"))continue;
		    	  	usr.setLogin_id(login_id);
		    	  	usr.setName(name);
		    	  	usr.setBirth_date(birth_date);
		    	  	usrList.add(usr);
		    	  	//System.out.println("name");
		    	  }
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return usrList;
	}


	//ユーザー検索(ユーザー名)
	public List<UserBeans> searchByName(String query){
		Connection conn = null;
		List<UserBeans> usrList = new ArrayList<UserBeans>();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE name LIKE ? ORDER BY name ASC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%"+query+"%");
			ResultSet rs = pStmt.executeQuery();
		      while (rs.next()) {
		    	  	String login_id = rs.getString("login_id");
		    	  	String name = rs.getString("name");
		    	  	String birth_date = rs.getString("birth_date");
		    	  	UserBeans usr = new UserBeans();
		    	  	if(login_id.equals("admin"))continue;
		    	  	usr.setLogin_id(login_id);
		    	  	usr.setName(name);
		    	  	usr.setBirth_date(birth_date);
		    	  	usrList.add(usr);
		    	  }
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return usrList;
	}


	//ユーザー検索(誕生日)
	/**
	 *
	 * @param query_begin
	 * @param query_end
	 * @return
	 */
	public List<UserBeans> searchByBirthdate(String query_begin, String query_end) {
		Connection conn = null;
		List<UserBeans> usrList = new ArrayList<UserBeans>();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE birth_date BETWEEN ? AND ? ORDER BY login_id ASC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, query_begin);
			pStmt.setString(2, query_end);
//		   DateFormat df = DateFormat.getDateInstance();
//		   Date beginDate = df.parse(query_begin);
//		   Date endDate = new DateFormat.parse(query_end);
			ResultSet rs = pStmt.executeQuery();
		      while (rs.next()) {
		    	  	String login_id = rs.getString("login_id");
		    	  	String name = rs.getString("name");
		    	  	String birth_date = rs.getString("birth_date");
		    	  	UserBeans usr = new UserBeans();
		    		if(login_id.equals("admin"))continue;
		    	  	usr.setLogin_id(login_id);
		    	  	usr.setName(name);
		    	  	usr.setBirth_date(birth_date);
		    	  	usrList.add(usr);
		    	  }
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return usrList;
	}






	//ユーザー詳細情報検索
	public UserBeans searchDetail(String query) {
		Connection conn = null;
  		UserBeans usr = new UserBeans();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE login_id = ? ORDER BY name ASC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, query);
			ResultSet rs = pStmt.executeQuery();
		      while (rs.next()) {
		    	  	String login_id = rs.getString("login_id");
		    	  	String name = rs.getString("name");
		    	  	String birth_date = rs.getString("birth_date");
		    	  	String create_date = rs.getString("create_date");
				String update_date = rs.getString("update_date");
		    	  	usr.setLogin_id(login_id);
		    	  	usr.setName(name);
		    	  	usr.setBirth_date(birth_date);
	    	  		usr.setCreate_date(create_date);
				usr.setUpdate_date(update_date);
		    	  }
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return usr;
	}




	//ユーザー情報の登録
	/**ユーザー新規登録
	 * @param login_id
	 * @param password
	 * @param confPassword
	 * @param name
	 * @param birth_date
	 * @param create_date
	 * @param update_date
	 * @return
	 */
	public boolean resisterUser(String login_id, String password, String confPassword, String name, String birth_date, String create_date, String update_date ) {
		String[] params = {login_id, password, confPassword, name, birth_date, create_date, update_date};
		if(Util.isEmptyCheck(params)) return false;
		if( existUser(login_id) ) return false;
		if( !password.equals(confPassword) ) return false;
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO user(login_id,name,birth_date,password,create_date,update_date) VALUES(?,?,?,?,?,?);";
			PreparedStatement pStmt = conn.prepareStatement(sql); //order不一致の場合 construction Accessor Impl.newInstanceエラー
			pStmt.setString(1, login_id);
			pStmt.setString(2, name);
			pStmt.setString(3, birth_date);
			pStmt.setString(4, Util.hashPass(password));
			pStmt.setString(5, create_date);
			pStmt.setString(6, update_date);
			//System.out.println(pStmt.toString());
			int num = pStmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//ユーザー情報の更新
	public boolean updateUser(String login_id, String password, String confPassword, String name, String birth_date, String update_date ) {

		if( login_id.isEmpty() ||  name.isEmpty() || birth_date.isEmpty()  || update_date.isEmpty() ) return false;
		if( (password.isEmpty() && !confPassword.isEmpty()) || (!password.isEmpty() && confPassword.isEmpty()) ) {
			System.out.println("work");
			return false;
		}
		if( !password.equals(confPassword) ) return false;
		Connection conn = null;
		try {
			if(password.isEmpty() && confPassword.isEmpty() ) {
				conn = DBManager.getConnection();
				String sql = "UPDATE user SET name=?, birth_date=?, update_date=? WHERE login_id = ?;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, name);
				pStmt.setString(2, birth_date);
				pStmt.setString(3, update_date);
				pStmt.setString(4, login_id);
				//System.out.println(pStmt.toString());
				int num = pStmt.executeUpdate();

			}else {
				conn = DBManager.getConnection();
				String sql = "UPDATE user SET name=?, birth_date=?, password=?, update_date=? WHERE login_id = ?;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, name);
				pStmt.setString(2, birth_date);
				pStmt.setString(3, Util.hashPass(password));
				pStmt.setString(4, update_date);
				pStmt.setString(5, login_id);
				//System.out.println(pStmt.toString());
				int num = pStmt.executeUpdate();
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}





	//ユーザー情報の削除
	public boolean removeUser(String login_id) {
		if( login_id.isEmpty() ) return false;

		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM user WHERE login_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login_id);
			//System.out.println(pStmt.toString());
			int num = pStmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}






////パスワード
//public String searchPass(String query_password) {
//	Connection conn = null;
//	try {
//		conn = DBManager.getConnection();
//		String sql = "SELECT password FROM user WHERE password='"+query_password+"';";
//		PreparedStatement pStmt = conn.prepareStatement(sql);
//		ResultSet rs = pStmt.executeQuery();
//		if(rs.next()) {
//			String searched_password = rs.getString("password");
//			return searched_password;
//		}
//		else return null;
//
//	}catch(SQLException e){
//		e.printStackTrace();
//		return null;
//	}
//}


//System.out.println(login_id);
//System.out.println(password);
//System.out.println(confPassword);
//System.out.println(name);
//System.out.println(birth_date);
//System.out.println(create_date);
//System.out.println(update_date);


