<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>서비스 목록</title></head>
<body>
<h2>서비스 목록</h2>
<a href="/services/new">새 서비스 추가</a><br><br>

<table border="1" cellpadding="5" cellspacing="0">
    <tr><th>ID</th><th>서비스명</th><th>설명</th><th>가격</th><th>삭제</th></tr>
    <c:forEach var="service" items="${services}">
        <tr>
            <td>${service.id}</td>
            <td>${service.name}</td>
            <td>${service.description}</td>
            <td>${service.price}</td>
            <td>
                <form action="/services/delete/${service.id}" method="post">
                    <input type="submit" value="삭제"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

