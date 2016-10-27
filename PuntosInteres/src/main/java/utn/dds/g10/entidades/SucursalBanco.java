package utn.dds.g10.entidades;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

public class SucursalBanco extends TipoPoi {
	
	private static int HoraInicio = 10;
	private static int HoraFin = 15;
	
	private String nombreGerente;
	private List<String> servicios;

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

}
