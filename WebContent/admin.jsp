<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="jp.ac.hal.Model.*"
    import="java.util.*"%>
<% List<User> uList = Dao.getInstance().fetchAllUser(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="page-header">
			<h1>ユーザ管理</h1>
		</div>
		<% for (int i = 0 ; i < uList.size() ; i++) { %>
		<div class="panel panel-default">
			<div class="panel-heading">
				<form action="Admin" method = "post">
				<span style="padding: 0 20px;">ユーザ名 : <%=uList.get(i).getUserName() %></span>
				<input type="hidden" name="<%=uList.get(i).getUserName() %>"/>
				<input type="submit" value="削除"/>
				</form>
			</div>
		</div>
		<% } %>

</body>
</html>