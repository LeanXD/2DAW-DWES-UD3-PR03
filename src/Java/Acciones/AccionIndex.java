/**
 * Acción: procesar login usuario
 */
package Java.Acciones;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import Java.Controlador.Accion;
import Java.Modelo.*;

/**
 * Procesa los datos de login y clave proporcionados por un usuario.
 * @author Eduardo A. Ponce
 */

public class AccionIndex implements Accion {

	private String vista;
	private String vistaOK = "index.html";
	private final String vistaError = "index.jsp";
	private final String vistaForm= "login.jsp";
	
	// Estas variables las necesitan todas las acciones 
	private ServletContext sc;
	private HttpSession sesion;
	private DataSource DS;
	private BeansUsuario modelo;
	
	
	public AccionIndex()
	{
		
	}
	
	public boolean ejecutar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		//Contralamos si el usuario se encuentra su sesión abierta o no
		String sesionActiva =(String) sesion.getAttribute("sesionCerrada");
		if(sesionActiva!=null){
			if(sesionActiva.equals("False")){
				vistaOK="WEB-INF/menu.jsp";
			}
		}
		vista = vistaOK;
		setVista(vista);
		return false;
	}

	public String getVista() 
	{
		return vista;
	}
	
	public void setVista(String vista)
	{
		this.vista = vista;
	}
	
	public Object getModelo() 
	{
		return modelo;
	}
	
	public void setModelo(BeansUsuario modelo)
	{
	    this.modelo = modelo;
	}
	
	public void setSc(ServletContext sc) 
	{
		this.sc = sc;
	}
	
	public ServletContext getSc()
	{
	    return sc;
	}
	
	
	public void setSesion(HttpSession sesion) {
		this.sesion = sesion;
	}
	
	public HttpSession getSesion() {
		return sesion;
	}
	
	public void setDS(DataSource ds)
	{
		DS = ds;
	}

}
