<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="domain.PC"%>
<%@ page import="domain.Product"%>

<%  
Product r1 = new Product(10l, "Xiaomy", "PC"); 
Product r2 = new Product(57l, "Hunday", "Laptop"); 
Product r3= new Product(94l, "Xiaomy", "Printer"); 
Product r4 = new Product(34l, "Samsung", "PC"); 
Product[] product = new Product[]{r1, r2, r3, r4}; 

//Создаем несколько объектов PC
PC pc1 = new PC(1L, 10L, 3.2F, 8, 500, "52x", 500.0, 10);
PC pc2 = new PC(2L, 57L, 2.8F, 4, 250, "48x", 800.0, 5);
PC pc3 = new PC(3L, 94L, 3.5F, 16, 1000, "DVD", 1200.0, 3);
PC pc4 = new PC(4L, 34L, 2.5F, 2, 120, "24x", 300.0, 15);

// Создаем список объектов PC
List<PC> pcList = new ArrayList<>();
pcList.add(pc1);
pcList.add(pc2);
pcList.add(pc3);
pcList.add(pc4);

// Устанавливаем список в атрибут requestScope, чтобы к нему можно было получить доступ из JSTL
request.setAttribute("pcList", pcList);

%> 

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
<nav class="navbar navbar-light bg-primary"> 
<div class="container-fluid"> 
<a class="navbar-brand" href="#"> 
</a> 
<h2>Список ПК</h2> 
</div> 
</nav> 
<!-- /Header -->


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
                                <th scope="col">Редактировать</th>
                                <th scope="col">Удалить</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pc" items="${pcList}">
                                <tr>
                                    <td>${pc.getId()}</td>
                                    <td>${pc.getModel()}</td>
                                    <td>${pc.getSpeed()}</td>
                                    <td>${pc.getRam()}</td>
                                    <td>${pc.getHd()}</td>
                                    <td>${pc.getCd()}</td>
                                    <td>${pc.getPrice()}</td>
                                    <td>${pc.getCount()}</td>
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
                                <input type="text" name="inputModel" class="form-control" id="pcModel"/>
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
                                <input type="text" name="inputCD" class="form-control" id="pcCD"/>
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