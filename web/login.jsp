<%@page import="Java.Modelo.BeansCaptcha"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logeate</title>
<% BeansCaptcha captcha =  (BeansCaptcha) session.getAttribute("captcha"); 
String error = (String) session.getAttribute("ErrorLogin");
String imagen = captcha.getNombre();%>
</head>
<body>
<center>
<br/>
<h4>Identificate</h4>
	<form action="controlador" method="post">
		<label>Login: </label>
		<input type="text" name="usuario" requiered>
		<br/>
		<label>Clave: </label>
		<input type="password" name="clave" requiered>
		<br/>
		<br/>
		<label>Introduce el siguiente c&oacute;digo que ves en la imagen</label>
		<br/>
		<br/>
		<%
			if(imagen!=null){
				System.out.println("WEB-INF/"+imagen);
				%>
					<img src="images/<%=imagen%>" width="200px" height="200px" >				
					<br/>
					<input type="text" name="captcha" requiered>
					<br/>
					<input type="hidden" name="idCaptcha" value="<%=Integer.toString(captcha.getId())%>"/>
				<%
			}else{
			%>
				<p style="color: red;">Error al cargar la imagen</p>
			<%
			}
		 %>
		 <input type="submit" name="accion" value="Acceder">
	</form>
	<%
		if(error!=null){
			%>
				<p style="color: red;"><%=error %></p>
			<%
		}
	 %>
</center>
</body>
</html>