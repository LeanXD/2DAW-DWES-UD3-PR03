/**
 * Instancia objetos de tipo Acción.
 * Es una clase abstracta que impide que se puedan instanciar objetos de ella,
 * pero permite que se obtengan clases derivadas.
 * Se encarga de obtener los objetos Acción específicos para una determinada acción.
 */
package Java.Controlador;

import Java.Acciones.AccionIndex;
import Java.Acciones.AccionLogin;
import Java.Acciones.AccionMenu;
import Java.Modelo.*;

/**
 * Factoría que devuelve los objetos Accion que
 * procesarán las peticiones
 * @author Eduardo A. Ponce
 * @version 2.0
 */
public abstract class FactoriaAcciones {
	public static Accion creaAccion(String accion)
	  {
		Accion accionF = new AccionIndex();
		if(accion!=null){
			switch(accion){
				case "Inicio":
					accionF = new AccionLogin();
				break;
				case "Acceder":
					accionF = new AccionLogin();
				break;
				case "Perfil Usuario":
					accionF = new AccionMenu();
				break;
				case "Salir":
					accionF = new AccionMenu();
				break;
				case "Volver":
					accionF = new AccionMenu();
				break;
			}
		}
	    return accionF;
	  }

}
