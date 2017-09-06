<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/login.css">
<title>ログイン画面</title>
</head>
<body>
		<div id="elem">
		<h1>ログイン画面</h1> <span class="br2"></span>

<%
System.out.println("login.jsp");
String error = (String)request.getAttribute("error_message");
if(error != null){%> <div class="error_message"><%=error %> </div>
<%}%>
<%
String finish = (String)request.getAttribute("finish_message");
if(finish != null){%> <div class="finish_message"><%=finish %> </div>
<%}%>

		<span class="br"></span>
		<form action="Login" method="post">
			<ul>
				<li id="login_id">
					<label for="login_id">ログインID</label>
					<input type="text" name="login_id"  size="25">
				</li>
				<li id="login_password">
					<label for="login_password">パスワード</label>
					<input type="password" name="login_password"  size="25">
				</li>
			</ul>
			<span class="br2"></span>
	 		<input type="submit" value="ログイン" id="login">
		</form>
		</div>
</body>
</html>