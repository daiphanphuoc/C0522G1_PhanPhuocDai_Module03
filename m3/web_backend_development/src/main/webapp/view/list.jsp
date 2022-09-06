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
    <link rel="stylesheet" href="../datatables/css/dataTables.bootstrap4.css"/>
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


            <table id="elementTable" class="table table-light table-striped table-bordered">
                <thead>
                <tr class="table-dark text-light">
                    <th class="text-center">STT</th>
                    <th>Name</th>

                    <th class="text-center">Update</th>
                    <th class="text-center">Delete</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach varStatus="status" var="element" items="${customerList}">
                    <tr>
                        <td class="text-center">${status.count}</td>
                        <td>${element.getName}</td>
                        <td class="text-center">
                            <a href="/servlet?action=update&id=${element.getId()}">
                                <button class="btn btn-primary btn-outline-secondary btn-sm">
                                    Update
                                </button>
                            </a>
                        </td>
                        <td class="text-center">
                            <a href="/servlet?action=delete&id=${element.getId()}" data-bs-toggle="modal"
                               data-bs-target="#deleteModal"
                               onclick="deleteCustomer('${element.getId()}','${element.getName()}')">
                                <button class="btn btn-danger btn-outline-secondary btn-sm">Delete
                                </button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>

    </div>
    <div class="row">
        <%@include file="/footer.jsp" %>
    </div>

</div>
<%--Modal hiển thị thông báo sau khi insert, update, delete--%>
<input hidden type="text" id="success" value="${msg}">
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

<%--Modal confirm delete đối tượng--%>
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <form action="/servlet" method="get">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">You Want To Delete?</h5>
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

    function deleteCustomer(id, name) {
        document.getElementById("idDelete").value = id;
        document.getElementById("nameDelete").innerText = name;
    }

</script>
<script src="../jquery/jquery-3.5.1.min.js"></script>
<script src="../datatables/js/jquery.dataTables.min.js"></script>
<script src="../datatables/js/dataTables.bootstrap4.min.js"></script>
<script>
    $(document).ready(function () {
        $('#elementTable').dataTable({
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 7
        });
    });
</script>
</html>
