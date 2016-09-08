package utn.dds.g10.entidades;

import java.util.ArrayList;
import java.util.List;

public class HistorialConsultas {
	private List<ResultadoConsulta> consultas = new ArrayList<ResultadoConsulta>();
	
	public List<ResultadoConsulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<ResultadoConsulta> consultas) {
		this.consultas = consultas;
	}

	

	public void AgregarResultado(ResultadoConsulta resultado) {
		consultas.add(resultado);
	}

}
