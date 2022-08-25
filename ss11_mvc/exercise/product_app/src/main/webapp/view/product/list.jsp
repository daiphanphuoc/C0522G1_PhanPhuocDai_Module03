<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 25/08/2022
  Time: 2:56 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
</head>
<body>
<form action="/products">
    <input type="text" name="name" placeholder="search"/>
    <input type="submit" name="action" value="search">
</form>
<a href="/products?action=add">
    <button>Thêm mới</button>
</a>
<jsp:useBean id="products" scope="request" type="java.util.List"/>
<%--<c:if test="${products}!=null">--%>
<h3>Danh sách sản phẩm</h3>
<table class="table-striped">
    <tr>
        <td>STT</td>
        <td>Name</td>
        <td>Price</td>
        <td>Description</td>
        <td>Manufacturer</td>
        <td>update</td>
        <td>delete</td>
        <td>view</td>
    </tr>

    <c:forEach varStatus="stt" var="product" items="${products}">
        <tr>
            <td>${stt.count}</td>
            <td>${product.getProductName()}</td>
            <td>${product.getProductPrice()}</td>
            <td>${product.getProductDescription()}</td>
            <td>${product.getManufacturer()}</td>
            <td><a href="/products?action=update&id=${product.getId()}">
                <button name="${product.getId()}" type="submit" value="update">Update</button>
            </a></td>
            <td><a href="/products?action=delete&id=${product.getId()}">
                <button name="${product.getId()}" type="submit" value="delete">Delete</button>
            </a></td>
            <td><a href="/products?action=view&id=${product.getId()}">
                <button name="${product.getId()}" type="submit" value="view">View</button>
            </a></td>

        </tr>
    </c:forEach>
</table>

<%--</c:if>--%>

</body>
</html>
