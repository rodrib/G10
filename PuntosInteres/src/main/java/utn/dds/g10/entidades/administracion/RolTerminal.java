package utn.dds.g10.entidades.administracion;

import java.util.ArrayList;

import javax.persistence.Entity;

import utn.dds.g10.entidades.administracion.acciones.Accion;
import utn.dds.g10.entidades.administracion.acciones.AuditarTiempoConsulta;

@Entity
public class RolTerminal extends Rol {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RolTerminal() {
		this.setAcciones();
	}
	
	@Override
	public void setAcciones() {
		this.acciones = new ArrayList<Accion>();
		this.acciones.add(new AuditarTiempoConsulta());
	}
	
}