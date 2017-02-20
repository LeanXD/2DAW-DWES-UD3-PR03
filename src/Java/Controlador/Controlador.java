package Java.Controlador;

import java.io.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

public class Controlador extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private DataSource dataSources;
	private ServletContext sc; 
	
	public void init(ServletConfig config) throws ServletException {

	    try {
	    	InitialContext initCtx = new InitialContext();
	    	setDataSources((DataSource) initCtx.lookup("java:jboss/datasources/dspr03"));
	 
	    	sc = config.getServletContext();
	    	sc.setAttribute("dataSources", getDataSources());
	    } 
	    catch(NamingException ne)
	    {
	        System.out.println("Fallo en la conexión.");
	    } 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    doPost(request,response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
	    AyudaSolicitud ayudaSol = new AyudaSolicitud(request);
	    Accion accion = ayudaSol.getAccion();
	    accion.setSc(sc);
	    accion.setDS (dataSources);
	    accion.setSesion(sesion);
	    if (accion.ejecutar(request,response))
	    {
	      String vista = accion.getVista();
	      sesion.setAttribute("modelo",accion.getModelo());
	      RequestDispatcher rq = request.getRequestDispatcher(vista);
	      rq.forward(request,response);
	    }
	    else
	    {
	    	request.setAttribute("error", "Se ha producido un error");
	    	String vista = accion.getVista();
		      RequestDispatcher rq = request.getRequestDispatcher(vista);
		      rq.forward(request,response);
	    }
	    
	}
  
	public void setDataSources(DataSource dataSources) {
		this.dataSources = dataSources;
	}

   
	public DataSource getDataSources() {
		return this.dataSources;
	}

}
