<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<h2>Shopping Cart</h2>
<a href="/">Back to Products</a>
<table border="1">
    <tr><th>Name</th><th>Price</th><th>Quantity</th><th>Total</th><th>Action</th></tr>
    <c:forEach var="item" items="${cartItems}">
        <tr>
            <td>${item.product.name}</td>
            <td>${item.product.price}</td>
            <td>${item.quantity}</td>
            <td>${item.quantity * item.product.price}</td>
            <td>
                <form action = "/cart/delete" method = "post">
                    <input type="hidden" name="cartItemId" value="${item.id}"/>
                    <input type="submit" value="Remove" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
