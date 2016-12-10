package utn.dds.g10.entidades.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import utn.dds.g10.entidades.Menu;
import utn.dds.g10.entidades.administracion.acciones.Accion;
import utn.dds.g10.entidades.administracion.acciones.AuditarTiempoConsulta;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name="RolTerminal")
//@DiscriminatorValue("RolTerminal")
public class RolTerminal extends Rol {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RolTerminal() {
		this.setAcciones();
		this.setNombre(this.getClass().getSimpleName());
	}
	
	
	
	@Override
	public void setAcciones() {
		this.acciones = new ArrayList<Accion>();
		this.acciones.add(new AuditarTiempoConsulta());
	}


	@Override
	public List<Menu> getMenues() {
		List<Menu> menues= new ArrayList<Menu>();
		 menues.add(new Menu("/PuntosInteres/faces/busquedaNueva.xhtml","Búsqueda"));
		return menues;
	}
	
}