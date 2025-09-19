<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>예약 등록</title></head>
<body>
<h2>새 예약 등록</h2>
<form action="/reservations" method="post">
    고객명: <input type="text" name="customerName" required/><br><br>

    <c:forEach var="service" items="${services}">
        <input type="checkbox" name="serviceId" value="${service.id}"/> ${service.name} (₩${service.price})<br>
        수량/인원 수: <input type="number" name="quantity" min="1" value="1"/><br><br>
    </c:forEach>

    <input type="submit" value="예약 등록"/>
</form>
<a href="/reservations">예약 목록으로</a>
</body>
</html>

