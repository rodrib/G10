package utn.dds.g10.model;

import java.util.ArrayList;
import java.util.List;

import utn.dds.g10.entidades.administracion.Usuario;

public class CambiosAccion {
	private List<ElementoCambioAccion> listaAccionesCambiadas = new ArrayList<ElementoCambioAccion>();
	private String procesoEjecutado;
	private Usuario usuario;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<ElementoCambioAccion> getListaAccion() {
		return listaAccionesCambiadas;
	}
	public void agregarListaAccion(ElementoCambioAccion elem) {
		this.listaAccionesCambiadas.add(elem);
	}
	public void setListaAccion(List<ElementoCambioAccion> listaPoi) {
		this.listaAccionesCambiadas = listaPoi;
	}
	public String getProcesoEjecutado() {
		return procesoEjecutado;
	}
	public void setProcesoEjecutado(String procesoEjecutado) {
		this.procesoEjecutado = procesoEjecutado;
	}
}
