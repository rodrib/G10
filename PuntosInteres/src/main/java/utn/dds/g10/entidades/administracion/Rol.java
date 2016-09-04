package utn.dds.g10.entidades.administracion;

import java.util.*;;

public abstract class Rol {

	List<String> modulos;

	public List<String> getModulos() {
		return modulos;
	}

	public void setModulos(List<String> modulos) {
		this.modulos = modulos;
	}
}
