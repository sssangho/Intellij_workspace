<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>할 일 상세</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-5">

<h1 class="mb-4">${todo.content}</h1>

<p><strong>기한:</strong>
    <%
        Object todoObj = request.getAttribute("todo");
        if (todoObj != null) {
            java.time.LocalDateTime dueDate = (java.time.LocalDateTime) todoObj.getClass()
                    .getMethod("getDueDate").invoke(todoObj);
            String formattedDate = dueDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            out.print(formattedDate);
        } else {
            out.print("없음");
        }
    %>
</p>

<p><strong>카테고리:</strong> ${todo.category}</p>
<p><strong>완료 여부:</strong> ${todo.completed ? "완료" : "미완료"}</p>

<div class="mt-4">
    <a href="/todos/${todo.id}/edit" class="btn btn-warning">수정</a>

    <form action="/todos/${todo.id}/delete" method="post" class="d-inline">
        <button type="submit" class="btn btn-danger">삭제</button>
    </form>

    <a href="/todos" class="btn btn-secondary">목록</a>
</div>

</body>
</html>
