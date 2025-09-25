<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Todo 목록</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-5">

<h1 class="mb-4">📋 Todo 목록</h1>

<div class="mb-3">
    <a href="/todos/new" class="btn btn-primary">할 일 추가</a>
</div>

<table class="table table-hover">
    <thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>날짜 / 시간</th>
        <th>내용</th>
        <th>카테고리</th>
        <th>완료여부</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="todo" items="${todoList}">
        <tr>
            <td>${todo.id}</td>
            <td>
                <%
                    java.time.LocalDateTime dueDate = (java.time.LocalDateTime) pageContext.getAttribute("todo").getClass().getMethod("getDueDate").invoke(pageContext.getAttribute("todo"));
                    String formattedDate = dueDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                %>
                <%= formattedDate %>
            </td>
            <td><a href="/todos/${todo.id}">${todo.content}</a></td>
            <td>${todo.category}</td>
            <td>
                <form action="/todos/${todo.id}/toggle" method="post" style="display:inline;">
                    <button type="submit" style="background:none; border:none; padding:0; cursor:pointer; font-size:1.2em;">
                        <c:choose>
                            <c:when test="${todo.completed}">
                                ✔️
                            </c:when>
                            <c:otherwise>
                                ○
                            </c:otherwise>
                        </c:choose>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
