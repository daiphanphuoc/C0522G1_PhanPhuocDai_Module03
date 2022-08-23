<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 23/08/2022
  Time: 2:29 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <style type="text/css">
      .login {
        height:180px; width:230px;
        margin:0;
        padding:10px;
        border:1px #CCC solid;
      }
      .login input {
        padding:5px; margin:5px
      }
    </style>
  </head>
  <body>
  <div class="login">
    <form action="/login" method="post">
      <input name="login" type="text" placeholder="login">
      <input name="password" type="password" placeholder="password">
      <input name="submit" type="submit" value="Sign in" >
    </form>
  </div>

  </body>
</html>
