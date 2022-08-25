<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 25/08/2022
  Time: 8:15 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Thông tin chi tiết sản phẩm</h3>
<p>
    <a href="/products">Về trang quản lý sản phẩm</a>
</p>
<fieldset>
    <legend>Thông tin sản phẩm</legend>
    <table>
        <tr>
            <td>Mã sản phẩm:</td>
            <td><input type="number" name="id" id="id" value="${product.getId()}"></td>
        </tr>
        <tr>
            <td>Tên sản phẩm:</td>
            <td><input type="text" name="name" id="name" value="${product.getProductName()}"></td>
        </tr>
        <tr>
            <td>Giá tiền:</td>
            <td><input type="number" name="price" id="price" value="${product.getProductPrice()}"></td>
        </tr>
        <tr>
            <td>Mô tả:</td>
            <td><input type="text" name="describe" id="describe" value="${product.getProductDescription()}"></td>
        </tr>
        <tr>
            <td>Hãng sản xuất:</td>
            <td><input type="text" name="producer" id="producer" value="${product.getManufacturer()}"></td>
        </tr>
    </table>
</fieldset>

</body>
</html>
