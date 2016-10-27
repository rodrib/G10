package utn.dds.g10.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import utn.dds.g10.entidades.administracion.Rol;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.entidades.administracion.acciones.*;

@ManagedBean(name = "acciones")
@SessionScoped
public class abmAccionesBean implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;

	public int getId() {
		return id;
	}

	public void setId(int identificador) {
		this.id = identificador;
	}

	String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static ArrayList<AccionBean> getOrderlist() {
		return orderList;
	}

	private static final ArrayList<AccionBean> orderList = new ArrayList<AccionBean>(
			Arrays.asList(
					new AccionBean(1, AuditarResultadoConsulta.class
							.getSimpleName()), new AccionBean(2,
							AuditarTiempoConsulta.class.getSimpleName())));

	public ArrayList<AccionBean> getOrderList() {
		return orderList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String addAction() {
		
		boolean existe = false;
		for (AccionBean accionBean : orderList) {
			if (accionBean.id == this.id) 
			{
				existe = true;
				break;
			}
		}
		
		//Si no existe lo agrega.
		if(!existe)
		{
			orderList.add(new AccionBean(this.id, this.nombre));
		}

		return null;
	}

	public String deleteAction(AccionBean miAccion) {
		orderList.remove(miAccion);
		return null;
	}

	public String agregarAccionesUsuario() {
		Usuario miUsuario = new Usuario();
		miUsuario.setNombre("Usuario prueba");
		Rol miRol = new RolAdministrador();

		ArrayList<Accion> acciones = new ArrayList<Accion>();

		for (AccionBean accionBean : orderList) {

			switch (accionBean.id) {
			case 1:
				acciones.add(new AuditarResultadoConsulta());
				break;
			case 2:
				acciones.add(new AuditarTiempoConsulta());
				break;
			default:
				break;
			}
		}
		miRol.setAcciones(acciones);
		miUsuario.setRol(miRol);

		return null;
	}

	public static class AccionBean {
		int id;
		String nombre;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public AccionBean(int identificador, String nombre) {
			this.id = identificador;
			this.nombre = nombre;
		}
	}
}