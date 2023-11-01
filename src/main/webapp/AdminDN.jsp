<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-81">
<title>Log in</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.login-wrapper {
	width: 400px;
	height: 100vh;
	margin: 0 auto;
	display: flex;
	align-items: center;
}

.login-form {
	border-radius: 10px;
	border: 1px solid #ccc;
	width: 100%;
	padding: 14px 20px;
}

.login-input {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
	outline: none;
	margin-bottom: 10px;
}

.input-wrap {
	display: flex;
}

.btn-login {
	width: 100%;
	margin-top: 16px;
	padding: 6px;
	border-radius: 6px;
	outline: none;
	border: 1px solid transparent;
	background-color: #26a2ef;
	color: #fff;
	margin-bottom: 20px;
}

.btn-login:hover {
	background-color: #2181bd;
}

.btn-el{
	color: #000;
	margin-right: 6px;
}
</style>
</head>
<body>
	<div class="login-wrapper" >
		<div class="login-form">
		<form method="post" action="AdminDNController" >
			<p class="h3" style="margin-bottom: 24px; text-align: center;">Login</p>
			<p style="margin-bottom: 4px">Username:</p>
			<input type="text" name="txtun" placeholder="Username"
				class="login-input" /> <br>

			<p style="margin-bottom: 4px;">Password:</p>
			<input type="password" name="txtpass" placeholder="Password"
				class="login-input" /> <br> <input type="submit" name="but"
				value="Login" class="btn-login" />

			<%
			String tb =  request.getParameter("tb");
			if (tb != null)
				out.print("<b>Đăng nhập sai</b>");
			%>
		</form>
	</div>

</body>
</html>