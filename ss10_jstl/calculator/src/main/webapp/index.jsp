<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 24/08/2022
  Time: 2:44 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>Simple Calculator</h1>
  <form method="get" action="/calculator">
    <fieldset>
      <legend>Calculator</legend>
      <table>
        <tr>
          <td>First operand: </td>
          <td><input name="firstOperand" type="text"/></td>
        </tr>
        <tr>
          <td>Operator: </td>
          <td>
            <select name="operator">
              <option value="ADDITION">ADDITION</option>
              <option value="SUBTRACTION">SUBTRACTION</option>
              <option value="MULTIPLICATION">MULTIPLICATION</option>
              <option value="DIVISION">DIVISION</option>
            </select>
          </td>
        </tr>
        <tr>
          <td>Second operand: </td>
          <td><input name="secondOperand" type="text"/></td>
        </tr>
        <tr>
          <td></td>
          <td><input type="submit" value="Calculate"/></td>
        </tr>
      </table>
    </fieldset>
  </form>
  </body>
</html>
