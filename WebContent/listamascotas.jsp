<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="menu.jsp"%>
        <div style="width:80%; margin:auto">
        <h2>Lista de Mascotas</h2>
	<table class="table table-striped">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Nombre</th>
      <th scope="col">Tipo Mascota</th>
      <th scope="col">Edad</th>
      <th scope="col">Rut Dueño</th>
      <th scope="col">Acción</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="mascota" items="${lista}">
    <tr>
      <th scope="row"><a href="mascotas?opcion=meditar&idMascota=<c:out value="${mascota.idMascota}"></c:out>"><c:out value="${mascota.idMascota}"></c:out></a></th>
      <td><c:out value="${mascota.nombreMascota}"></c:out></td>
      <td><c:out value="${mascota.tipoMascota}"></c:out></td>
      <td><c:out value="${mascota.edad}"></c:out></td>
      <td><c:out value="${mascota.rutDueno}"></c:out></td>
      <td><a href="mascotas?opcion=eliminar&id=<c:out value="${ mascota.idMascota}"></c:out>">Eliminar</a></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
	
</div>	
</body>
</html>