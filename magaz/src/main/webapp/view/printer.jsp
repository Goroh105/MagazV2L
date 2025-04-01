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
<nav class="navbar navbar-light bg-primary"> 
<div class="container-fluid"> 
<a class="navbar-brand" href="#"> 
</a> 
<h2>Управление магазином</h2> 
</div> 
</nav> 
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
                                <th scope="col">Редактировать</th>
                                <th scope="col">Удалить</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="printer" items="${pr}">
                                <tr>
                                    <td>${printer.getId()}</td>
                                    <td>${printer.getModel()}</td>
                                    <td>${printer.getColor()}</td>
                                    <td>${printer.getType()}</td>
                                    <td>${printer.getPrice()}</td>
                                    <td>${printer.getCount()}</td>
                                    <td width="20"><a href="#" role="button" class="btn btn-outline-primary">
                                        Редактировать</a></td>
                                    <td width="20"><a href="#" role="button" class="btn btn-outline-primary">
                                        Удалить</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-4 border px-4">
                    <form method="POST" action="">
                        <h3>Новый принтер</h3>
                        <div class="mb-3">
                            <label for="inputModel" class="col-sm-3 col-form-label">Model</label>
                            <div class="col-sm-6">
                                <input type="text" name="inputModel" class="form-control" id="printerModel"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputColor" class="col-sm-3 col-form-label">Color</label>
                            <div class="col-sm-6">
                                <input type="checkbox" name="inputColor" id="printerColor"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputType" class="col-sm-3 col-form-label">Type</label>
                            <div class="col-sm-6">
                                <input type="text" name="inputType" class="form-control" id="printerType"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputPrice" class="col-sm-3 col-form-label">Price</label>
                            <div class="col-sm-6">
                                <input type="number" step="0.01" name="inputPrice" class="form-control" id="printerPrice"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputCount" class="col-sm-3 col-form-label">Count</label>
                            <div class="col-sm-6">
                                <input type="number" name="inputCount" class="form-control" id="printerCount"/>
                            </div>
                        </div>
                        <p>
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </p>
                    </form>
                </div>
            </div>
    </div>

<!-- Footer --> 
<footer style="background: #d7d7d7 ; " class="page-footer font
small black"> 
<!-- Copyright --> 
<div class="footer-copyright text-center py-3 px-3" > 
Демонстрационное веб-приложение JavaEE © 2025 Copyright: 
<a href="https://dolsoft.com/"> Dolsoft.com</a> 
</div>  
<!-- Copyright --> 
</footer> 
<!-- /Footer --> 

</body>
</html>