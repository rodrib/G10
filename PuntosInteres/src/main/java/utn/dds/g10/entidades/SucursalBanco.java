package utn.dds.g10.entidades;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import utn.dds.g10.DAO.DaoRelacional;

@Entity
@Table(name = "sucursalBanco")
//@AttributeOverride(column = @Column, name = "poi")
public class SucursalBanco extends TipoPoi {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column
	private static int HoraInicio = 10;
	@Column
	private static int HoraFin = 15;
	@Column
	private String nombreGerente;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="texto", joinColumns=@JoinColumn(name="id"))
	private List<String> servicios = new ArrayList<String>();

	public String getNombreGerente() {
		return nombreGerente;
	}

	public void setNombreGerente(String nombreGerente) {
		this.nombreGerente = nombreGerente;
	}

	public List<String> getServicios() {
		return servicios;
	}

	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}

	@Override
	public float getDistanciaMaxima() {
		return super.distanciaDefecto;
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		return utn.dds.g10.Utiles.Util.CalcularDistancia(miLocacion, otraLocacion) < getDistanciaMaxima();
	}

	@Override
	public boolean estaDisponible(LocalDateTime fecha, String x) {
		return EsDiaSemana(fecha) && EsHorarioDisponible(fecha);
	}
	
	
	private boolean EsDiaSemana(LocalDateTime fecha)
	{
		return fecha.getDayOfWeek() != DayOfWeek.SUNDAY && fecha.getDayOfWeek() != DayOfWeek.SATURDAY;
	}
	
	private boolean EsHorarioDisponible(LocalDateTime fecha)
	{
		return  fecha.getHour() > HoraInicio && fecha.getHour() < HoraFin;
	}

	@Override
	public boolean CumpleCondicionBusqueda(String condicion) {
	
		if (this.servicios!=null){
			for (Iterator<String> serviciosBusqueda = this.servicios.iterator(); serviciosBusqueda.hasNext();) {
				String nombreServicio =serviciosBusqueda.next();
							
				if  (nombreServicio.equals(condicion)){
					return true;				
				}		
			}
		}
		return false;
	}

	@Override
	public String tipoPOI() {
		return "SucursalBanco";
	}

	@Override
	public TipoPoi obtenerPOI(int id) {
//		ArrayList<SucursalBanco> listaBancos = new ArrayList<SucursalBanco>();
//		listaBancos = (ArrayList<SucursalBanco>) DaoRelacional.obtenerBancos();
//
//		for (SucursalBanco b : listaBancos) {
//			if (b.getIdTipoPoi()==id)
//				return b;
//		}
		return null;
	}

}