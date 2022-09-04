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
    <div class="row">
        <jsp:include page="/view/header.jsp"/>
    </div>


    <div class="row ">
        <div class="col-sm-3">
            <jsp:include page="/view/left.jsp"/>
        </div>


        <div class="col-sm-9">

            <div class="row">
                <h3 class="text-center">Cập nhập nhân viên</h3>
                <form method="post">
                    <fieldset>
                        <legend style="color: crimson">Thông tin nhân viên</legend>
                        <table>
                            <tr>
                                <td>Tên nhân viên:</td>
                                <td>
                                    <input name="name" value="${employee.getName()}">
                                    <input hidden name="id" value="${employee.getIDEmployee()}">
                                </td>
                            </tr>
                            <tr>
                                <td>Giới tính:</td>
                                <td>
                                    <label class="d-block me-4">
                                        <input type="radio" value="true" name="gender" checked> Male
                                        <i class="fa-solid fa-mars"></i>
                                    </label>
                                    <label class="d-block">
                                        <input type="radio" value="false" name="gender" > Female
                                        <i class="fa-solid fa-venus"></i>
                                    </label>
                                    <%--<input name="gender" value="${employee.isSex()}"></td>--%>
                            </tr>
                            <tr>
                                <td>Ngày sinh:</td>
                                <td><input type="date" name="dateOfBirth" value="${employee.getBirthday()}"></td>
                            </tr>
                            <tr>
                                <td>Số CMND:</td>
                                <td><input name="idCard" value="${employee.getIDCitizen()}"></td>
                            </tr>
                            <tr>
                                <td>Số điện thoại:</td>
                                <td><input name="phoneNumber" value="${employee.getPhone()}"></td>
                            </tr>
                            <tr>
                                <td>Email:</td>
                                <td><input name="email" value="${employee.getEmail()}"></td>
                            </tr>
                            <tr>
                                <td>Địa chỉ:</td>
                                <td><input name="address" value="${employee.getAddress()}"></td>
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
                                <td><input type="number" name="salary" value="${employee.getSalary()}"></td>
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


