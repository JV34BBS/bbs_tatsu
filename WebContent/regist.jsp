<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>BBS | 新規登録</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<header></header>
	<div class="container">
		<div class="page-header">
			<h1>BBS <small>新規登録</small></h1>
		</div>
		<form action="Regist" method="POST">
			<div class="form-group">
				<label for="InputPassword">ユーザ名</label>
				<input type="text" class="form-control" id="InputPassword" name="name">
			</div>
			<div class="form-group">
				<label for="InputEmail">Email</label>
				<input type="email" class="form-control" id="InputEmail" placeholder="example@example.com" name="email">
			</div>
			<div class="form-group">
				<label for="InputPassword">パスワード</label>
				<input type="password" class="form-control" id="InputPassword" name="pass">
			</div>
			<button type="submit" class="btn btn-primary">登録</button>
		</form>
	</div>
	<footer></footer>
</body>
</html>