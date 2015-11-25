<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>BBS | ログイン</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<header></header>
	<div class="container">
		<div class="page-header">
			<h1>BBS <small>ログイン</small></h1>
		</div>
		<%
			String msg = (String)request.getAttribute("msg");
			if(msg != null){
		%>
		<div class="alert alert-danger" role="alert"><%=msg %></div>
		<% 
			}
		%>
		<form action="Login" method="POST">
			<div class="form-group">
				<label for="InputEmail">Email</label>
				<input type="email" class="form-control" id="InputEmail" placeholder="example@example.com" name="email">
			</div>
			<div class="form-group">
				<label for="InputPassword">パスワード</label>
				<input type="password" class="form-control" id="InputPassword" name="pass">
			</div>
			<button type="submit" class="btn btn-primary">ログイン</button>
		</form>
		<a class="btn btn-link" href="regist.jsp" role="button">新規登録はこちら</a>
	</div>
	<footer></footer>
</body>
</html>