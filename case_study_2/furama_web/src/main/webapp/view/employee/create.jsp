<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 01/09/2022
  Time: 6:03 CH
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

            <h3>Thêm mới nhân viên</h3>


            <form class="w-50 border border-2 border-success p-3 bg-warning rounded" action="/employee?action=create" method="post">
                <fieldset>
                    <legend style="color: crimson">Thông tin nhân viên</legend>
                    <table>
                        <tr>
                            <td>Tên khách hàng:</td>
                            <td><input name="name" ></td>
                        </tr>
                        <tr>
                            <td>Giới tính:</td>
                            <td><input name="gender" ></td>
                        </tr>
                        <tr>
                            <td>Ngày sinh:</td>
                            <td><input name="dateOfBirth"type="date" ></td>
                        </tr>
                        <tr>
                            <td>Số CMND:</td>
                            <td><input name="idCard" ></td>
                        </tr>
                        <tr>
                            <td>Số điện thoại:</td>
                            <td><input name="phoneNumber" ></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><input name="email" ></td>
                        </tr>
                        <tr>
                            <td>Địa chỉ:</td>
                            <td><input name="address" ></td>
                        </tr>
                        <tr>
                            <td><label for="degree">Trình độ:</label></td>
                            <td><select id="degree" name="degree">
                                <option value="${employee.getDegree().getId()}">${employee.getDegree().getName()}</option>
                                <c:forEach var="educationDegree" items="${degreeList}">
                                    <option value="${educationDegree.getId()}">${educationDegree.getName()}</option>
                                </c:forEach>
                            </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="position">Chức vụ/ vị trí:</label></td>
                            <td>
                                <select id="position" name="position">
                                    <option value="${employee.getPosition().getId()}">${employee.getPosition().getName()}</option>
                                    <c:forEach var="position" items="${positionList}">
                                        <option value="${position.getId()}">${position.getName()}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="division">Bộ phận:</label></td>
                            <td>
                                <select id="division" name="division">
                                    <option value="${employee.getDivision().getId()}">${employee.getDivision().getName()}</option>
                                    <c:forEach var="division" items="${divisionList}">
                                        <option value="${division.getId()}">${division.getName()}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Lương:</td>
                            <td><input name="salary" type="number"></td>
                        </tr>
                        <tr>
                            <td>Tài khoản:</td>
                            <td><input name="userName" ></td>
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
