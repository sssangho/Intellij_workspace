<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Simple Board</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 40px;
            background-color: #f5f5f5;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        form {
            background: #fff;
            padding: 25px;
            margin-bottom: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }
        input[type="text"], textarea {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }
        button, a.btn {
            display: inline-block;
            background-color: #007bff;
            color: white;
            padding: 10px 16px;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
            transition: background 0.2s ease-in-out;
        }
        a.btn-danger {
            background-color: #dc3545;
        }
        a.btn:hover, button:hover {
            opacity: 0.9;
        }
        table {
            width: 100%;
            background: white;
            border-collapse: collapse;
            border-radius: 6px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
        }
        th, td {
            padding: 14px;
            border-bottom: 1px solid #eee;
            text-align: left;
        }
        th {
            background: #f0f0f0;
        }
        td:last-child {
            white-space: nowrap;
        }
    </style>
</head>
<body>

<h1>📋 게시판</h1>

<!-- ✏️ 글쓰기 / 수정 폼 -->
<form method="post" action="/board/save">
    <input type="hidden" name="id" value="${form.id}">

    <label>제목</label>
    <input type="text" name="title" value="${form.title}" required>

    <label>내용</label>
    <textarea name="content" rows="5" required>${form.content}</textarea>

    <button type="submit">${form.id == null ? '등록' : '수정'}</button>

    <c:if test="${form.id != null}">
        <a href="/board" class="btn">취소</a>
    </c:if>
</form>

<!-- 📄 게시글 목록 -->
<table>
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>내용</th>
        <th>작성일</th>
        <th>관리</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.id}</td>
            <td>${item.title}</td>
            <td>${item.content}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty item.createdAt}">
                        <fmt:formatDate value="${item.createdAt}" pattern="yyyy-MM-dd HH:mm" />
                    </c:when>
                    <c:otherwise>
                        -
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="/board?editId=${item.id}" class="btn">수정</a>
                <a href="/board/delete/${item.id}" class="btn btn-danger"
                   onclick="return confirm('정말 삭제할까요?');">삭제</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
