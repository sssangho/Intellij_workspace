<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head><title>예약 상세</title></head>
<body>
<h2>예약 상세</h2>

고객명: ${reservation.customerName} <br>
예약일: <fmt:formatDate value="${reservation.reservationDate}" pattern="yyyy-MM-dd"/> <br><br>

<table border="1" cellpadding="5" cellspacing="0">
    <tr><th>서비스명</th><th>가격</th><th>수량</th><th>총액</th></tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>${item.service.name}</td>
            <td>₩${item.service.price}</td>
            <td>${item.quantity}</td>
            <td>₩${item.quantity * item.service.price}</td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/reservations">예약 목록으로</a>
</body>
</html>

