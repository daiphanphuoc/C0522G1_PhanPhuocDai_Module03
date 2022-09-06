<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 06/09/2022
  Time: 1:44 CH
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
        <jsp:include page="/header.jsp"/>
    </div>


    <div class="row ">
        <div class="col-sm-3">
            <jsp:include page="/left.jsp"/>
        </div>


        <div class="col-sm-9">


            <form class="w-50 border border-2 border-success p-3 rounded" action="/servlet?action=create"
                  method="post">
                <div class="form-group">
                    <label for="name" class="h6">Name:</label>
                    <div class="input-group">
                        <input type="text" id="name" class="form-control" placeholder="Input name" name="name" required
                               pattern="^[A-Z][a-z]+( [A-Z][a-z]*)+$"
                               title="Tên khách hàng không được chứa số và các kí tự đầu tiên của mỗi từ phải viết hoa.">
                    </div>
                </div>

                <div class="mt-3 form-group">
                    <label for="dateOfBirth" class="h6">Date of Birth:</label>
                    <input type="date" id="dateOfBirth" class="form-control" name="dateOfBirth" min="${minAge}"
                           max="${maxAge}">
                </div>

                <div class="mt-3 form-group">
                    <label class="h6">Gender:</label>
                    <div class="d-flex">
                        <label class="d-block me-4">
                            <input type="radio" value="1" name="gender" checked> Male
                        </label>
                        <label class="d-block">
                            <input type="radio" value="0" name="gender"> Female
                        </label>
                    </div>
                </div>

                <div class="mt-3 form-group">
                    <label for="idCard" class="h6">Id card:</label>
                    <div class="input-group">
                        <input type="text" id="idCard" class="form-control" placeholder="Input Id card" name="idCard"
                               required pattern="^\d{9}|\d{12}$"
                               title="Số CMND phải đúng định dạng XXXXXXXXX hoặc XXXXXXXXXXXX (X là số 0-9)."/>
                    </div>
                </div>

                <div class="mt-3 form-group">
                    <label for="phone" class="h6">Phone number:</label>
                    <div class="input-group">
                        <input type="text" id="phone" class="form-control" placeholder="Input Phone number" name="phone"
                               required pattern="^09[01]\d{7}|[(]84[)][+]9[01]\d{7}$"
                               title="Số điện thoại phải đúng định dạng 090xxxxxxx hoặc 091xxxxxxx hoặc (84)+90xxxxxxx hoặc (84)+91xxxxxxx.">
                    </div>
                </div>

                <div class="mt-3 form-group">
                    <label for="email" class="h6">Email:</label>
                    <div class="input-group">
                        <input type="text" id="email" class="form-control" placeholder="Input Email" name="email"
                               required
                               pattern="^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]{2,}(\.[A-Za-z0-9]{2,}){1,2}$"
                               title="Địa chỉ email phải đúng định dạng.">
                    </div>
                </div>

                <div class="mt-3 form-group">
                    <label for="address" class="h6">Address:</label>
                    <div class="input-group">
                        <input type="text" id="address" class="form-control" placeholder="Input Address" name="address"
                               required pattern="^.+$" title="Vui lòng không để trống địa chỉ.">
                    </div>
                </div>

                <div class="mt-3 form-group">
                    <label class="h6" for="customerType">Customer Type:</label>
                    <div class="input-group">
                        <select id="customerType" class="form-control" name="customerType">
                            <c:forEach var="customerType" items="${customerTypeList}">
                                <option value="${customerType.customerTypeId}">${customerType.customerTypeName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="mt-3 text-center">
                    <button class="btn btn-info btn-outline-success btn-sm border border-2 border-success text-dark">
                        Save
                    </button>
                </div>
            </form>

        </div>

    </div>
    <div class="row">
        <%@include file="/footer.jsp" %>
    </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
