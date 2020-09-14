<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clinica Pet Smile - Lista de usuarios</title>
</head>
<body>
<%@include file="menu.jsp"%>
<div style="width:80%; margin:auto">
        <h2>Lista de usuarios</h2>
	<table class="table table-striped">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Nombre Usuario</th>
      <th scope="col">Password</th>
      <th scope="col">Acción</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="usuario" items="${lista}">
    <tr>
      <th scope="row"><a href="usuarios?opcion=meditar&id=<c:out value="${usuario.id}"></c:out>"><c:out value="${usuario.id}"></c:out>  </a></th>
      <td><c:out value="${usuario.nombreUsuario}"></c:out></td>
      <td><c:out value="${usuario.password}"></c:out></td>
      <td><a href="usuarios?opcion=eliminar&id=<c:out value="${usuario.id}"></c:out>">Eliminar</a></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
	
</div>	
</body>
</html>