<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head><title>예약 목록</title></head>
<body>
<h2>예약 목록</h2>
<a href="/reservations/new">새 예약 추가</a><br><br>

<table border="1" cellpadding="5" cellspacing="0">
    <tr><th>ID</th><th>고객명</th><th>예약일</th><th>상세보기</th><th>삭제</th></tr>
    <c:forEach var="reservation" items="${reservations}">
        <tr>
            <td>${reservation.id}</td>
            <td>${reservation.customerName}</td>
            <td><fmt:formatDate value="${reservation.reservationDate}" pattern="yyyy-MM-dd"/></td>
            <td><a href="/reservations/${reservation.id}">보기</a></td>
            <td>
                <form action="/reservations/delete/${reservation.id}" method="post">
                    <input type="submit" value="삭제"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
