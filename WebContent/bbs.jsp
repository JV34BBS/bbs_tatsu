<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>BBS | bbs</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<header></header>
	<div class="container">
		<div class="page-header">
			<h1>BBS <small>bbs</small></h1>
		</div>
		<form>
			<div class="form-group">
				<label for="InputEmail">コメント</label>
				<textarea class="form-control" id="comment" name="comment">
				</textarea>
			</div>
			<button type="submit" class="btn btn-primary">書き込む</button>
		</form>
	</div>
	<footer></footer>
</body>
</html>