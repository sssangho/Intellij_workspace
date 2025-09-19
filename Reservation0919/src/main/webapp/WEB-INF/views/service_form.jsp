<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head><title>서비스 등록</title></head>
<body>
<h2>새 서비스 등록</h2>
<form action="/services" method="post">
    서비스명: <input type="text" name="name" required/><br><br>
    설명: <input type="text" name="description"/><br><br>
    가격: <input type="number" name="price" required/><br><br>
    <input type="submit" value="등록"/>
</form>
<a href="/services">목록으로</a>
</body>
</html>

