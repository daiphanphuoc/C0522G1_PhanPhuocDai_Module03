<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 24/08/2022
  Time: 8:13 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<h2>Currency Converter</h2>
<form method="post" action="converter">
    <label>Rate: </label><br/>
    <input type="text" name="rate" placeholder="RATE" value="23000"/><br/>
    <label>USD: </label><br/>
    <input type="text" name="usd" placeholder="USD" value="0"/><br/>
    <input type="submit" id="submit" value="Converter"/>
</form>
</body>
</html>
