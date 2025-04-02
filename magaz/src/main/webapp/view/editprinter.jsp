<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<%@ page import="domain.Product"%>
<%@ page import="domain.Printer"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="css/style.css"> 

<!-- Bootstrap CSS --> 
<link rel="stylesheet" href="css/bootstrap.min.css"> 
<!-- jQuery --> 
<script defer src="js/jquery.min.js"></script> 
<!-- Bootstrap JS + Popper JS --> 
<script defer src="js/bootstrap.min.js"></script> 
<script 
src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.mi
 n.js"></script> 
 
 
<title>Принтеры</title>
</head>
<body>

<!-- Header --> 
<jsp:include page="/view/header.jsp" />
<!-- /Header -->

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>

    <%-- ADD THIS BLOCK --%>
    <%
    java.util.List pr = (java.util.List) request.getAttribute("pr");
    if (pr == null) {
        out.println("<p>Список Printer не установлен!</p>");
    } else if (pr.isEmpty()) {
        out.println("<p>Список Printer пуст!</p>");
    } else {
        out.println("<p>Список Printer содержит " + pr.size() + " элементов.</p>");
    }
    %>
    <%-- END OF ADDED BLOCK --%>

  <div class="container-fluid">
            <div class="row justify-content-start">
                <div class="col-8 border bg-light px-5">
                    <h3>Список Принтеров</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Model</th>
                                <th scope="col">Color</th>
                                <th scope="col">Type</th>
                                <th scope="col">Price</th>
                                <th scope="col">Count</th>
                                <th scope="col">Maker</th>
                                <th scope="col">Type</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="printer" items="${pr}">
                            <c:set var="product" value="${productMap[printer.model]}" />
                                <tr>
                                    <td>${printer.getId()}</td>
                                    <td>${printer.getModel()}</td>
                                    <td>${printer.getColor()}</td>
                                    <td>${printer.getType()}</td>
                                    <td>${printer.getPrice()}</td>
                                    <td>${printer.getCount()}</td>
                                    <td>${product.getmaker()}</td>
                                    <td>${product.gettype()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-4 border px-4">
                
                    <form method="POST" action="editprinter">
                        <h3>Редактирование принтера</h3>
                        <input type="hidden" name="id" value="${printer.id}">
                        <div class="mb-3">
                <label for="inputModel" class="col-sm-3 col-form-label">Model</label>
                <div class="col-sm-6">
                    <input type="text" name="inputModel" class="form-control" id="productModel" readonly value="${printer.model}" />
                </div>
            </div>
                        <div class="mb-3">
                <label for="inputColor" class="col-sm-3 col-form-label">Color</label>
                <div class="col-sm-6">
                    <select name="inputColor" class="form-control" id="printerColor">
                        <option value="true" ${printer.color ? 'selected' : ''}>True</option>
                        <option value="false" ${!printer.color ? 'selected' : ''}>False</option>
                    </select>
                </div>
            </div>
                        <div class="mb-3">
                <label for="inputType" class="col-sm-3 col-form-label">Type</label>
                <div class="col-sm-6">
                    <select name="inputType" class="form-control" id="printerType">
                        <option value="Inkjet" ${printer.type == 'Inkjet' ? 'selected' : ''}>Inkjet</option>
                        <option value="Laser" ${printer.type == 'Laser' ? 'selected' : ''}>Laser</option>
                        <option value="MFD" ${printer.type == 'MFD' ? 'selected' : ''}>MFD</option>
                    </select>
                </div>
            </div>
                        <<div class="mb-3">
                <label for="inputPrice" class="col-sm-3 col-form-label">Price</label>
                <div class="col-sm-6">
                    <input type="number" step="0.01" name="inputPrice" class="form-control" id="printerPrice" value="${printer.price}"/>
                </div>
            </div>
                        <div class="mb-3">
                <label for="inputCount" class="col-sm-3 col-form-label">Count</label>
                <div class="col-sm-6">
                    <input type="number" name="inputCount" class="form-control" id="printerCount" value="${printer.count}"/>
                </div>
            </div>
            <p>
                <button type="submit" class="btn btn-primary">Сохранить изменения</button> 
            </p>
                    </form>
                </div>
            </div>
    </div>

<!-- Footer --> 
<jsp:include page="/view/footer.jsp" />
<!-- /Footer -->  

</body>
</html>