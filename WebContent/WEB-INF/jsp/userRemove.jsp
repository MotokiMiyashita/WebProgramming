<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ユーザ削除確認</title>
	<link rel="stylesheet" type="text/css" href="css/userRemove.css">
</head>
<body>
	<div class="main">
		<div id="topRight">
			<span class="username">${sessionScope.ub.getName()}さん</span>
			<a href="Logout"><span class="logout">ログアウト</span></a>
		</div>

		<h1>ユーザ削除確認</h1>
		<div class="message">
			ログインID: ${du.login_id} <br>
			を本当に削除してよろしいでしょうか。
		</div>
		<div class="form_conf">
			<form action="UserList" method="get">
				<div class="no"><input type="submit" value="キャンセル" class="no_bottom"></div>
				<input type="hidden" name="login_id" size="20" value="${du.login_id}">
			</form>
			<form action="UserRemove" method="post">
				<div class="ok"><input type="submit" value="OK" class="ok_bottom"></div>
				<input type="hidden" name="login_id" size="20" value="${du.login_id}">

			</form>
		</div>
	</div>
</body>
</html>

