package utn.dds.g10.gestores.Buscador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utn.dds.g10.entidades.ResultadoConsulta;

public class HistorialConsultasUsuario {
	private List<ResultadoBusquedaTotalUsuario> consultasTotal = new ArrayList<ResultadoBusquedaTotalUsuario>();
	private List<ResultadoBusquedaParcialUsuario> consultasParcial = new ArrayList<ResultadoBusquedaParcialUsuario>();
	
	public List<ResultadoBusquedaTotalUsuario> getConsultasTotal() {
		return consultasTotal;
	}

	public void setConsultasTotal(List<ResultadoBusquedaTotalUsuario> consultasTotal) {
		this.consultasTotal = consultasTotal;
	}

	public List<ResultadoBusquedaParcialUsuario> getConsultasParcial() {
		return consultasParcial;
	}

	public void setConsultasParcial(
			List<ResultadoBusquedaParcialUsuario> consultasParcial) {
		this.consultasParcial = consultasParcial;
	}

	public void AgregarResultado(ResultadoConsulta resultado) {
		ResultadoBusquedaParcial resultadoParcial = new ResultadoBusquedaParcial();
		resultadoParcial.setCriterioBusqueda(resultado.getCriterioBusqueda());
		resultadoParcial.setCantidadResultados(resultado.getCantidadResultados());
		
		//Busca por usuario. Si ya existe, se busca por el criterio de busqueda
		if (!consultasParcial.isEmpty()){
		
		for (Iterator<ResultadoBusquedaParcialUsuario> consultaBusqueda = this.consultasParcial.iterator(); consultaBusqueda.hasNext();) {
			ResultadoBusquedaParcialUsuario resultadoUsuarioParcialEncontrado = consultaBusqueda.next();
						
			if  (resultadoUsuarioParcialEncontrado.getUsuario().equalsIgnoreCase(resultado.getUsuario())){
				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuarioParcialEncontrado.getResultados().iterator(); consultaBusquedaParcial.hasNext();) {
					ResultadoBusquedaParcial resultadoParcialEncontrado = consultaBusquedaParcial.next();
					//Revisar esta condicion, cuando guardar un nuevo criterio
					if (!resultadoParcialEncontrado.getCriterioBusqueda().equalsIgnoreCase(resultadoParcial.getCriterioBusqueda())){
						resultadoUsuarioParcialEncontrado.getResultados().add(resultadoParcial);
						
						AgregarResultadoTotal(resultado);
						
					}
					//Si el criterio de busqueda ya existe para ese usuario, no se agrega nada
					//Tampoco se cambia Total
				}
			}else{//Si el usuario no existe se crea un resultado nuevo con ese usuario y la busqueda realizada
				ResultadoBusquedaParcialUsuario resultadoUsuarioParcialNuevo = new ResultadoBusquedaParcialUsuario();
				resultadoUsuarioParcialNuevo.setUsuario(resultado.getUsuario());
				resultadoUsuarioParcialNuevo.agregarResultado(resultadoParcial);
				consultasParcial.add(resultadoUsuarioParcialNuevo);
				
				//Se crea el usuario nuevo en Total
				AgregarResultadoTotalNuevo(resultado);
			}
		}
		
		}else{
			ResultadoBusquedaParcialUsuario resultadoUsuarioParcialNuevo = new ResultadoBusquedaParcialUsuario();
			resultadoUsuarioParcialNuevo.setUsuario(resultado.getUsuario());
			resultadoUsuarioParcialNuevo.agregarResultado(resultadoParcial);
			consultasParcial.add(resultadoUsuarioParcialNuevo);
		}

	}
	
	public void AgregarResultadoTotal(ResultadoConsulta resultado) {
		//Se recorre buscando el usuario
		
		if (!consultasParcial.isEmpty()){
		
		for (Iterator<ResultadoBusquedaTotalUsuario> consultaBusqueda = this.consultasTotal.iterator(); consultaBusqueda.hasNext();) {
			ResultadoBusquedaTotalUsuario resultadoUsuarioTotalEncontrado = consultaBusqueda.next();
						
			if  (resultadoUsuarioTotalEncontrado.getUsuario().equalsIgnoreCase(resultado.getUsuario())){
				resultadoUsuarioTotalEncontrado.setCantidadResultados(resultadoUsuarioTotalEncontrado.getCantidadResultados()+resultado.getCantidadResultados());
			}
		}
		}else{
			ResultadoBusquedaTotalUsuario resultadoUsuarioTotalNuevo = new ResultadoBusquedaTotalUsuario();
			resultadoUsuarioTotalNuevo.setCantidadResultados(resultado.getCantidadResultados());
			resultadoUsuarioTotalNuevo.setUsuario(resultado.getUsuario());
			consultasTotal.add(resultadoUsuarioTotalNuevo);
		}
	}
	
	public void AgregarResultadoTotalNuevo(ResultadoConsulta resultado) {
		ResultadoBusquedaTotalUsuario resultadoUsuarioTotalNuevo = new ResultadoBusquedaTotalUsuario();
		resultadoUsuarioTotalNuevo.setCantidadResultados(resultado.getCantidadResultados());
		resultadoUsuarioTotalNuevo.setUsuario(resultado.getUsuario());
		consultasTotal.add(resultadoUsuarioTotalNuevo);
	}
	
}
