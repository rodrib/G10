package utn.dds.g10.entidades;

import java.util.List;

public class HistorialConsultas {
	private List<ResultadoConsulta> consultas;
	
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
