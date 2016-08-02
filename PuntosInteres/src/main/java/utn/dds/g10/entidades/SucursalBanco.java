package utn.dds.g10.entidades;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class SucursalBanco extends TipoPoi {
	
	private static int HoraInicio = 10;
	private static int HoraFin = 15;
	
	private String nombreSucursal;
	private String nombreGerente;

	public String getNombreGerente() {
		return nombreGerente;
	}

	public void setNombreGerente(String nombreGerente) {
		this.nombreGerente = nombreGerente;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
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
		//En este caso al no tener rubro, ni servicios, hacemos que siempre sea válido.
		return false;
	}

}
