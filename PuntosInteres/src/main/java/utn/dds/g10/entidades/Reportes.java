package utn.dds.g10.entidades;

public class Reportes {

	HistorialConsultas historialConsulta;	
	
	public Reportes(HistorialConsultas historial){
		historialConsulta = historial;
	}
	
	public void imprimirReportePorFecha(){
		
		for(ResultadoConsulta lista: historialConsulta.getConsultas()){
			
			System.out.println(lista.getFechaHora()+"-"+lista.getCantidadResultados());			
			
		}
		
	}
	
	
}
