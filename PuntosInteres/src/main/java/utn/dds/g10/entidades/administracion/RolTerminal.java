package utn.dds.g10.entidades.administracion;

import java.util.ArrayList;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import utn.dds.g10.entidades.administracion.acciones.Accion;
import utn.dds.g10.entidades.administracion.acciones.AuditarTiempoConsulta;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
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
	
}