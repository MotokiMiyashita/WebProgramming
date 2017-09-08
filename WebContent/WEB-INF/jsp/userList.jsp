<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UserBeans"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.servlet.RequestDispatcher"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ユーザ一覧</title>
	<link rel="stylesheet" href="css/userList.css">
</head>
<body>
	<div class="main">
			<div class="topRight"><span class="username">${sessionScope.ub.getName()}さん</span>
				<a href="Logout"><span class="logout">ログアウト</span></a>
			</div>

		<h1>ユーザ一覧</h1>
		<%String message = (String)request.getAttribute("success_message");
		if(message != null){%> <div class="resultmessage"><%=message %> </div> <%}%>
		<!-- <div class="result1">ユーザ情報の登録に成功しました</div> -->

		<% UserBeans ub = (UserBeans) session.getAttribute("ub");%>
 		<% if( (ub.getLogin_id().equals("admin")) ) {%><div id="userResister"><a href="UserResister">新規登録</a></div><%} %>
  	<!-- 	<div id="userResister"><a href="UserResister">新規登録</a></div> -->

		<form action="UserList" method="post">
			<div id="bold">
			<ul>
				<li class="elem">
					<label for="login_id">ログインID</label>
					<input type="text" name="login_id"  size="45" value="${fv.login_id}"></li>
				<li class="elem">
					<label for="name">ユーザ名</label>
					<input type="text" name="name" size="45" value=""></li>
				<li class="elem">
					<label for="startBirth">生年月日</label>
					<input type="date" name="startBirth" value="年/月/日" ><span id="boldNot">~</span>
					<input type="date" name="endBirth" value="年/月/日"><br></li>
			</ul>
			<div id="search"><input type="submit" value="検索" id="search_bottom"></div><br>
			</div>
		</form>

		<hr>
		<table border="1" id="userlist">
			<tr class="title">
				<th>ログインID</th>
				<th>ユーザ名</th>
				<th>生年月日</th>
				<th>&nbsp;</th>
			</tr>
			<%
			List<UserBeans> list = (List<UserBeans>)request.getAttribute("showHitUser");
			//System.out.println("userList.jsp");
			if(list!=null){
				for(UserBeans each_user : list){%>
			<tr>
					<td> <label class="listElem"><%=each_user.getLogin_id()%></label></td>
					<td> <label class="listElem"><%=each_user.getName()%></label></td>
					<td> <label class="listElem"><%=each_user.getBirth_date()%></label></td>
					<td>
	 					<ul class="nav">
	  						<form action="UserDetail" method="post">
								<input type="hidden" name="selectedID" value="<%=each_user.getLogin_id() %>">
								<input type="submit" value="詳細" class="detail">
							</form>
						<% if(ub.getLogin_id().equals("admin") || each_user.getLogin_id().equals(ub.getLogin_id())) {%>
	 						<form action="UserUpdate" method="post">
								<input type="hidden" name="selectedID" value="<%=each_user.getLogin_id() %>">
								<input type="submit" value="更新" class="update">
							</form>
						<%} %>
						<% if(ub.getLogin_id().equals("admin")) {%>
							<form action="UserRemove" method="post">
								<input type="hidden" name="selectedID" value="<%=each_user.getLogin_id() %>">
								<input type="submit" value="削除" class="remove">
							</form>
						<%} %>

						</ul>
					</td>
			</tr>
				<%}
			}
			%>
		</table>
	</div>

</body>
</html>






 <%--first href doGet()ver
 						<a href="UserDetail<%="?login_id="+each_user.getLogin_id()%>" class="detail">詳細</a>
 					<% if(ub.getLogin_id().equals("admin") || each_user.getLogin_id().equals(ub.getLogin_id())) {%>
						<a href="UserUpdate<%="?login_id="+each_user.getLogin_id()%>" class="update">更新</a>
					<%} %>
					<% if(ub.getLogin_id().equals("admin")) {%>
						<a href="UserRemove<%="?login_id="+each_user.getLogin_id()%>" class="delete">削除</a>
					<%} %>
--%>

<!-- href onClick(),post ver
						<a href="" onclick="document.detail.submit(); return false;">d</a>
						<form name="detail" method="POST" action="UserResister">
						<input type=hidden name="aaa" value="d">

			<%--	my		<form name="detail" method="post" action="UserDetail">
						<input type=hidden name="login_id" value="<%=each_user.getLogin_id() %>>"></form>
						<a href="UserDetail" onclick="document.detail.submit(); return false;">詳細</a>
			--%>
-->

<%-- requestビーンズver
						<button type="button" class="detail" onclick="<%
 						request.setAttribute("selectedUserID", each_user.getLogin_id());
	 					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDetail.jsp");
						dispatcher.forward(request, response);
 						">詳細</button>
 --%>

<!-- ボタンver 			<button type="button" class="update">更新</button>
 						<button type="button" class="delete">削除</button>
 -->
