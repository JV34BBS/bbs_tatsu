<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="jp.ac.hal.Model.*"
    import="java.util.*"%>
<%	List<User> uList = Dao.getInstance().fetchAllUser();
	String msg = "";
	msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Admin</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<div class="page-header">
		<h1>BBS <small>ユーザ管理</small></h1>
		<div class="page-header">
			<div style="text-align: right;">
				<small>ログインユーザ名 : <%=((User)session.getAttribute("logUser")).getUserName()%></small>
				<form action="Bbs" class="form-inline" style="display: inline-block;">
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-xs" name="logout">ログアウト</button>
					</div>
				</form>
			</div>
		</div>
		<%
			if(msg != null){
		%>
		<div class="alert alert-danger" role="alert"><%=msg %></div>
		<%
			}
		%>
</div>
		<% for (int i = 0 ; i < uList.size() ; i++) { %>
		<div class="panel-heading">
			<form action="Admin" method = "post">
				<div class ="row">
					<div class ="col-xs-1">
						ユーザ名 :
					</div>
					<div class="col-xs-3">
						<%=uList.get(i).getUserName() %>
					</div>
					<div class="col-xs-4">
						<input type="hidden" name="user" value="<%=uList.get(i).getUserName() %>"/>
						<input type="submit" value="削除" class="btn btn-primary btn-xm"/>
					</div>
				</div>
			</form>
		</div>
		<% } %>
	</div>
</body>
</html>