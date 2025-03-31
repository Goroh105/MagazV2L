<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="jakarta.tags.core"%> 
<%@ page import="domain.Product"%>

<%  
Product r1 = new Product(1l, "Xiaomy", "PC"); 
Product r2 = new Product(2l, "Hunday", "Laptop"); 
Product r3= new Product(3l, "Xiaomy", "Printer"); 
Product r4 = new Product(4l, "Samsung", "PC"); 
Product[] product = new Product[]{r1, r2, r3, r4}; 
int length = product.length; 
pageContext.setAttribute("product", product); 
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Продукты магазина компьютерной 
техники</title>

<link rel="stylesheet" type="text/css" href="css/style.css"> 

<!-- Bootstrap CSS --> 
<link rel="stylesheet" href="css/bootstrap.min.css"> 
<!-- jQuery --> 
<script defer src="js/jquery-3.6.4.js"></script> 
<!-- Bootstrap JS + Popper JS --> 
<script defer src="js/bootstrap.min.js"></script>
</head>
<body>


 <div class="container-fluid"> 
 
<!-- Header --> 
<nav class="navbar navbar-light bg-primary"> 
<div class="container-fluid"> 
<a class="navbar-brand" href="#"> 
</a> 
<h2>Управление магазином</h2> 
</div> 
</nav> 
<!-- /Header -->

  <div class="container-fluid"> 
      <div class="row justify-content-start "> 
        <div class="col-8 border bg-light px-4"> 
          <h3>Список продуктов</h3> 
          <table class="table"> 
            <thead> 
              <th scope="col">Код</th> 
              <th scope="col">Должность</th> 
              <th scope="col"> Редактировать</th> 
              <th scope="col">Удалить</th> 
            </thead> 
            <tbody> 
              <c:forEach var="product" items="${product}"> 
                <tr> 
                  <td>${product.getmodel()}</td> 
                  <td>${product.getmaker()}</td> 
                  <td>${product.gettype()}</td> 
                  <td width="20"><a href="#" role="button" 
                     class="btn btn-outline-primary">  
                     Редактировать</a></td> 
                   <td width="20"><a href="#" role="button" 
                     class="btn btn-outline-primary">  
                     Удалить</a></td> 
                  </tr> 
               </c:forEach> 
            </tbody> 
          </table> 
        </div> 
        <div class="col-4 border px-4"> 
          <form method="POST" action=""> 
            <h3>Новая должность</h3> 
            <div class="mb-3"> 
              <br> <label for="inputproduct"  
              class="col-sm-3 col-form-label">Должность</label> 
              <div class="col-sm-6"> 
                <input type="text" name="inputproduct"  
                  class="form-control" id="personproduct" /> 
            </div> 
          </div> 
          <p> 
            <br> <br> <br> 
<button type="submit"  
class="btn btn-primary">Добавить</button> 
<br> 
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
</div>

</body>
</html>