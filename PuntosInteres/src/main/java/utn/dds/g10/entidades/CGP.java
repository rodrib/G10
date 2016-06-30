package utn.dds.g10.entidades;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
public class CGP extends TipoPoi {

	List<ServicioCGP> servicios;	

	@Override
	public float getDistanciaMaxima() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		// TODO Auto-generated method stub
		return miLocacion.getCodigoComuna() == otraLocacion.getCodigoComuna();
	}

	@Override
	public boolean estaDisponible(LocalDateTime fecha, String x) {
		// TODO Auto-generated method stub
		
		if (x == ""){ // no se ingreso un valor en x
			Iterator<ServicioCGP> i = servicios.iterator();
			while(i.hasNext()){
				ServicioCGP servicio = (ServicioCGP) i.next();
				if(servicio.estaDisponible(fecha)){
					return true;
				}
			
			}
		}else{ // x es el nombre del servicio
			Iterator<ServicioCGP> i = servicios.iterator();
			while(i.hasNext()){
				ServicioCGP servicio = (ServicioCGP) i.next();
				if(servicio.nombre==x){
					return servicio.estaDisponible(fecha);
				}
			
			}
		}
		return false;
	}
	
	public List<ServicioCGP> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioCGP> servicios) {
		this.servicios = servicios;
	}

}
