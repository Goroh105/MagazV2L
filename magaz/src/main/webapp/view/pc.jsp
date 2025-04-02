<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="domain.PC"%>
<%@ page import="domain.Product"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Компьютеры</title>


<link rel="stylesheet" type="text/css" href="css/style.css"> 

<!-- Bootstrap CSS --> 
<link rel="stylesheet" href="css/bootstrap.min.css"> 
<!-- jQuery --> 
<script defer src="js/jquery-3.6.4.js"></script> 
<!-- Bootstrap JS + Popper JS --> 
<script defer src="js/bootstrap.min.js"></script>
</head>
<body>

 
<!-- Header --> 
<jsp:include page="/view/header.jsp" />
<!-- /Header -->


    <%-- ADD THIS BLOCK --%>
    <%
    java.util.List pcs = (java.util.List) request.getAttribute("pcs");
    if (pcs == null) {
        out.println("<p>Список PC не установлен!</p>");
    } else if (pcs.isEmpty()) {
        out.println("<p>Список PC пуст!</p>");
    } else {
        out.println("<p>Список PC содержит " + pcs.size() + " элементов.</p>");
    }
    %>
    <%-- END OF ADDED BLOCK --%>

<div class="container-fluid">
            <div class="row justify-content-start">
                <div class="col-8 border bg-light px-4">
                    <h3>Список PC</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Model</th>
                                <th scope="col">Speed</th>
                                <th scope="col">RAM</th>
                                <th scope="col">HD</th>
                                <th scope="col">CD</th>
                                <th scope="col">Price</th>
                                <th scope="col">Count</th>
                                <th scope="col">Maker</th>
                                <th scope="col">Type</th>
                                <th scope="col">Редактировать</th>
                                <th scope="col">Удалить</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pc" items="${pcs}">
                             <c:set var="product" value="${productMap[pc.model]}" />
                                <tr>
                                    <td>${pc.getId()}</td>
                                    <td>${pc.getModel()}</td>
                                    <td>${pc.getSpeed()}</td>
                                    <td>${pc.getRam()}</td>
                                    <td>${pc.getHd()}</td>
                                    <td>${pc.getCd()}</td>
                                    <td>${pc.getPrice()}</td>
                                    <td>${pc.getCount()}</td>
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
                        <h3>Новый PC</h3>
                        <div class="mb-3">
                              <label for="inputModel" class="col-sm-3 col-form-label">Model</label>
                                  <div class="col-sm-6">
                                       <select name="inputModel" class="form-control" id="pcModel">
                                                   <c:forEach var="entry" items="${productMap}">
                                                          <option value="${entry.key}">${entry.key}</option>
                                                   </c:forEach>
                                        </select>
                                   </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputSpeed" class="col-sm-3 col-form-label">Speed</label>
                            <div class="col-sm-6">
                                <input type="number" step="0.1" name="inputSpeed" class="form-control" id="pcSpeed"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputRAM" class="col-sm-3 col-form-label">RAM</label>
                            <div class="col-sm-6">
                                <input type="number" name="inputRAM" class="form-control" id="pcRAM"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputHD" class="col-sm-3 col-form-label">HD</label>
                            <div class="col-sm-6">
                                <input type="number" name="inputHD" class="form-control" id="pcHD"/>
                            </div>
                        </div>
                        <div class="mb-3">
                          <label for="inputCD" class="col-sm-3 col-form-label">CD</label>
                            <div class="col-sm-6">
                                   <select name="inputCD" class="form-control" id="pcCD">
                                              <option value="DVD">DVD</option>
                                                <option value="BluRay">BluRay</option>
                                   </select>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputPrice" class="col-sm-3 col-form-label">Price</label>
                            <div class="col-sm-6">
                                <input type="number" step="0.01" name="inputPrice" class="form-control" id="pcPrice"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="inputCount" class="col-sm-3 col-form-label">Count</label>
                            <div class="col-sm-6">
                                <input type="number" name="inputCount" class="form-control" id="pcCount"/>
                            </div>
                        </div>
                        <br>
                            <c:if test="${not empty errorMessage}">
                               <p style="color:red;">${errorMessage}</p>
                            </c:if>
                        <br>
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