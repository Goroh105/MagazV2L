<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<%@ page import="domain.Product"%>
<%@ page import="domain.Laptop"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="css/style.css"> 

<!-- Bootstrap CSS --> 
<link rel="stylesheet" href="css/bootstrap.min.css"> 
<!-- jQuery --> 
<script defer src="js/jquery-3.6.4.js"></script> 
<!-- Bootstrap JS + Popper JS --> 
<script defer src="js/bootstrap.min.js"></script>
 
<title>Ноутбуки</title>
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
    java.util.List laps = (java.util.List) request.getAttribute("laps");
    if (laps == null) {
        out.println("<p>Список Laptop не установлен!</p>");
    } else if (laps.isEmpty()) {
        out.println("<p>Список Laptop пуст!</p>");
    } else {
        out.println("<p>Список Laptop содержит " + laps.size() + " элементов.</p>");
    }
    %>
    <%-- END OF ADDED BLOCK --%>

<div class="container-fluid">
            <div class="row justify-content-start">
                <div class="col-8 border bg-light px-5">
                    <h3>Список ноутбуков</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Model</th>
                                <th scope="col">Speed</th>
                                <th scope="col">RAM</th>
                                <th scope="col">HD</th>
                                <th scope="col">Screen</th>
                                <th scope="col">Price</th>
                                <th scope="col">Count</th>
                                <th scope="col">Maker</th>
                                <th scope="col">Type</th>
                                <th scope="col">Редактировать</th>
                                <th scope="col">Удалить</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="laptop" items="${laps}">
                             <c:set var="product" value="${productMap[laptop.model]}" />
                                <tr>
                                    <td>${laptop.getId()}</td>
                                    <td>${laptop.getModel()}</td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getSpeed() %></td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getRam() %></td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getHd() %></td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getScreen() %></td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getPrice() %></td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getCount() %></td>
                                    <td>${product.getmaker()}</td>
                                    <td>${product.gettype()}</td>
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
                        <h3>Новый ноутбук</h3>
                        <div class="mb-3">
                        <label for="inputModel" class="col-sm-3 col-form-label">Model</label>
                            <div class="col-sm-6">
                                <input type="text" name="inputModel" class="form-control" id="laptopModel"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputSpeed" class="col-sm-3 col-form-label">Speed</label>
                            <div class="col-sm-6">
                                <input type="number" step="0.1" name="inputSpeed" class="form-control" id="laptopSpeed"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputRAM" class="col-sm-3 col-form-label">RAM</label>
                            <div class="col-sm-6">
                                <input type="number" name="inputRAM" class="form-control" id="laptopRAM"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputHD" class="col-sm-3 col-form-label">HD</label>
                            <div class="col-sm-6">
                                <input type="number" name="inputHD" class="form-control" id="laptopHD"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputScreen" class="col-sm-3 col-form-label">Screen</label>
                            <div class="col-sm-6">
                                <input type="number" step="0.1" name="inputScreen" class="form-control" id="laptopScreen"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputPrice" class="col-sm-3 col-form-label">Price</label>
                            <div class="col-sm-6">
                                <input type="number" step="0.01" name="inputPrice" class="form-control" id="laptopPrice"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputCount" class="col-sm-3 col-form-label">Count</label>
                            <div class="col-sm-6">
                                <input type="number" name="inputCount" class="form-control" id="laptopCount"/>
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
<jsp:include page="/view/footer.jsp" />
<!-- /Footer -->  

</body>
</html>