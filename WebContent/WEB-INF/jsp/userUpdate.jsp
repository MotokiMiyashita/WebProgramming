<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ユーザ情報更新</title>
	<link rel="stylesheet" type="text/css" href="css/userUpdate.css">
</head>
<body>
	<div class="main">
		<div id="topRight">
			<span class="username">${sessionScope.ub.getName()}さん</span>
			<a href="Logout"><span class="logout">ログアウト</span></a>
		</div>

		<h1>ユーザ情報更新</h1>
		<%String error = (String)request.getAttribute("error_message");
		if(error != null){%> <div class="error_message"><%=error %> </div>
		<%}%>

		<form action="UserUpdate" method="post">
				<ul>
					<li class="elem">
						<label for="login_id">ログインID</label>
						${du.login_id}</li>
					<li class="elem">
						<label for="password">パスワード</label>
						<input type="password" name="password" size="20"></li>
					<li class="elem">
						<label for="confPassword">パスワード(確認)</label>
						<input type="password" name="confPassword" size="20"></li>
					<li class="elem">
						<label for="name">ユーザ名</label>
						<input type="text" name="name" size="20" value="${du.name}"></li>
					<li class="elem">
						<label for="birth_date">生年月日</label>
						<input type="date" name="birth_date" size="20" value="${du.birth_date}"></li>
				</ul>
				<input type="hidden" name="login_id" size="20" value="${du.login_id}">
				<input type="hidden" name="update_date" value="<%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %>">
				<div class="update"><input type="submit" value="更新" class="update_bottom"></div>
			</form>

		<div class="toUserlist"><a href="UserList">戻る</a></div>
	</div>
</body>
</html>