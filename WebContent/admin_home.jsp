<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="jp.ac.hal.Model.*"
    import="java.util.*"
%>
<%
	if (((User)session.getAttribute("logUser")) == null) { response.sendRedirect("login.jsp"); }
	List<Comment> cList = Dao.getInstance().fetchAllComment();
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>管理者HOME</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<header></header>
	<div class="container">
		<div class="page-header">
			<h1>管理者HOME</h1>
			<div style="text-align: right;">
				<small>ログインユーザ名 : <%=((User)session.getAttribute("logUser")).getUserName()%></small>
				<form action="Bbs" class="form-inline" style="display: inline-block;">
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-xs" name="logout">ログアウト</button>	
					</div>
				</form>
			</div>
		</div>
		

		<a class="btn btn-primary" href="admin.jsp" ><i class="glyphicon glyphicon-cog"></i>ユーザ管理</a>
		<a class="btn btn-primary" href="bbs.jsp" role="button"><i class="glyphicon glyphicon-cog"></i>コメント管理</a>
		
		
		
	</div>
	<footer></footer>
</body>
</html>