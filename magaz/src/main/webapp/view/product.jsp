<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%> 
<%@ page import="domain.Product"%>

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
<jsp:include page="/view/header.jsp" />
<!-- /Header -->

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>

    <%-- ADD THIS BLOCK --%>
    <%
    java.util.List pro = (java.util.List) request.getAttribute("pro");
    if (pro == null) {
        out.println("<p>Список Product не установлен!</p>");
    } else if (pro.isEmpty()) {
        out.println("<p>Список Product пуст!</p>");
    } else {
        out.println("<p>Список Product содержит " + pro.size() + " элементов.</p>");
    }
    %>
    <%-- END OF ADDED BLOCK --%>

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
              <c:forEach var="product" items="${pro}"> 
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
            <h3>Новый продукт</h3> 
            <div class="mb-3"> 
              <br> <label for="inputproduct"  
              class="col-sm-3 col-form-label">Модель</label> 
              <div class="col-sm-6"> 
                <input type="text" name="inputproduct"  
                  class="form-control" id="productmodel" /> 
            </div> 
          </div> 
          <div class="mb-3"> 
              <br> <label for="inputproduct"  
              class="col-sm-3 col-form-label">Производитель</label> 
              <div class="col-sm-6"> 
                <input type="text" name="inputproduct"  
                  class="form-control" id="productmaker" /> 
            </div> 
          </div> 
          <div class="mb-3"> 
              <br> <label for="inputproduct"  
              class="col-sm-3 col-form-label">Тип товара</label> 
              <div class="col-sm-6"> 
                <input type="text" name="inputproduct"  
                  class="form-control" id="producttype" /> 
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
<jsp:include page="/view/footer.jsp" />
<!-- /Footer -->  
</div>

</body>
</html>