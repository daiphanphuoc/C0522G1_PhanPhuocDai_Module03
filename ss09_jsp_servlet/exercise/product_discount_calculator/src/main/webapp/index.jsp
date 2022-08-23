<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 23/08/2022
  Time: 3:11 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="/product" method="post">
    <input name="product" type="text" placeholder="mô tả sản phẩm">
    <input name="price" type="text" placeholder="giá niêm yết">
    <input name="percent" type="text" placeholder="tỷ lệ chiết khấu %">
    <input id="sub" type="submit" value="submit">
  </form>
  </body>
</html>
