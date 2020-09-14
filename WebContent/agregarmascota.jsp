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
    <h2>Agregar Mascota</h2>
 <form action="mascotas" method="post">
 <input type="hidden" name="opcion" value="guardar">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Rut Dueño:</label>
      <input type="text" name="rutD" class="form-control" id="inputEmail4" placeholder="Rut Dueño">
    </div>
    <div class="form-group col-md-6">
      <label for="inputState">Tipo Mascota</label>
      <select id="inputState" name="tipo" class="form-control">
        <option value="" disabled selected>Seleccionar</option>
        <option  value="Perro">Perro</option>
        <option  value="Gato">Gato</option>
      </select>
    </div>
  </div>
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="inputAddress">Edad:</label>
    <input type="text" name="edad" class="form-control" id="inputAddress" placeholder="Edad">
  </div>
  <div class="form-group col-md-6">
    <label for="inputAddress2">Nombre Mascota:</label>
    <input type="text" name="nombre" class="form-control" id="inputAddress2" placeholder="Nombre Mascota">
  </div>
  </div>
  
  <button type="submit" value="Guardar" class="btn btn-primary">Guardar</button>
</form>
    
	</div>
</body>
</html>