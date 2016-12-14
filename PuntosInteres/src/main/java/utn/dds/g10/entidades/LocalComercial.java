package utn.dds.g10.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import utn.dds.g10.DAO.DaoRelacional;
@Entity
@Table(name = "TipoPoi_LocalComercial")
public class LocalComercial extends TipoPoi {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)	
    @JoinColumn(name = "id_rubro")
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
//		ArrayList<LocalComercial> listaLocales = new ArrayList<LocalComercial>();
//		listaLocales = (ArrayList<LocalComercial>) DaoRelacional.obtenerLocales();
//
//		for (LocalComercial l : listaLocales) {
//			if (l.getIdTipoPoi()==id)
//				return l;
//		}
		return null;
	}
	
	private ArrayList<String> servicios = new ArrayList<String>();
	
		public ArrayList<String> getServicios() {
			return servicios;
	 	}	

}

