<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html> 
<head> 
 <!-- Bootstrap CSS --> 
<link rel="stylesheet" href="css/bootstrap.min.css"> 
<!-- jQuery --> 
<script defer src="js/jquery-3.6.4.js"></script> 
<!-- Bootstrap JS + Popper JS --> 
<script defer src="js/bootstrap.bundle.min.js"></script>
<!-- Настройка viewport --> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<!-- Bootstrap CSS --> 
<link rel="stylesheet" href="css/bootstrap.min.css"> 

<link rel="stylesheet" type="text/css" href="css/style.css"> 

<meta charset="UTF-8"> 
<title>Управление магазином</title> 
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

<div class="container-fluid"> 
<div class="container"> 
   <br><br><br> 
<div class="list-group text-center py-3 px-3"> 
    <h2>Продукты магазина</h2> 
     <ul class="list-group list-group-flush"> 
     <li class="list-group-item list-group-item
primary"><a href="/magaz/product">Все продукты</a> 
      <li class="list-group-item list-group-item
info"><a href="/magaz/printer">Принтеры</a> 
      <li class="list-group-item list-group-item
info"><a href="/magaz/laptop">Ноутбуки</a> 
<li class="list-group-item list-group-item
info"><a href="/magaz/pc">Компьютеры</a> 
     </ul> 
</div> 
</div> 
<br><br> 
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
