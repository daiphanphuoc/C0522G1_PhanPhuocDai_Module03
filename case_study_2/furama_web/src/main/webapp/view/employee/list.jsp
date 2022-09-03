<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 01/09/2022
  Time: 1:06 CH
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
    <style>
        a {
            text-decoration: none;
            color: black;
        }

        a:hover {
            text-decoration: underline;
            color: #7abaff;
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
            <h2 class="text-center fw-bold">CUSTOMER LIST</h2>
            <div class="row">
                <%--
                <a href="/customer?action=create">
                    <button class="btn btn-success btn-sm my-2">
                        <span class="fa-solid fa-person-circle-plus text-light h5 my-auto me-1"></span> Add new Customer
                    </button>
                </a>
                --%>
                <fieldset>
                    <form action="/customer" method="get">
                        <input hidden type="text" name="action" value="search">
                        <input type="text" name="searchName" value="${searchName}" placeholder="Nhập tên khách hàng">
                        <input type="text" name="searchAddress" value="${searchAddress}" placeholder="Nhập địa chỉ">
                        <input type="text" name="searchType" value="${searchType}"
                               placeholder="Nhập tên loại khách hàng">
                        <button class="btn btn-success btn-sm " type="submit">Search</button>
                    </form>
                </fieldset>

            </div>
            <div>


                <table class="table table-striped table-bordered border border-3 border-secondary">
                    <tr class="text-center bg-info">
                        <th>STT</th>
                        <th>Họ và Tên</th>
                        <th>Ngày sinh</th>
                        <th>Giới tính</th>
                        <th>Địa chỉ</th>
                        <th>Cập nhật</th>
                        <th>Xóa</th>
                    </tr>
                    <%--String name, String iDCitizen, Date birthday, boolean sex, String phone,
                                        String email, int iDCustomer, CustomerType customerType, String address--%>
                    <c:forEach varStatus="status" var="customer" items="${customers}">
                        <tr>
                            <td class="text-center">${status.count}</td>
                            <td>
                                <a data-bs-toggle="modal" data-bs-target="#infoCustomer"
                                   onclick="infoCustomer('${customer.getInfo()}')" href="#">
                                        ${customer.name}
                                </a>
                            </td>
                            <td class="text-center">${customer.birthday}</td>
                            <c:if test="${customer.sex}">
                                <td class="text-center">Nam</td>
                            </c:if>
                            <c:if test="${!customer.sex}">
                                <td class="text-center">Nữ</td>
                            </c:if>
                            <td>${customer.address}</td>
                            <td class="text-center">
                                <a href="/customer?action=update&id=${customer.iDCustomer}">
                                    <span class="fa-solid fa-user-pen text-primary h4 m-auto"></span>
                                </a>
                            </td>
                            <td class="text-center">
                                <a href="/customer?action=delete&id=${customer.iDCustomer}" data-bs-toggle="modal"
                                   data-bs-target="#deleteModal"
                                   onclick="deleteCustomer('${customer.iDCustomer}','${customer.name}')">
                                    <span class="fa-solid fa-person-circle-minus text-danger h4 m-auto"></span>
                                </a>
                                    <%--<a href="/customer?action=delete&id=${customer.iDCustomer}">
                                        <span class="fa-solid fa-person-circle-minus text-danger h4 m-auto"></span>
                                    </a>--%>
                            </td>
                        </tr>
                    </c:forEach>
                </table>


            </div>

        </div>

    </div>
    <div class="row">
        <%@include file="/view/footer.jsp" %>
    </div>
    <div class="modal" style="margin-top: 10%" id="infoCustomer" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="infoCustomerLabel" style="color: #4267b2">Thông Tin Nhân Viên : <span
                            style="color: rebeccapurple" id="nameEmployee"></span></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table border="1" class="table table-striped">
                        <tr>
                            <td>Giới tính:</td>
                            <td><span id="genderEmployee"></span></td>
                        </tr>
                        <tr>
                            <td>Ngày sinh:</td>
                            <td><span id="dateOfBirthEmployee"></span></td>
                        </tr>
                        <tr>
                            <td>Số CMND:</td>
                            <td><span id="idCardEmployee"></span></td>
                        </tr>
                        <tr>
                            <td>Số điện thoại:</td>
                            <td><span id="phoneNumberEmployee"></span></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><span id="emailEmployee"></span></td>
                        </tr>
                        <tr>
                            <td>Địa chỉ:</td>
                            <td><span id="addressEmployee"></span></td>
                        </tr>
                        <tr>
                            <td>Trình độ:</td>
                            <td><span id="degreeEmployee"></span></td>
                        </tr>
                        <tr>
                            <td>Vị trí:</td>
                            <td><span id="positionEmployee"></span></td>
                        </tr>
                        <tr>
                            <td>Bộ phận:</td>
                            <td><span id="divisionEmployee"></span></td>
                        </tr>
                        <tr>
                            <td>Lương:</td>
                            <td><span id="salaryEmployee"></span></td>
                        </tr>
                        <tr>
                            <td>Tài khoản:</td>
                            <td><span id="userNameEmployee"></span></td>
                        </tr>
                    </table>

                </div>

            </div>

        </div>
    </div>

</div>


<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <form action="/employee" method="get">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">You Want To Delete?</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="text" hidden name="idDelete" id="idDelete">
                    <input type="text" hidden name="action" value="delete">
                    <strong>Customer: </strong>
                    <span id="nameDelete"></span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                    </button>
                    <button type="submit" class="btn btn-primary">Delete</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script>
    function deleteCustomer(id, name) {
        document.getElementById("idDelete").value = id;
        document.getElementById("nameDelete").innerText = name;
    }

    function infoEmployee(employee) {

        let info = employee.split("@@");
        document.getElementById("nameEmployee").innerText = info[0];
        let gender = info[3];
        let gt;
        if (gender == "true") {
            gt = "Nam";
        } else {
            gt = "Nữ";
        }
        document.getElementById("genderEmployee").innerHTML = gt;
        /* getName(), getIDCitizen(),dateString,
                isSex(), getPhone(), getEmail(), getAddress(), iDEmployee,
                 degree.getName(),position.getName(),division.getName(), salary, userName;*/
        document.getElementById("dateOfBirthEmployee").innerHTML = info[2];
        document.getElementById("idCardEmployee").innerHTML = info[1];
        document.getElementById("phoneNumberEmployee").innerHTML = info[4];
        document.getElementById("emailEmployee").innerHTML = info[5];
        document.getElementById("addressEmployee").innerHTML = info[6];
        document.getElementById("degreeEmployee").innerHTML = info[8];
        document.getElementById("positionEmployee").innerHTML = info[9];
        document.getElementById("divisionEmployee").innerHTML = info[10];
        document.getElementById("salaryEmployee").innerHTML = info[11];
        document.getElementById("userNameEmployee").innerHTML = info[12];

    }
</script>

</html>
