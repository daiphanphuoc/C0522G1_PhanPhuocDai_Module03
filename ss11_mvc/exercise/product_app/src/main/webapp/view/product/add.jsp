<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 25/08/2022
  Time: 2:58 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Thêm thông tin sản phẩm</h3>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/products">Về trang chủ</a>
</p>
<form method="post">
    <fieldset>
        <legend>Thông tin sản phẩm</legend>
        <table>
            <tr>
                <td>Mã sản phẩm:</td>
                <td><input type="number" name="id" id="id"></td>
            </tr>
            <tr>
                <td>Tên sản phẩm:</td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Giá tiền:</td>
                <td><input type="number" name="price" id="price"></td>
            </tr>
            <tr>
                <td>Mô tả:</td>
                <td><input type="text" name="describe" id="describe"></td>
            </tr>
            <tr>
                <td>Hãng sản xuất:</td>
                <td><input type="text" name="producer" id="producer"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Thêm sản phẩm"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
