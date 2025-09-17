<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>ToDo List</title>
    <style>
        .completed {
            text-decoration: line-through;
            color: gray;
        }
    </style>
</head>
<body>
<h2>할 일 목록</h2>
<a href="add">할 일 추가</a>
<ul>
    <c:forEach var="todo" items="${todos}">
        <li>
            <a href="toggle/${todo.id}">
                <span class="${todo.completed ? 'completed' : ''}">
                    ${todo.title}
                </span>
                [${todo.completed ? '완료됨' : '미완료'}]
            </a>
            <a href="delete/${todo.id}">[삭제]</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>