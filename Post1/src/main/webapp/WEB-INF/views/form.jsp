<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${post != null ? "글 수정" : "글 작성"}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-5">

<h1 class="mb-4">
    <c:choose>
        <c:when test="${post != null}">✏️ 글 수정</c:when>
        <c:otherwise>📝 글 작성</c:otherwise>
    </c:choose>
</h1>

<form method="post" action="<c:choose>
        <c:when test="${post != null}">/posts/${post.id}/edit</c:when>
        <c:otherwise>/posts</c:otherwise>
    </c:choose>">

    <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input type="text" class="form-control" name="title" id="title" value="${post.title}" required/>
    </div>

    <div class="mb-3">
        <label for="content" class="form-label">내용</label>
        <textarea class="form-control" name="content" id="content" rows="5" required>${post.content}</textarea>
    </div>

    <div class="mb-3">
        <label for="writer" class="form-label">작성자</label>
        <input type="text" class="form-control" name="writer" id="writer" value="${post.writer}" required/>
    </div>

    <button type="submit" class="btn btn-success">
        <c:choose>
            <c:when test="${post != null}">수정 완료</c:when>
            <c:otherwise>작성 완료</c:otherwise>
        </c:choose>
    </button>

    <a href="/posts" class="btn btn-secondary">취소</a>
</form>

</body>
</html>
