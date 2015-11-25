<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	if(msg == null)
	{
		msg = "不正なアクセスです。";
	}
%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>BBS | 登録完了</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/validator.js"></script>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>BBS <small>登録完了</small></h1>
		</div>
		<div class="alert alert-success" role="alert"><%= msg %></div>
		<a href="login.jsp">ログイン場面へ</a>
	</div>
</body>
</html>