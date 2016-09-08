package utn.dds.g10.gestores.Buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utn.dds.g10.entidades.ResultadoConsulta;

public class HistorialConsultasFecha {
	private List<ResultadoBusquedaFecha> consultas = new ArrayList<ResultadoBusquedaFecha>();
	static final int INICIAL = 1;
	
	public List<ResultadoBusquedaFecha> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<ResultadoBusquedaFecha> consultas) {
		this.consultas = consultas;
	}

	public void AgregarResultado(ResultadoConsulta resultado) {
		ResultadoBusquedaFecha resultadoFecha = new ResultadoBusquedaFecha();
		resultadoFecha.setFechaHora(resultado.getFechaHora());
		
		//Busca por fecha. Si ya existe, le aumenta la cantidad de busquedas. Caso contrario, agrega un resultado nuevo
		if (!consultas.isEmpty()){
		
		for (Iterator<ResultadoBusquedaFecha> consultaBusqueda = this.consultas.iterator(); consultaBusqueda.hasNext();) {
			ResultadoBusquedaFecha resultadoFechaEncontrado = consultaBusqueda.next();
			LocalDate fechaBusqueda = resultadoFechaEncontrado.getFechaHora();
						
			if  (fechaBusqueda.isEqual(resultadoFecha.getFechaHora())){
				resultadoFechaEncontrado.setCantidadBusquedas(resultadoFechaEncontrado.getCantidadBusquedas()+1);
				consultas.add(resultadoFechaEncontrado);
			}
			else{
				resultadoFecha.setCantidadBusquedas(INICIAL);
				consultas.add(resultadoFecha);
			}		
		}
		
		}else{
			resultadoFecha.setCantidadBusquedas(INICIAL);
			consultas.add(resultadoFecha);
		}
	}

}
