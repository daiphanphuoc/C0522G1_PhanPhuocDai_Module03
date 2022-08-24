<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 24/08/2022
  Time: 3:14 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Phan Phước Đại</p>
<c:out value="${firstNumber}"></c:out>
<c:out value="${operator}"></c:out>
<%--<c:choose>
    <c:when test="${opertor=='ADDITION'}"> <c:out value=" + "></c:out></c:when>
    <c:when test="${opertor=='SUBTRACTION'}"> <c:out value=" - "></c:out> </c:when>
    <c:when test="${opertor=='MULTIPLICATION'}"><c:out value=" * "></c:out> </c:when>
    <c:otherwise> <c:out value=" / "></c:out> </c:otherwise>
</c:choose>--%>
<c:out value="${secondNumber}"></c:out>
<c:out value=" = "></c:out>
<c:out value="${result}"></c:out>
</body>
</html>
