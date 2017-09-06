<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ユーザ情報詳細参照</title>
	<link rel="stylesheet" type="text/css" href="css/userDetail.css">
</head>
<body>
	<div class="main">
		<div id="topRight">
			<span class="username">${sessionScope.ub.getName()}さん</span>
			<a href="Logout"><span class="logout">ログアウト</span></a>
		</div>

		<h1>ユーザ情報詳細参照</h1>
		<form action="" method="post">
			<ul>
					<li class="elem">
						<label for="login_id">ログインID</label>
<%-- 						${requestScope.du.login_id}</li> --%>
 						${du.login_id}</li>
					<li class="elem">
						<label for="login_name">ユーザ名</label>
						${requestScope.du.getName()}</li>
					<li class="elem">
						<label for="startBirth">生年月日</label>
						${requestScope.du.getBirth_date()}</li>
					<li class="elem">
						<label for="resister_day">登録日時</label>
						${requestScope.du.getCreate_date()}</li>
					<li class="elem">
						<label for="update_day">更新日時</label>
						${requestScope.du.getUpdate_date()}</li>
				</ul>
			<div class="toUserlist"><a href="UserList">戻る</a></div>
		</form>
	</div>
</body>
</html>