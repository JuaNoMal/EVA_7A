<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clinica Pet Smile - Agregar Dueño</title>
</head>
<body>
<%@include file="menu.jsp"%>
        <div style="width:80%; margin:auto">
    <h2>Agregar Dueño</h2>
 <form>
  <div class="form-row">
    <div class="form-group col-md-2">
      <label for="inputEmail4">Rut:</label>
      <input type="text" class="form-control" id="inputEmail4" placeholder="Rut Dueño">
    </div>
    <div class="form-group col-md-5">
      <label for="inputPassword4">Nombre:</label>
      <input type="text" class="form-control" id="inputPassword4" placeholder="Nombre">
    </div>
    <div class="form-group col-md-5">
      <label for="inputPassword4">Apellido:</label>
      <input type="text" class="form-control" id="inputPassword4" placeholder="Apellido">
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputCity">Dirección</label>
      <input type="text" class="form-control" id="inputCity" placeholder="Direccion">
    </div>
    <div class="form-group col-md-4">
      <label for="inputState">Correo</label>
      <input type="text" class="form-control" id="inputZip" placeholder="Email">
    </div>
    <div class="form-group col-md-2">
      <label for="inputZip">Teléfono</label>
      <input type="text" class="form-control" id="inputZip" placeholder="Teléfono">
    </div>
  </div>
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="inputAddress2">Nombre Mascota:</label>
    <input type="text" class="form-control" id="inputAddress2" placeholder="Nombre Mascota">
  </div>
  </div>
  <button type="submit" class="btn btn-primary">Guardar</button>
</form>
    
	</div>
</body>
</html>