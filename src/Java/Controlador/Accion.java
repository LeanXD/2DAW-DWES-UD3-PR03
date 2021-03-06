package Java.Controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;


public interface Accion {

  public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException;
 
  public String getVista();
  
  public Object getModelo();
  
  public void setSc(ServletContext sc);

  public void setDS(DataSource ds);
  
  public void setSesion(HttpSession sesion);

}
