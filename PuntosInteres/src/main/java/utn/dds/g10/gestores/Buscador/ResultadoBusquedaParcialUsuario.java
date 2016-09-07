package utn.dds.g10.gestores.Buscador;

import java.util.ArrayList;
import java.util.List;

public class ResultadoBusquedaParcialUsuario {

	private String usuario;
	private List<ResultadoBusquedaParcial> resultados = new ArrayList<ResultadoBusquedaParcial>();
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public List<ResultadoBusquedaParcial> getResultados() {
		return resultados;
	}
	public void setResultados(List<ResultadoBusquedaParcial> resultados) {
		this.resultados = resultados;
	}
	
	public void agregarResultado(ResultadoBusquedaParcial resultado){
		resultados.add(resultado);
	}
	
	
}
