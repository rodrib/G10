package utn.dds.g10.gestores.Buscador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utn.dds.g10.entidades.ResultadoConsulta;

public class HistorialConsultasUsuario {
	private static List<ResultadoBusquedaTotalUsuario> consultasTotal = new ArrayList<ResultadoBusquedaTotalUsuario>();
	private static List<ResultadoBusquedaParcialUsuario> consultasParcial = new ArrayList<ResultadoBusquedaParcialUsuario>();

	public static List<ResultadoBusquedaTotalUsuario> getConsultasTotal() {
		return consultasTotal;
	}

	public static void setConsultasTotal(
			List<ResultadoBusquedaTotalUsuario> consultasTotal) {
		HistorialConsultasUsuario.consultasTotal = consultasTotal;
	}

	public static List<ResultadoBusquedaParcialUsuario> getConsultasParcial() {
		return consultasParcial;
	}

	public static ResultadoBusquedaParcialUsuario buscarUsuario(
			String usuario) {
		int encontrado=0;
		ResultadoBusquedaParcialUsuario resultadoUsuarioParcialEncontrado = new ResultadoBusquedaParcialUsuario();
		// Busca por usuario. Si ya existe, se busca por el criterio de busqueda
		if (!consultasParcial.isEmpty()) {

			for (Iterator<ResultadoBusquedaParcialUsuario> consultaBusqueda = HistorialConsultasUsuario.consultasParcial
					.iterator(); consultaBusqueda.hasNext();) {
				resultadoUsuarioParcialEncontrado = consultaBusqueda.next();

				if (resultadoUsuarioParcialEncontrado.getUsuario()
						.equalsIgnoreCase(usuario)) {
					encontrado=1;
					break;
				}
			}
		}
		if (encontrado!=0)
			return resultadoUsuarioParcialEncontrado;
		else
			return new ResultadoBusquedaParcialUsuario();
	}
	
	public static ResultadoBusquedaParcialUsuario filtrarUsuarioFechaDesde(
			LocalDateTime fechaDesde,ResultadoBusquedaParcialUsuario resultado) {
		ResultadoBusquedaParcialUsuario resultadoUsuarioParcialFiltrado = new ResultadoBusquedaParcialUsuario();
		
		ResultadoBusquedaParcial resultadoBusquedaParcialEncontrado = new ResultadoBusquedaParcial();
		for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultado.getResultados()
				.iterator(); consultaBusquedaParcial.hasNext();) {
			
			resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();
			resultadoUsuarioParcialFiltrado.setUsuario(resultado.getUsuario());
			if (resultadoBusquedaParcialEncontrado.getFecha().isAfter(fechaDesde)){
				resultadoUsuarioParcialFiltrado.getResultados().add(resultadoBusquedaParcialEncontrado);
			}
		}
		return resultadoUsuarioParcialFiltrado;
	}
	
	public static ResultadoBusquedaParcialUsuario filtrarUsuarioFechaHasta(
			LocalDateTime fechaHasta,ResultadoBusquedaParcialUsuario resultado) {
		ResultadoBusquedaParcialUsuario resultadoUsuarioParcialFiltrado = new ResultadoBusquedaParcialUsuario();
		
		ResultadoBusquedaParcial resultadoBusquedaParcialEncontrado = new ResultadoBusquedaParcial();
		for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultado.getResultados()
				.iterator(); consultaBusquedaParcial.hasNext();) {
			
			resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();
			resultadoUsuarioParcialFiltrado.setUsuario(resultado.getUsuario());
			if (resultadoBusquedaParcialEncontrado.getFecha().isBefore(fechaHasta)){
				resultadoUsuarioParcialFiltrado.getResultados().add(resultadoBusquedaParcialEncontrado);
			}
		}
		return resultadoUsuarioParcialFiltrado;
	}
	
	public static ResultadoBusquedaParcialUsuario filtrarUsuarioFechaDesdeHasta(
			LocalDateTime fechaDesde,LocalDateTime fechaHasta,ResultadoBusquedaParcialUsuario resultado) {
		ResultadoBusquedaParcialUsuario resultadoUsuarioParcialFiltrado = new ResultadoBusquedaParcialUsuario();
		
		ResultadoBusquedaParcial resultadoBusquedaParcialEncontrado = new ResultadoBusquedaParcial();
		for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultado.getResultados()
				.iterator(); consultaBusquedaParcial.hasNext();) {
			
			resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();
			resultadoUsuarioParcialFiltrado.setUsuario(resultado.getUsuario());
			if (resultadoBusquedaParcialEncontrado.getFecha().isBefore(fechaHasta)&&resultadoBusquedaParcialEncontrado.getFecha().isAfter(fechaDesde)){
				resultadoUsuarioParcialFiltrado.getResultados().add(resultadoBusquedaParcialEncontrado);
			}
		}
		return resultadoUsuarioParcialFiltrado;
	}
	
	public static List<ResultadoBusquedaParcialUsuario> buscarFechaDesde(
			LocalDateTime fechaDesde) {
		List<ResultadoBusquedaParcialUsuario> listaResultado = new ArrayList<ResultadoBusquedaParcialUsuario>();
		ResultadoBusquedaParcialUsuario resultadoUsuarioParcialEncontrado = new ResultadoBusquedaParcialUsuario();

		if (!consultasParcial.isEmpty()) {

			for (Iterator<ResultadoBusquedaParcialUsuario> consultaBusqueda = HistorialConsultasUsuario.consultasParcial
					.iterator(); consultaBusqueda.hasNext();) {
				resultadoUsuarioParcialEncontrado = consultaBusqueda.next();
				
				ResultadoBusquedaParcial resultadoBusquedaParcialEncontrado = new ResultadoBusquedaParcial();
				ResultadoBusquedaParcialUsuario resultadoParcialAgregado = new ResultadoBusquedaParcialUsuario();
				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuarioParcialEncontrado.getResultados()
						.iterator(); consultaBusquedaParcial.hasNext();) {
					
					resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();
					resultadoParcialAgregado.setUsuario(resultadoUsuarioParcialEncontrado.getUsuario());
					if (resultadoBusquedaParcialEncontrado.getFecha().isAfter(fechaDesde)){
						resultadoParcialAgregado.getResultados().add(resultadoBusquedaParcialEncontrado);
					}
				}
				listaResultado.add(resultadoParcialAgregado);
			}
		}
		return listaResultado;
	}
	
	public static List<ResultadoBusquedaParcialUsuario> buscarFechaHasta(
			LocalDateTime fechaHasta) {
		List<ResultadoBusquedaParcialUsuario> listaResultado = new ArrayList<ResultadoBusquedaParcialUsuario>();
		ResultadoBusquedaParcialUsuario resultadoUsuarioParcialEncontrado = new ResultadoBusquedaParcialUsuario();

		if (!consultasParcial.isEmpty()) {

			for (Iterator<ResultadoBusquedaParcialUsuario> consultaBusqueda = HistorialConsultasUsuario.consultasParcial
					.iterator(); consultaBusqueda.hasNext();) {
				resultadoUsuarioParcialEncontrado = consultaBusqueda.next();
				
				ResultadoBusquedaParcial resultadoBusquedaParcialEncontrado = new ResultadoBusquedaParcial();
				ResultadoBusquedaParcialUsuario resultadoParcialAgregado = new ResultadoBusquedaParcialUsuario();
				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuarioParcialEncontrado.getResultados()
						.iterator(); consultaBusquedaParcial.hasNext();) {
					
					resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();
					resultadoParcialAgregado.setUsuario(resultadoUsuarioParcialEncontrado.getUsuario());
					if (resultadoBusquedaParcialEncontrado.getFecha().isBefore(fechaHasta)){
						resultadoParcialAgregado.getResultados().add(resultadoBusquedaParcialEncontrado);
					}
				}
				listaResultado.add(resultadoParcialAgregado);
			}
		}
		return listaResultado;
	}
	
	public static List<ResultadoBusquedaParcialUsuario> buscarFechaDesdeHasta(
			LocalDateTime fechaDesde,LocalDateTime fechaHasta) {
		List<ResultadoBusquedaParcialUsuario> listaResultado = new ArrayList<ResultadoBusquedaParcialUsuario>();
		ResultadoBusquedaParcialUsuario resultadoUsuarioParcialEncontrado = new ResultadoBusquedaParcialUsuario();

		if (!consultasParcial.isEmpty()) {

			for (Iterator<ResultadoBusquedaParcialUsuario> consultaBusqueda = HistorialConsultasUsuario.consultasParcial
					.iterator(); consultaBusqueda.hasNext();) {
				resultadoUsuarioParcialEncontrado = consultaBusqueda.next();
				
				ResultadoBusquedaParcial resultadoBusquedaParcialEncontrado = new ResultadoBusquedaParcial();
				ResultadoBusquedaParcialUsuario resultadoParcialAgregado = new ResultadoBusquedaParcialUsuario();
				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuarioParcialEncontrado.getResultados()
						.iterator(); consultaBusquedaParcial.hasNext();) {
					
					resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();
					resultadoParcialAgregado.setUsuario(resultadoUsuarioParcialEncontrado.getUsuario());
					if (resultadoBusquedaParcialEncontrado.getFecha().isBefore(fechaHasta)&&resultadoBusquedaParcialEncontrado.getFecha().isAfter(fechaDesde)){
						resultadoParcialAgregado.getResultados().add(resultadoBusquedaParcialEncontrado);
					}
				}
				listaResultado.add(resultadoParcialAgregado);
			}
		}
		return listaResultado;
	}

	public static void setConsultasParcial(
			List<ResultadoBusquedaParcialUsuario> consultasParcial) {
		HistorialConsultasUsuario.consultasParcial = consultasParcial;
	}

	public void AgregarResultado(ResultadoConsulta resultado) {
		ResultadoBusquedaParcial resultadoParcial = new ResultadoBusquedaParcial();
		resultadoParcial.setCriterioBusqueda(resultado.getCriterioBusqueda());
		resultadoParcial.setCantidadResultados(resultado
				.getCantidadResultados());
		resultadoParcial.setFecha(LocalDateTime.now());
		resultadoParcial.setListaPOISbusquedaParcial(resultado.getPuntos());
		ResultadoBusquedaParcialUsuario resultadoUsuarioParcialEncontrado = new ResultadoBusquedaParcialUsuario();

		int usuarioEncontrado = 0;
		int criterioEncontrado = 0;

		// Busca por usuario. Si ya existe, se busca por el criterio de busqueda
		if (!consultasParcial.isEmpty()) {

			for (Iterator<ResultadoBusquedaParcialUsuario> consultaBusqueda = HistorialConsultasUsuario.consultasParcial
					.iterator(); consultaBusqueda.hasNext();) {
				resultadoUsuarioParcialEncontrado = consultaBusqueda.next();

				if (resultadoUsuarioParcialEncontrado.getUsuario()
						.equalsIgnoreCase(resultado.getUsuario())) {
					usuarioEncontrado = 1;
					break;
				}
			}

			if (usuarioEncontrado == 1) {

				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuarioParcialEncontrado
						.getResultados().iterator(); consultaBusquedaParcial
						.hasNext();) {
					ResultadoBusquedaParcial resultadoParcialEncontrado = consultaBusquedaParcial
							.next();
					// Revisar esta condicion, cuando guardar un nuevo criterio
					if (resultadoParcialEncontrado.getCriterioBusqueda()
							.equalsIgnoreCase(
									resultadoParcial.getCriterioBusqueda())) {
						criterioEncontrado = 1;
						break;
					}
					// Si el criterio de busqueda ya existe para ese usuario, no
					// se agrega nada
					// Tampoco se cambia Total
				}

				if (criterioEncontrado == 0) {
					resultadoUsuarioParcialEncontrado.getResultados().add(
							resultadoParcial);
					AgregarResultadoTotal(resultado);
				}

			} else {// Si el usuario no existe se crea un resultado nuevo con
					// ese usuario y la busqueda realizada
				ResultadoBusquedaParcialUsuario resultadoUsuarioParcialNuevo = new ResultadoBusquedaParcialUsuario();
				resultadoUsuarioParcialNuevo.setUsuario(resultado.getUsuario());
				resultadoUsuarioParcialNuevo.agregarResultado(resultadoParcial);
				consultasParcial.add(resultadoUsuarioParcialNuevo);

				// Se crea el usuario nuevo en Total
				AgregarResultadoTotalNuevo(resultado);
			}

		} else {
			ResultadoBusquedaParcialUsuario resultadoUsuarioParcialNuevo = new ResultadoBusquedaParcialUsuario();
			resultadoUsuarioParcialNuevo.setUsuario(resultado.getUsuario());
			resultadoUsuarioParcialNuevo.agregarResultado(resultadoParcial);
			consultasParcial.add(resultadoUsuarioParcialNuevo);
			AgregarResultadoTotalNuevo(resultado);

		}

	}

	public void AgregarResultadoTotal(ResultadoConsulta resultado) {
		// Se recorre buscando el usuario

		if (!consultasTotal.isEmpty()) {

			for (Iterator<ResultadoBusquedaTotalUsuario> consultaBusqueda = HistorialConsultasUsuario.consultasTotal
					.iterator(); consultaBusqueda.hasNext();) {
				ResultadoBusquedaTotalUsuario resultadoUsuarioTotalEncontrado = consultaBusqueda
						.next();

				if (resultadoUsuarioTotalEncontrado.getUsuario()
						.equalsIgnoreCase(resultado.getUsuario())) {
					resultadoUsuarioTotalEncontrado
							.setCantidadResultados(resultadoUsuarioTotalEncontrado
									.getCantidadResultados()
									+ resultado.getCantidadResultados());
				}
			}
		} else {
			ResultadoBusquedaTotalUsuario resultadoUsuarioTotalNuevo = new ResultadoBusquedaTotalUsuario();
			resultadoUsuarioTotalNuevo.setCantidadResultados(resultado
					.getCantidadResultados());
			resultadoUsuarioTotalNuevo.setUsuario(resultado.getUsuario());
			consultasTotal.add(resultadoUsuarioTotalNuevo);
		}
	}

	public void AgregarResultadoTotalNuevo(ResultadoConsulta resultado) {
		ResultadoBusquedaTotalUsuario resultadoUsuarioTotalNuevo = new ResultadoBusquedaTotalUsuario();
		resultadoUsuarioTotalNuevo.setCantidadResultados(resultado
				.getCantidadResultados());
		resultadoUsuarioTotalNuevo.setUsuario(resultado.getUsuario());
		consultasTotal.add(resultadoUsuarioTotalNuevo);
	}

}
