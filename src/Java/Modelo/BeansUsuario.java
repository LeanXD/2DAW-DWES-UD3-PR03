package Java.Modelo;

public class BeansUsuario {
	public String usuario;
	public String clave;
	public String nombre;
	
	public BeansUsuario(String usuario, String clave, String nombre) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
