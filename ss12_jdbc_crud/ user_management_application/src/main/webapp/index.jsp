<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 26/08/2022
  Time: 8:17 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%-- <c:redirect url="/users"></c:redirect>--%>
<%
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/users");
    requestDispatcher.forward(request, response);
%>
</body>
</html>
