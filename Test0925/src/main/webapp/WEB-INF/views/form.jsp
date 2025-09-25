<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title><c:choose>
        <c:when test="${todo != null}">할 일 수정</c:when>
        <c:otherwise>할 일 추가</c:otherwise>
    </c:choose></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-5">

<h1>
    <c:choose>
        <c:when test="${todo != null}">할 일 수정</c:when>
        <c:otherwise>할 일 추가</c:otherwise>
    </c:choose>
</h1>

<form action="<c:choose>
                 <c:when test='${todo != null}'>/todos/${todo.id}/edit</c:when>
                 <c:otherwise>/todos</c:otherwise>
             </c:choose>" method="post">

    <div class="mb-3">
        <label for="dueDate" class="form-label">날짜/시간</label>
        <input type="datetime-local" id="dueDate" name="dueDate" class="form-control"
               value="<c:if test='${todo != null}'>${todo.dueDate.toString().substring(0,16)}</c:if>" />
    </div>


    <div class="mb-3">
        <label for="content" class="form-label">내용</label>
        <input type="text" id="content" name="content" class="form-control"
               value="${todo != null ? todo.content : ''}" />
    </div>

    <div class="mb-3">
        <label for="category" class="form-label">카테고리</label>
        <input id="category" name="category" class="form-control" value="${todo != null ? todo.category : ''}">
    </div>

    <button type="submit" class="btn btn-primary">
        <c:choose>
            <c:when test="${todo != null}">수정</c:when>
            <c:otherwise>추가</c:otherwise>
        </c:choose>
    </button>
</form>

</body>
</html>
