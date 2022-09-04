<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 03/09/2022
  Time: 7:11 CH
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

  <title>Furama</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
  <style>
    a {
      text-decoration: none;
    }

    a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>


<div class="container-fluid">
  <div class="row">
    <jsp:include page="/view/header.jsp"/>
  </div>


  <div class="row ">
    <div class="col-sm-3">
      <jsp:include page="/view/left.jsp"/>
    </div>


    <div class="col-sm-9">

      <h3>Thêm mới hợp đồng</h3>


      <form action="/contract?action=create" method="post">
        <fieldset>
          <legend style="color: crimson">Thông tin hợp đồng</legend>
          <table>
            <tr>
              <td>Ngày bắt  đầu:</td>
              <td><input type="date" id="startDay" class="form-control" name="startDay"></td>
            </tr>
            <tr>
              <td>Ngày kết thúc:</td>
              <td><input type="date" id="endDay" class="form-control" name="endDay"></td>
            </tr>
            <tr>
              <td>Tiền trả trước:</td>
              <td><input type="text" id="deposit" class="form-control" placeholder="Input deposit" name="deposit"></td>
            </tr>
            <tr>
              <td>Nhân viên:</td>
              <td>
                <select id="employee" class="form-control" name="employee">
                  <c:forEach var="employee" items="${employeeList}">
                    <option value="${employee.iDEmployee}">${employee.name}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td>Khách hàng:</td>
              <td>
                <select id="customer" class="form-control" name="customer">
                  <c:forEach var="customer" items="${customerList}">
                    <option value="${customer.iDCustomer}">${customer.name}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td>Dịch vụ</td>
              <td>
                <select id="facility" class="form-control" name="facility">
                  <c:forEach var="facility" items="${facilityList}">
                    <option value="${facility.getIDFacility()}">${facility.nameFacility}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td></td>
              <td><input type="submit" value="Create"></td>
            </tr>
          </table>
        </fieldset>
      </form>
      <%--giao diện cho từng trang--%>

    </div>

  </div>
  <div class="row">
    <%@include file="/view/footer.jsp" %>
  </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>

