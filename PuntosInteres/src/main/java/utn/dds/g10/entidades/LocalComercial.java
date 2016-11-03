package utn.dds.g10.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import utn.dds.g10.DAO.DaoBase;
@Entity
public class LocalComercial extends TipoPoi {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private RubroLocal rubro;
	//List<DayOfWeek> diasDisponible;	

	@Override
	public float getDistanciaMaxima() {
		return rubro.getDistanciaMaxima();
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		return utn.dds.g10.Utiles.Util.CalcularDistancia(miLocacion, otraLocacion) < this.getDistanciaMaxima();
	}

	@Override
	public boolean estaDisponible(LocalDateTime fecha, String x){
		return EsDiaDisponible(fecha) && EsHorarioDisponible(fecha);
	}
	
	
	private boolean EsDiaDisponible(LocalDateTime fecha)
	{
		return rubro.EsDiaDisponible(fecha);
	}
	
	private boolean EsHorarioDisponible(LocalDateTime fecha)
	{
		return rubro.EsHorarioDisponible(fecha);
	}
	
	public RubroLocal getRubro() {
		return rubro;
	}

	public void setRubro(RubroLocal rubro) {
		this.rubro = rubro;
	}

	@Override
	public boolean CumpleCondicionBusqueda(String condicion) {
		// TODO Auto-generated method stub
		return  (this.getRubro() != null && this.getRubro().CumpleCondicionBusqueda(condicion));
	}

	@Override
	public String tipoPOI() {
		return "LocalComercial";
	}

	@Override
	public TipoPoi obtenerPOI(int id) {
		//buscar local -> buscar rubro
		ArrayList<LocalComercial> listaLocales = new ArrayList<LocalComercial>();
		listaLocales = (ArrayList<LocalComercial>) DaoBase.obtenerLocales();

		for (LocalComercial l : listaLocales) {
			if (l.getIdTipoPoi()==id)
				return l;
		}
		return null;
	}
}

