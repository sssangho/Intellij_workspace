<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시판 목록</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-5">

<h1 class="mb-4">📋 게시판 목록</h1>

<div class="mb-3">
    <a href="/posts/new" class="btn btn-primary">글쓰기</a>
</div>

<table class="table table-hover">
    <thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>제목</th>
        <th>작성자</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${posts}">
        <tr>
            <td>${post.id}</td>
            <td><a href="/posts/${post.id}">${post.title}</a></td>
            <td>${post.writer}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
