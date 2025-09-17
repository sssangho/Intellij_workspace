<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>할 일 추가</title></head>
<body>
<h2>할 일 추가</h2>
<form action="add" method="post">
    <input type="text" name="title" required />
    <input type="submit" value="추가" />
</form>
<a href="/">뒤로</a>
</body>
</html>