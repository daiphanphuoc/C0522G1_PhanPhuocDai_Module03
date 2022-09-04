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
    <link rel="stylesheet" href="/datatables/css/dataTables.bootstrap4.min.css"/>
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
            <h2 class="text-center fw-bold">EMPLOYEE LIST</h2>
            <div class="row">
                <%--
                <a href="/customer?action=create">
                    <button class="btn btn-success btn-sm my-2">
                        <span class="fa-solid fa-person-circle-plus text-light h5 my-auto me-1"></span> Add new Customer
                    </button>
                </a>
                --%>
                <fieldset>
                    <form action="/employee" method="get">
                        <input hidden type="text" name="action" value="search">
                        <input type="text" name="searchName" value="${searchName}" placeholder="Nhập tên nhân viên">
                        <input type="text" name="searchAddress" value="${searchAddress}" placeholder="Nhập địa chỉ">
                        <input type="text" name="searchPhone" value="${searchPhone}"
                               placeholder="Nhập số điện thoại">
                        <button class="btn btn-success btn-sm " type="submit">Search</button>
                    </form>
                </fieldset>

            </div>
            <div>


                <table id="employeeTable" class="table table-striped table-bordered border border-3 border-secondary">
                    <thead>
                    <tr class="text-center bg-info">
                        <th>STT</th>
                        <th>Họ và Tên</th>
                        <th>Ngày sinh</th>
                        <th>Giới tính</th>
                        <th>Địa chỉ</th>
                        <th>Cập nhật</th>
                        <th>Xóa</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach varStatus="status" var="employee" items="${employees}">
                        <tr>
                            <td class="text-center">${status.count}</td>
                            <td>
                                <a data-bs-toggle="modal" data-bs-target="#infoEmployee"
                                   onclick="infoEmployee('${employee.getInfo()}')" href="#">
                                        ${employee.name}
                                </a>
                            </td>
                            <td class="text-center">${employee.birthday}</td>
                            <c:if test="${employee.sex}">
                                <td class="text-center">Nam</td>
                            </c:if>
                            <c:if test="${!employee.sex}">
                                <td class="text-center">Nữ</td>
                            </c:if>
                            <td>${employee.address}</td>
                            <td class="text-center">
                                <a href="/employee?action=update&id=${employee.iDEmployee}">
                                    <span class="fa-solid fa-user-pen text-primary h4 m-auto"></span>
                                </a>
                            </td>
                            <td class="text-center">
                                <a href="/employee?action=delete&id=${employee.iDEmployee}" data-bs-toggle="modal"
                                   data-bs-target="#deleteModal"
                                   onclick="deleteEmployee('${employee.iDEmployee}','${employee.name}')">
                                    <span class="fa-solid fa-person-circle-minus text-danger h4 m-auto"></span>
                                </a>
                                    <%--<a href="/customer?action=delete&id=${employee.iDEmployee}">
                                        <span class="fa-solid fa-person-circle-minus text-danger h4 m-auto"></span>
                                    </a>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div>

        </div>

    </div>
    <div class="row">
        <%@include file="/view/footer.jsp" %>
    </div>

    <div class="modal" style="margin-top: 1%" id="infoEmployee" tabindex="-1" aria-labelledby="infoEmployeeLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="infoEmployeeLabel" style="color: #4267b2">Thông Tin Nhân Viên : <span
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

<input hidden type="text" id="success" value="${msg}">
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

<button type="button" id="modalSuccess" hidden class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#messModal">
    Launch demo modal
</button>

<div class="modal fade" id="messModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-primary" id="messModalLabel">${msg}</h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script>
    function showSuccessModal() {
        let any = document.getElementById("success").value;
        //alert(any);
        if (any != null && any !== "") {
            document.getElementById("modalSuccess").click();
        }
    }

    showSuccessModal();

    function deleteEmployee(id, name) {
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
<script src="/jquery/jquery-3.5.1.min.js"></script>
<script src="/datatables/js/jquery.dataTables.min.js"></script>
<script src="/datatables/js/dataTables.bootstrap4.min.js"></script>
<script>


    $(document).ready(function () {
        $('#employeeTable').dataTable({
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 5
        });
    });
</script>
</html>
