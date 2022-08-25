<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 25/08/2022
  Time: 3:25 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Xóa sản phẩm</h3>
<p>
    <a href="/products">Về trang quản lý sản phẩm</a>
</p>
<form method="post">
    <p>Bạn có chắc muốn xóa không ?</p>
    <fieldset>
        <legend>Thông tin sản phẩm</legend>
        <table>
            <tr>
                <td>Mã sản phẩm:</td>
                <td>${product.getId()}</td>
            </tr>
            <tr>
                <td>Tên sản phẩm:</td>
                <td>${product.getProductName()}</td>
            </tr>
            <tr>
                <td>Giá tiền:</td>
                <td>${product.getProductPrice()}</td>
            </tr>
            <tr>
                <td>Mô tả:</td>
                <td>${product.getProductDescription()}</td>
            </tr>
            <tr>
                <td>Hãng sản xuất:</td>
                <td>${product.getManufacturer()}</td>
            </tr>
            <tr>
                <td><input type="submit" value="Xóa"></td>
                <td><a href="/product">Không</a></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
