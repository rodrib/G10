import PuntosInteres.src.main.java.utn.dds.g10.entidades.Locacion;
import PuntosInteres.src.main.java.utn.dds.g10.entidades.TipoPoi;
import PuntosInteres.src.main.java.utn.dds.g10.entidades.ServicioCGP;

import java.time.LocalDate;
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
		if (servicios.isEmpty())
			return false;
		
		if (x == null){ // no se ingreso un valor en x
			Iterator i = servicios.iterator();
			while(i.hasNext()){
				ServicioCGP servicio = i.next();
				if(servicio.estaDisponible(fecha)){
					return true;
				}
			
			}
		}else{ // x es el nombre del servicio
			Iterator i = servicios.iterator();
			while(i.hasNext()){
				ServicioCGP servicio = i.next();
				if(servicio.nombre==x){
					return servicio.estaDisponible(fecha);
				}
			
			}
		}
		return false;
	}
}
