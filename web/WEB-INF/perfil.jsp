<%@page import="Java.Modelo.BeansUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%BeansUsuario modelo = (BeansUsuario) session.getAttribute("modelo"); %>
<title>Bienvenido <%=modelo.getNombre() %></title>
</head>
<body>
	<center>
		<h3>Datos Usuarios</h3>
		<br/>
		<label><b>Login  </b></label>
		<label><%=modelo.getUsuario()%></label>
		<br>
		<label><b>Clave  </b></label>
		<label><%=modelo.getClave()%></label>
		<br/>
		<label><b>Nombre  </b></label>
		<label><%=modelo.getNombre()%></label>
		<br/>
		<br/>
		<form action="controlador" method="post">
			<input type="submit" name="accion" value="Volver"/>
		</form>
	</center>
</body>
</html>