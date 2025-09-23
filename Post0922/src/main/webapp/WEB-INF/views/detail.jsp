<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시글 상세</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-5">

<h1 class="mb-4">${post.title}</h1>

<p><strong>작성자:</strong> ${post.writer}</p>
<p class="border p-3">${post.content}</p>

<div class="mt-4">
    <a href="/posts/${post.id}/edit" class="btn btn-warning">수정</a>

    <form action="/posts/${post.id}/delete" method="post" class="d-inline">
        <button type="submit" class="btn btn-danger">삭제</button>
    </form>

    <a href="/posts" class="btn btn-secondary">목록</a>
</div>

<hr/>

<!-- 댓글 목록 -->
<h4 class="mt-5">💬 댓글</h4>
<div class="mb-3">
    <c:forEach var="comment" items="${comments}">
        <div class="border rounded p-3 mb-2">
            <strong>${comment.writer}</strong>
            <small class="text-muted float-end">${comment.createdAt}</small>
            <p class="mb-0">${comment.content}</p>
        </div>
    </c:forEach>
</div>

<!-- 댓글 작성 폼 -->
<form method="post" action="/posts/${post.id}/comments" class="mt-4">
    <div class="mb-2">
        <label class="form-label">작성자</label>
        <input type="text" name="writer" class="form-control" required/>
    </div>
    <div class="mb-2">
        <label class="form-label">내용</label>
        <textarea name="content" class="form-control" rows="3" required></textarea>
    </div>
    <button type="submit" class="btn btn-primary">댓글 작성</button>
</form>

</body>
</html>