package utn.dds.g10.gestores.Buscador;

import java.util.Iterator;
import java.util.List;

import utn.dds.g10.entidades.ResultadoConsulta;

public class HistorialConsultasParcialUsuario {
	private List<ResultadoBusquedaParcialUsuario> consultas;
	
	public List<ResultadoBusquedaParcialUsuario> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<ResultadoBusquedaParcialUsuario> consultas) {
		this.consultas = consultas;
	}

	public void AgregarResultado(ResultadoConsulta resultado) {
		ResultadoBusquedaParcial resultadoParcial = new ResultadoBusquedaParcial();
		resultadoParcial.setCriterioBusqueda(resultado.getCriterioBusqueda());
		resultadoParcial.setCantidadResultados(resultado.getCantidadResultados());
		
		//Busca por usuario. Si ya existe, se busca por el criterio de busqueda
		for (Iterator<ResultadoBusquedaParcialUsuario> consultaBusqueda = this.consultas.iterator(); consultaBusqueda.hasNext();) {
			ResultadoBusquedaParcialUsuario resultadoUsuarioParcialEncontrado = consultaBusqueda.next();
						
			if  (resultadoUsuarioParcialEncontrado.getUsuario().equalsIgnoreCase(resultado.getUsuario())){
				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuarioParcialEncontrado.getResultados().iterator(); consultaBusquedaParcial.hasNext();) {
					ResultadoBusquedaParcial resultadoParcialEncontrado = consultaBusquedaParcial.next();
					if (!resultadoParcialEncontrado.getCriterioBusqueda().equalsIgnoreCase(resultadoParcial.getCriterioBusqueda())){
						resultadoUsuarioParcialEncontrado.getResultados().add(resultadoParcial);
					}
					//Si el criterio de busqueda ya existe para ese usuario, no se agrega nada
				}
			}else{//Si el usuario no existe se crea un resultado nuevo con ese usuario y la busqueda realizada
				ResultadoBusquedaParcialUsuario resultadoUsuarioParcialNuevo = new ResultadoBusquedaParcialUsuario();
				resultadoUsuarioParcialNuevo.setUsuario(resultado.getUsuario());
				resultadoUsuarioParcialNuevo.agregarResultado(resultadoParcial);
				consultas.add(resultadoUsuarioParcialNuevo);
			}
		}	
	}

}
