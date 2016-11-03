package utn.dds.g10.entidades;

import java.time.LocalDateTime;

import javax.persistence.Entity;
@Entity
public class ParadaColectivo extends TipoPoi {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public float getDistanciaMaxima() {
		return 100;
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		return utn.dds.g10.Utiles.Util.CalcularDistancia(miLocacion, otraLocacion) < getDistanciaMaxima();
	}

	@Override
	public boolean estaDisponible(LocalDateTime fecha, String x) {
		return true;
	}

	@Override
	public boolean CumpleCondicionBusqueda(String condicion) {
		//En este caso al no tener rubro, ni servicios, hacemos que siempre sea válido.
		return false;
	}
	
	@Override
	public String tipoPOI() {
		return "ParadaColectivo";
	}

	@Override
	public TipoPoi obtenerPOI(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
