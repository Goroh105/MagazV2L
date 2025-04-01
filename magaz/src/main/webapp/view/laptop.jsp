<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<%@ page import="domain.Product"%>
<%@ page import="domain.Laptop"%>

<%  
Product r1 = new Product(1L, "Xiaomy", "PC"); 
Product r2 = new Product(2L, "Hunday", "Laptop"); 
Product r3= new Product(12L, "Xiaomy", "Printer");
Product[] product = new Product[]{r1, r2, r3}; 

//Создаем несколько объектов Laptop
Laptop laptop1 = new Laptop(1L, 1234L, 3.2F, 8, 500, 15.6F, 500.0, 10);
Laptop laptop2 = new Laptop(2L, 5678L, 2.8F, 4, 250, 14.0F, 800.0, 5);
Laptop laptop3 = new Laptop(3L, 9012L, 3.5F, 16, 1000, 17.3F, 1200.0, 3);
Laptop laptop4 = new Laptop(4L, 3456L, 2.5F, 2, 120, 13.3F, 300.0, 15);

// Создаем список объектов Laptop
List<Laptop> laptopList = new ArrayList<>();
laptopList.add(laptop1);
laptopList.add(laptop2);
laptopList.add(laptop3);
laptopList.add(laptop4);

// Устанавливаем список в атрибут requestScope, чтобы к нему можно было получить доступ из JSTL
pageContext.setAttribute("laptopList", laptopList);
%> 
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
<nav class="navbar navbar-light bg-primary"> 
<div class="container-fluid"> 
<a class="navbar-brand" href="#"> 
</a> 
<h2>Список ноутбуков</h2> 
</div> 
</nav> 
<!-- /Header -->

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
                                <th scope="col">Редактировать</th>
                                <th scope="col">Удалить</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="laptop" items="${laptopList}">
                                <tr>
                                    <td>${laptop.getId()}</td>
                                    <td>${laptop.getModel()}</td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getSpeed() %></td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getRam() %></td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getHd() %></td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getScreen() %></td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getPrice() %></td>
                                    <td><%= ((domain.Laptop) pageContext.findAttribute("laptop")).getCount() %></td>
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