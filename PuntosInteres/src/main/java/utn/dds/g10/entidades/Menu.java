package utn.dds.g10.entidades;

public class Menu {
	String link;
	String nombre;
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Menu(String link, String nombre)
	{
		this.link = link;
		this.nombre = nombre;
	}

}
