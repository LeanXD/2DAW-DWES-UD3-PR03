/**
 * Acción: procesar login usuario
 */
package Java.Acciones;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import Java.Controlador.Accion;
import Java.Modelo.*;
import sun.tracing.NullProviderFactory;


public class AccionLogin implements Accion {

	private String vista;
	private  String vistaOK = "WEB-INF/menu.jsp";
	private final String vistaError = "VistaError.jsp";
	private BeansUsuario modelo = null;
	
	private ServletContext sc;
	private HttpSession sesion;
	private DataSource dataS;	
	

	public AccionLogin()
	{

	}
	
	public boolean ejecutar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		boolean resultado = true;
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		String usuario, clave, nombre, captcha;
		int numCaptcha = 0;
		
		usuario = request.getParameter("usuario");
		clave = request.getParameter("clave");
		captcha = request.getParameter("captcha");
		if(usuario!=null && clave != null){
			try {
				conexion = dataS.getConnection();
				st = conexion.createStatement();
				rs = st.executeQuery("select usuario, clave, nombre from pr03.usuarios where usuario = '"+usuario+"'");
				if (rs.next()) {
					if (!rs.getString("clave").equals(clave)) {
						sesion.setAttribute("ErrorLogin", "La clave no coincide.");
						vistaOK = "login.jsp";
					}else{
						//Creamos nuestro modelo de usuario.
						modelo = new BeansUsuario(rs.getString("usuario"), rs.getString("clave"), rs.getString("nombre"));
						//Comprobamos el captcha
						String idCaptcha = request.getParameter("idCaptcha");
						rs = st.executeQuery("select id, nombre, texto from captchas where id = "+idCaptcha);
						if(rs.next()){
							if(!rs.getString("texto").equals(captcha)){
								sesion.setAttribute("ErrorLogin", "Los captcha no coinciden.");
								//Si falla lo volvemos a generar
								sesion.setAttribute("captcha", newCaptcha(rs, st));
								vistaOK = "login.jsp";
							}else{
								sesion.setAttribute("sesionCerrada", "False");	
								sesion.removeAttribute("captcha");
							}
						}
					}
				}
				else
				{
					vistaOK = "login.jsp";
					request.setAttribute("ErrorLogin", "El usuario no existe.");
				}
			} catch (SQLException e) {
				request.setAttribute("ErrorSQL", "Fallo en la conexión");
				resultado = false;
			}
		}else{
			//Controlamos que entra por primera vez el usuario.
			//Le mandamos al usuario una clave captcha a rellenar 
			try {
				conexion = dataS.getConnection();
				st = conexion.createStatement();
				sesion.setAttribute("captcha", newCaptcha(rs, st));
				vistaOK = "login.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("ErrorSQL", "Fallo en la conexión");
				resultado = false;
			}
			
		}
		if (resultado==true){
			vista = vistaOK;
		}else{
			vista = vistaError;
		} 
		
		return resultado;
	}
	
	public BeansCaptcha newCaptcha(ResultSet rs, Statement st) throws SQLException{
		BeansCaptcha modeloC = null;
		
		int numCaptcha = 0;
		numCaptcha = (int) (Math.random()*2+0);
		rs = st.executeQuery("select id, nombre, texto from pr03.captchas where id = "+Integer.toString(numCaptcha));
		if(rs.next()){
			modeloC = new BeansCaptcha(rs.getString("nombre"), rs.getString("texto"), rs.getInt("id"));
		}
		return modeloC; 
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
	
	public void setDS(DataSource dataS)
	{
		this.dataS = dataS;
	}
}
