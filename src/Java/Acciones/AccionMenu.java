package Java.Acciones;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import Java.Controlador.Accion;
import Java.Modelo.BeansUsuario;

public class AccionMenu implements Accion {
	
	private String vista;
	private String vistaOK = "WEB-INF/menu.jsp";
	private final String vistaError = "VistaError.jsp";
	private BeansUsuario modelo = null;
	
	private ServletContext sc;
	private HttpSession sesion;
	private DataSource dataS;	

	@Override
	public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String estadoSesion = (String) sesion.getAttribute("sesionCerrada");
		boolean resultado = true;
		this.modelo = (BeansUsuario) sesion.getAttribute("modelo");
		if(estadoSesion!=null){
			if(estadoSesion.equals("True")){
				vistaOK = "index.html";
			}else{
				switch (request.getParameter("accion")) {
				case "Perfil Usuario":
					vistaOK = "WEB-INF/perfil.jsp";
					break;
				case "Salir":
					sesion.removeAttribute("modelo");
					sesion.removeAttribute("dataSource");
					sesion.setAttribute("sesionCerrada", "True");
					vistaOK = "index.html";
					break;

				default:
					break;
				}
			}
			
			
		}
		if (resultado==true){
			vista = vistaOK;
		}else{
			vista = vistaError;
		} 
		return resultado;
	}

	@Override
	public String getVista() {
		// TODO Auto-generated method stub
		return this.vista;
	}

	@Override
	public Object getModelo() {
		// TODO Auto-generated method stub
		return this.modelo;
	}

	@Override
	public void setSc(ServletContext sc) {
		// TODO Auto-generated method stub
		this.sc = sc;
		
	}

	@Override
	public void setDS(DataSource ds) {
		// TODO Auto-generated method stub
		this.dataS = ds;
	}

	@Override
	public void setSesion(HttpSession sesion) {
		// TODO Auto-generated method stub
		this.sesion = sesion;
	}

}
