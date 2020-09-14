<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="menu.jsp"%>
	 <div style="width:80%; margin:auto">
    <h2>Agregar Usuario</h2>
 <form action="usuarios" method="post">
 <input type="hidden" name="opcion" value="guardar">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputPassword4">Nombre Usuario:</label>
      <input type="text" name="name" class="form-control" id="inputPassword4" placeholder="Nombre Usuario">
    </div>
     <div class="form-group col-md-6">
    <label for="inputAddress">Password:</label>
    <input type="password" name="password" class="form-control" id="inputAddress" placeholder="Password">
  </div>
  </div>
  
  
  <button type="submit" value="Guardar" class="btn btn-primary">Guardar</button>
</form>
    
	</div>
       
</body>
</html>