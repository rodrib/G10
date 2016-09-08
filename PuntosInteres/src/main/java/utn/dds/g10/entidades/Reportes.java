package utn.dds.g10.entidades;

import utn.dds.g10.gestores.Buscador.HistorialConsultasFecha;
import utn.dds.g10.gestores.Buscador.HistorialConsultasUsuario;
import utn.dds.g10.gestores.Buscador.ResultadoBusquedaFecha;

public class Reportes {

	HistorialConsultasFecha histoConFecha;
	HistorialConsultasUsuario histoConUsuario;


	public Reportes(){
		histoConFecha= new HistorialConsultasFecha();
		histoConUsuario= new HistorialConsultasUsuario();
	}


	public HistorialConsultasFecha getHistoConFecha() {
		return histoConFecha;
	}


	public void setHistoConFecha(HistorialConsultasFecha histoConFecha) {
		this.histoConFecha = histoConFecha;
	}


	public HistorialConsultasUsuario getHistoConUsuario() {
		return histoConUsuario;
	}


	public void setHistoConUsuario(HistorialConsultasUsuario histoConUsuario) {
		this.histoConUsuario = histoConUsuario;
	}


	public void imprimirReporteCantBusquedas(){
		
		for(ResultadoBusquedaFecha resultBusqueda: histoConFecha.getConsultas()){
			System.out.println(resultBusqueda.getFechaHora()+"--"+resultBusqueda.getCantidadBusquedas());
		}
		
		
	
//Esto de aca abajo borrarlo
//	HistorialConsultas historialConsulta = new HistorialConsultas();	
//	
//	public Reportes(HistorialConsultas historial){
//		historialConsulta = historial;
//	}
//	
//	public void imprimirReportePorFecha(){
//		
//		for(ResultadoConsulta lista: historialConsulta.getConsultas()){
//			
//			System.out.println(lista.getFechaHora()+"-"+lista.getCantidadResultados());			
//			
//		}
//		
		
	}
	
	
}
