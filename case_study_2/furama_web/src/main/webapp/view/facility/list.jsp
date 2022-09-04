<%--
  Created by IntelliJ IDEA.
  User: DELL5577
  Date: 03/09/2022
  Time: 7:09 CH
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


            <h3 style="color: #6a1a21;text-align: center">Quản lý cơ sở</h3>

            <form action="/facility" method="get">
                <input hidden type="text" name="action" value="search">
                <input type="text" name="nameSearch" value="${nameSearch}" placeholder="nhập tên dịch vụ">
                <%--<input type="text" name="rentType" value="${rentType}" placeholder="kiểu thuê">--%>
                <select name="rentType">
                    <option value="" disabled selected>Chọn kiểu thuê</option>
                    <c:forEach var="rentType" items="${rentTypeList}">
                        <option value="${rentType.name}">${rentType.name}</option>
                    </c:forEach>
                </select>
                <select name="facilityType">
                    <option value="" disabled selected>Chọn loại cơ sở</option>
                    <c:forEach var="facilityType" items="${facilityTypeList}">
                        <option value="${facilityType.name}">${facilityType.name}</option>
                    </c:forEach>
                </select>
                <button type="submit">Search</button>
            </form>

            <input hidden type="text" id="success" value="${msg}">


            <div style="text-align: center">
                <table id="facilityTable" class="table table-striped">
                    <thead>
                    <tr style="background: aquamarine">
                        <th>STT</th>
                        <th>Tên cơ sở</th>
                        <th>Diện tích</th>
                        <th>Phí thuê</th>
                        <th>Số lượng người tối đa</th>
                        <th>Kiểu thuê</th>
                        <th>Kiểu cơ sở</th>
                        <th>Cập nhập</th>
                        <th>Xóa</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach varStatus="status" var="facility" items="${facilityList}">
                        <tr>
                            <td>${status.count}</td>
                            <td><a data-bs-toggle="modal" data-bs-target="#infoFacility"
                                   onclick="infoFacility('${facility.getInfo()}')" href="#">${facility.getNameFacility()}</a></td>
                            <td>${facility.getLeasedArea()}</td>
                            <td>${facility.getRentalCosts()}</td>
                            <td>${facility.getMaxPerson()}</td>
                            <td>${facility.getRentalType().getName()}</td>
                            <td>${facility.getFacilityType().getName()}</td>

                            <td>
                                <a href="/facility?action=update&id=${facility.getIDFacility()}">
                                    <button type="button" class="btn btn-primary">Update</button>
                                </a>
                            </td>
                            <td>
                                <button onclick="deleteFacility('${facility.getIDFacility()}','${facility.getNameFacility()}')" type="button"
                                        class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Xóa
                                </button>

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

</div>

<div class="modal" style="margin-top: 1%" id="infoFacility" tabindex="-1" aria-labelledby="infoFacilityLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="infoCustomerLabel" style="color: #4267b2">Thông Tin Cơ sở dịch vụ : <span
                        style="color: rebeccapurple" id="nameFacility"></span></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table id="facilityTableModal" border="1" class="table table-striped">

                    <tr>
                        <td>Diện tích:</td>
                        <td><span id="areaFacility"></span></td>
                    </tr>
                    <tr>
                        <td>Phí thuê:</td>
                        <td><span id="costFacility"></span></td>
                    </tr>
                    <tr>
                        <td>Số người tối đa:</td>
                        <td><span id="maxPeopleFacility"></span></td>
                    </tr>
                    <tr>
                        <td>Kiểu thuê:</td>
                        <td><span id="rentTypeFacility"></span></td>
                    </tr>

                    <tr>
                        <td>Kiểu dịch vụ:</td>
                        <td><span id="facilityType"></span></td>
                    </tr>
                    <tr>

                        <td>Tiêu chuẩn phòng:</td>
                        <td><span id="standardRoomFacility"></span></td>
                    </tr>
                    <tr>
                        <td>Mô tả khác:</td>
                        <td><span id="descriptionFacility"></span></td>
                    </tr>
                    <tr>
                        <td>Diện tích hồ bơi:</td>
                        <td><span id="poolAreaFacility"></span></td>
                    </tr>
                    <tr>
                        <td>Số tầng:</td>
                        <td><span id="numberOfFloorsFacility"></span></td>
                    </tr>
                    <tr>
                        <td>Dịch vụ miễn phí:</td>
                        <td><span id="freeFacility"></span></td>
                    </tr>
                </table>

            </div>

        </div>

    </div>
</div>


<button type="button" id="modalSuccess" hidden class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#messModal">
    Launch demo modal
</button>
<div class="modal fade" id="messModal" tabindex="-1" aria-labelledby="infoFacilityLabel" aria-hidden="true">
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

<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/facility">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">Xóa Cơ Sở</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input hidden type="text" name="idDelete" id="idDelete">
                    <input hidden type="text" name="action" value="delete">
                    <span>Bạn có chắc đang muốn xóa</span>
                    <span style="color: red" id="nameDelete"></span> ?
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Xóa</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
<script>
    function showSuccessModal() {
        let any = document.getElementById("success").value;
        //alert(any);
        if (any != null && any !== "") {
            document.getElementById("modalSuccess").click();
        }
    }

    showSuccessModal();

    function deleteFacility(id, name) {
        document.getElementById("idDelete").value = id;
        document.getElementById("nameDelete").innerText = name;
    }

    function infoFacility(facility) {

        let info = facility.split("@@");
        document.getElementById("facilityType").innerText = info[0];
        document.getElementById("nameFacility").innerHTML = info[2];
        /* getFacilityType().getName(),getIDFacility(),getNameFacility(),getLeasedArea(),
                getRentalCosts(),getMaxPerson(),getRentalType().getName(),
                getDescription(),getRoom(),getFloor(),getAreaPool(),"null"*/
        document.getElementById("areaFacility").innerHTML = info[3];
        document.getElementById("costFacility").innerHTML = info[4];
        document.getElementById("maxPeopleFacility").innerHTML = info[5];
        document.getElementById("rentTypeFacility").innerText = info[6];
        document.getElementById("descriptionFacility").innerHTML = info[7];
        document.getElementById("standardRoomFacility").innerHTML = info[8];
        document.getElementById("numberOfFloorsFacility").innerHTML = info[9];
        document.getElementById("poolAreaFacility").innerHTML = info[10];
        document.getElementById("freeFacility").innerHTML = info[11];
    }
</script>
<script src="/jquery/jquery-3.5.1.min.js"></script>
<script src="/datatables/js/jquery.dataTables.min.js"></script>
<script src="/datatables/js/dataTables.bootstrap4.min.js"></script>
<script>
    $(document).ready(function () {
        $('#facilityTable').dataTable({
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 5
        });
    });
</script>
</html>
