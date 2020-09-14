<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clinica Pet Smile - Agendar</title>
</head>
<body>
<%@include file="menu.jsp"%>
        
       <div style="width:80%; margin:auto">
    <h2>Agendar</h2>
 <form>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputPassword4">Rut Dueño:</label>
      <input type="text" class="form-control" id="inputPassword4" placeholder="Rut">
    </div>
     <div class="form-group col-md-6">
    <label for="inputAddress">Nombre Mascota:</label>
    <input type="text" class="form-control" id="inputAddress" placeholder="Nombre Mascota">
  </div>
  </div>
  <div class="form-row">
 
  <div class="form-group col-md-6">
    <label for="inputAddress2">Hora:</label>
    <input type="time" class="form-control" id="inputAddress2">
  </div>
  <div class="form-group col-md-6">
      <label for="inputCity">Fecha:</label>
      <input type="date" class="form-control" id="inputCity">
    </div>
  </div>
 
  
  <button type="submit" class="btn btn-primary">Guardar</button>
</form>
    
	</div>
</body>
</html>