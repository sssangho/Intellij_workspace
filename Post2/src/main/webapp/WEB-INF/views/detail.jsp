<%@ page contentType="text/html;charset=UTF-8" %>
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

</body>
</html>
