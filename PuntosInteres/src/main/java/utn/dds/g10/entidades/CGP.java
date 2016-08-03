package utn.dds.g10.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
public class CGP extends TipoPoi {

	String comuna;
	String zonas;
	String director;
	String domicilio;
	String telefono;
	ArrayList<ServicioCGP> servicios ;	
	
	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getZonas() {
		return zonas;
	}

	public void setZonas(String zonas) {
		this.zonas = zonas;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ArrayList<ServicioCGP> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<ServicioCGP> servicios) {
		this.servicios = servicios;
	}

	@Override
	public float getDistanciaMaxima() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaDisponible(LocalDateTime fecha, String x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CumpleCondicionBusqueda(String condicion) {
		// TODO Auto-generated method stub
		return false;
	}


//	List<ServicioCGP> servicios;	
//
//	@Override
//	public float getDistanciaMaxima() {
//		return 0;
//	}
//
//	@Override
//	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
//		return miLocacion.getCodigoComuna() == otraLocacion.getCodigoComuna();
//	}
//
//	@Override
//	public boolean estaDisponible(LocalDateTime fecha, String x) {
//		
//		if (x == ""){ // no se ingreso un valor en x
//			Iterator<ServicioCGP> i = servicios.iterator();
//			while(i.hasNext()){
//				ServicioCGP servicio = (ServicioCGP) i.next();
//				if(servicio.estaDisponible(fecha)){
//					return true;
//				}
//			
//			}
//		}else{ // x es el nombre del servicio
//			Iterator<ServicioCGP> i = servicios.iterator();
//			while(i.hasNext()){
//				ServicioCGP servicio = (ServicioCGP) i.next();
//				if(servicio.nombre==x){
//					return servicio.estaDisponible(fecha);
//				}
//			
//			}
//		}
//		return false;
//	}
//	
//	public List<ServicioCGP> getServicios() {
//		return servicios;
//	}
//
//	public void setServicios(List<ServicioCGP> servicios) {
//		this.servicios = servicios;
//	}
//
//	@Override
//	public boolean CumpleCondicionBusqueda(String condicion) {
//	
//		for (Iterator<ServicioCGP> serviciosBusqueda = this.servicios.iterator(); serviciosBusqueda.hasNext();) {
//			String nombre =serviciosBusqueda.next().getNombre();
//			if (nombre != null && nombre.contains(condicion))
//				return true;
//		}
//		
//		return false;
//	}

	
	
}
