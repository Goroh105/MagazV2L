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
<jsp:include page="/view/header.jsp" />
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
<jsp:include page="/view/footer.jsp" />
<!-- /Footer -->  

</body> 
</html> 
