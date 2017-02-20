<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Producido</title>
</head>
<%String ErrorSQL = (String) request.getAttribute("ErrorSQL"); 
String error = (String) request.getAttribute("error"); 
%>
<body>
	<center>
		<br/>
		<%if(ErrorSQL!=null){
			%>
			<h2>Error en la base de datos:</h5>
			<p style="color: red;"><%=ErrorSQL %></p>
			<br>
			<%
		} 
		if(error!=null){
			%>
			<h2>404 NOT FOUNT</h5>
			<p style="color: red;"><%=error %></p>
			<br>
			<%
		}
		%>
		<br/>
		<h5>Para volver al inicio pulsar el bot&oacute;n</h5>
		<br/>
		<form action="controlador" method="post">
			<input type="submit" name="accion" value="Inicio"/>
		</form>
	</center>
</body>
</html>