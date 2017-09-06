<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ユーザ新規登録</title>
	<link rel="stylesheet" type="text/css" href="css/userResister.css">
</head>
<body>
	<div class="main">
		<div id="topRight">
			<span class="username">${sessionScope.ub.getName()}さん</span>
			<a href="Logout"><span class="logout">ログアウト</span></a>
		</div>

		<h1>ユーザ新規登録</h1>
		<%String error = (String)request.getAttribute("error_message");
		if(error != null){%> <div class="error_message"><%=error %> </div>
		<%}%>

		<form action="UserResister" method="post">
				<ul>
					<li class="elem">
						<label for="login_id">ログインID</label>
						<input type="text" name="login_id"  size="20" value="${requestScope.value.getLogin_id()}"></li>
					<li class="elem">
						<label for="passowrd">パスワード</label>
						<input type="password" name="password" size="20"></li>
					<li class="elem">
						<label for="confPassword">パスワード(確認)</label>
						<input type="password" name="confPassword" size="20"></li>
					<li class="elem">
						<label for="name">ユーザ名</label>
						<input type="text" name="name" size="20" value="${requestScope.value.getName()}"></li>
					<li class="elem">
						<label for="birth_date">生年月日</label>
						<input type="date" name="birth_date" size="20" value="${requestScope.value.getBirth_date()}"></li>
				</ul>

<!-- <div class="result2">入力された内容は正しくありません<div> -->麻
 				<input type="hidden" name="create_date" value="<%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %>">
 				<input type="hidden" name="update_date" value="<%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %>">
				<div class="resister"><input type="submit" value="登録" class="resister_bottom"></div>
			</form>

		<div class="toUserlist"><a href="UserList">戻る</a></div>

	</div>
</body>
</html>



<!-- 	<form>
		ログインID<input type="text" name="login_id"><br>
		パスワード<input type="text" name="password"><br>
		パスワード(確認)<input type="text" name="passwordConf"><br>
		ユーザ名<input type="text" name="username"><br>
		生年月日<input type="text" name="birthday"><br>
		<input type="submit" name="submit" id="submit_button"  value="登録"><br>
		</form> -->