<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="jp.ac.hal.Model.*"
    import="java.util.*"%>
<%
	if (((User)session.getAttribute("logUser")) == null) { response.sendRedirect("login.jsp"); }
	List<Comment> cList = Dao.getInstance().fetchAllComment();
%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>BBS | bbs</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- 全件選択 -->
	<script>
		function Click_Sub1(check)
		{
			for(var i=0;i<document.all.deleName.length;i++)
				{
					document.all.deleName[i].checked = check;
				}
		}
	</script>
</head>
<body>
	<header></header>
	<div class="container">
		<div class="page-header">
			<h1>BBS <small>bbs</small> </h1>
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
			if (request.getAttribute("msg") != null) {
		%>
		<div class="alert alert-danger" role="alert"><%=request.getAttribute("msg")%></div>
		<%
			}
		%>
		
		<!-- 管理者側の掲示板 -->
		<% if(((User)session.getAttribute("logUser")).getAdmin() == 0){ %>
			<form action="Bbs" method="POST" class="" style="">
				<div class="form-group">
					<input class="senntaku" type="button" onclick="Click_Sub1(true)" value="全件選択" class="btn btn-default btn-xs">
					<input class="senntaku" type="button" onclick="Click_Sub1(false)" value="全件取り消し" class="btn btn-default btn-xs">
					<button type="submit" name="deleteSeleBtn" class="btn btn-default btn-xs">削除</button>
				</div>
		
			<% for (int i = 0 ; i < cList.size() ; i++) { %>
			<div class="panel panel-default">
				<div class="panel-heading">
					<span style="font-weight: bold;"><%=cList.get(i).getId() %></span>
					<span style="padding: 0 20px;">投稿者 : <%=cList.get(i).getUserName() %></span>
					<span style="padding: 0 50px;">投稿日時 : <%=cList.get(i).getCreatedAt() %></span>
						<div class="checkb" style="display:inline-block;">
							<input type="checkbox" name="deleName" value="<%= cList.get(i).getId() %>">	
						</div>
				</div>
				<div class="panel-body">
					<%=cList.get(i).getComment()%>
				</div>
			</div>
			<% } %>
			</form>
			
		<% }else if(((User)session.getAttribute("logUser")).getAdmin() == 1){ %>
		<!-- ユーザ側の掲示板 -->
		
			<% for (int i = 0 ; i < cList.size() ; i++) { %>
			<div class="panel panel-default">
				<div class="panel-heading">
					<span style="font-weight: bold;"><%=cList.get(i).getId() %></span>
					<span style="padding: 0 20px;">投稿者 : <%=cList.get(i).getUserName() %></span>
					<span style="padding: 0 50px;">投稿日時 : <%=cList.get(i).getCreatedAt() %></span>
					<%
						if (((User)session.getAttribute("logUser")).getUserName().equals(cList.get(i).getUserName())) {
					%>
					<form action="Bbs" method="POST" class="form-inline" style="display: inline-block;">
						<div class="form-group">
							<input type="hidden" name="comment_id" value="<%= cList.get(i).getId() %>">
							<button type="submit" name="deleteBtn" class="btn btn-default btn-xs">削除</button>	
						</div>
					</form>
					<%  } %>
				</div>
				<div class="panel-body">
					<%=cList.get(i).getComment()%>
				</div>
			</div>
			<% } %>
		<% } %>
			
		<form action="Bbs" method="POST">
			<div class="form-group">
				<label for="InputEmail">コメント</label>
				<textarea class="form-control" id="comment" name="comment">
				</textarea>
			</div>
			<input type="hidden" name="userName" value="<%=((User)session.getAttribute("logUser")).getUserName()%>">
			<button type="submit" name="submit" class="btn btn-primary">書き込む</button>
		</form>
	</div>
	<footer></footer>
</body>
</html>