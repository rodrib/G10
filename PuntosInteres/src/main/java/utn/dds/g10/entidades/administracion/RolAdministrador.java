package utn.dds.g10.entidades.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import utn.dds.g10.entidades.Menu;
import utn.dds.g10.entidades.administracion.acciones.Accion;
import utn.dds.g10.entidades.administracion.acciones.AuditarResultadoConsulta;
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name="RolAdministrador")
public class RolAdministrador extends Rol {
	
	private static final long serialVersionUID = 1L;

	public RolAdministrador()
	{
		this.setAcciones();
		this.setNombre(this.getClass().getSimpleName());
	}

	@Override
	public void setAcciones() {
		this.acciones  = new ArrayList<Accion>();
		this.acciones.add(new AuditarResultadoConsulta());
	}

	@Override
	public List<Menu> getMenues() {
		List<Menu> menues= new ArrayList<Menu>();
		 menues.add(new Menu("/PuntosInteres/faces/ABMAcciones.xhtml","Acciones"));
		 menues.add(new Menu("/PuntosInteres/faces/busquedaNueva.xhtml","Búsqueda"));
		 menues.add(new Menu("/PuntosInteres/faces/historial.xhtml","Historial"));
		return menues;
	}
}
