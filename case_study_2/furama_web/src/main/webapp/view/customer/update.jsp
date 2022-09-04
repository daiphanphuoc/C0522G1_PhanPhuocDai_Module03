<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 30/08/2022
  Time: 8:47 SA
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
    <div  class="row">
        <jsp:include page="/view/header.jsp"/>
    </div>


    <div class="row ">
        <div class="col-sm-3">
            <jsp:include page="/view/left.jsp"/>
        </div>


        <div class="col-sm-9">

            <div class="row">
                <h3 class="text-center">Cập nhập khách hàng</h3>
                <form  method="post">
                    <fieldset>
                        <legend style="color: crimson">Thông tin khách hàng</legend>
                        <table>
                            <tr>
                                <td>Tên khách hàng:</td>
                                <td>
                                    <input name="name" value="${customer.getName()}">
                                    <input hidden name="id" value="${customer.getIDCustomer()}">
                                </td>
                            </tr>
                            <tr>
                                <td>Giới tính:</td>
                                <td><input name="gender" value="${customer.isSex()}"></td>
                            </tr>
                            <tr>
                                <td>Ngày sinh:</td>
                                <td><input type="date" name="dateOfBirth" value="${customer.getBirthday()}"></td>
                            </tr>
                            <tr>
                                <td>Số CMND:</td>
                                <td><input name="idCard" value="${customer.getIDCitizen()}"></td>
                            </tr>
                            <tr>
                                <td>Số điện thoại:</td>
                                <td><input name="phoneNumber" value="${customer.getPhone()}"></td>
                            </tr>
                            <tr>
                                <td>Email:</td>
                                <td><input name="email" value="${customer.getEmail()}"></td>
                            </tr>
                            <tr>
                                <td>Địa chỉ:</td>
                                <td><input name="address" value="${customer.getAddress()}"></td>
                            </tr>
                            <tr>
                                <td>Mã loại khách hàng:</td>
                                <td><input type="number" name="customerTypeId" value="${customer.getCustomerType().getId()}"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="update"></td>
                            </tr>
                        </table>
                    </fieldset>
                </form>

            </div>
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


