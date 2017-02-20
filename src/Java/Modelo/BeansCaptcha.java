package Java.Modelo;

public class BeansCaptcha {
	public String nombre;
	public String texto;
	public int id;
	
	public BeansCaptcha(String nombre, String texto, int id) {
		super();
		this.nombre = nombre;
		this.texto = texto;
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
