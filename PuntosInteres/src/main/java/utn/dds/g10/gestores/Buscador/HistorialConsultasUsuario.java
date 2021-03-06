package utn.dds.g10.gestores.Buscador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utn.dds.g10.DAO.Dao;
import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.Kiosco;
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.entidades.ServicioCGP;
import utn.dds.g10.entidades.SucursalBanco;

public class HistorialConsultasUsuario {
	private static List<ResultadoBusquedaTotalUsuario> consultasTotal = new ArrayList<ResultadoBusquedaTotalUsuario>();
	
	private static List<ResultadoBusquedaParcialUsuario> consultasParcial = new ArrayList<ResultadoBusquedaParcialUsuario>();
	
	public HistorialConsultasUsuario(){
		
		if (consultasParcial.isEmpty()) {
		
		//Obtiene el listado de la base de datos
		List<ResultadoBusquedaParcialUsuario> consultasParcialInicial = new ArrayList<ResultadoBusquedaParcialUsuario>();
		
		consultasParcialInicial = DaoRelacional.obtenerResultados();
		
		//Inicializa el listado
		for (Iterator<ResultadoBusquedaParcialUsuario> iterador = consultasParcialInicial.iterator(); iterador
				.hasNext();) {
			
			ResultadoBusquedaParcialUsuario resultadoObtenido = new ResultadoBusquedaParcialUsuario();
			resultadoObtenido = iterador.next();
			
			obtenerResultado(resultadoObtenido);
			
			consultasParcial.add(resultadoObtenido);
		}
		
		}

	}
	
	
	private ResultadoBusquedaParcialUsuario obtenerResultado(ResultadoBusquedaParcialUsuario resultado){
		ResultadoBusquedaParcialUsuario resultadoReconstruido = new ResultadoBusquedaParcialUsuario();
		resultadoReconstruido.setId(resultado.getId());
		resultadoReconstruido.setUsuario(resultado.getUsuario());
		resultadoReconstruido.setResultados(resultado.getResultados());

		return resultadoReconstruido;
	}
	
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
	
	public static int buscarUsuarioIndice(
			ResultadoBusquedaParcialUsuario resultadoUsuarioParcialEncontrado) {
		
		// Busca por usuario. Si ya existe, se busca por el criterio de busqueda

		int i;
			for (i=0;i<consultasParcial.size(); i++ ) {
				ResultadoBusquedaParcialUsuario elemResul = consultasParcial.get(i);

				if (resultadoUsuarioParcialEncontrado.getUsuario()
						.equalsIgnoreCase(elemResul.getUsuario())) {
					break;
				}
			}
		
		return i;
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
		
		Dao repositorio = new DaoRelacional();
		
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
		resultadoParcial.setCantidadResultados(resultado.getCantidadResultados());
		resultadoParcial.setFecha(LocalDateTime.now());
		
//		List<POI> resultadoPOISsinfiltrar = new ArrayList<POI>();
//		resultadoPOISsinfiltrar=resultado.getPuntos();
		
//		List<POI> resultadoPOIS = 		
//		resultadoParcial.setListaPOISbusquedaParcial();
		
		//resultadoParcial.setListaPOISbusquedaParcial(filtrarRepetidos(resultadoPOISsinfiltrar));
		resultadoParcial.setListaPOISbusquedaParcial(resultado.getPuntos());
		ResultadoBusquedaParcialUsuario resultadoUsuarioParcialEncontrado = new ResultadoBusquedaParcialUsuario();

		int usuarioEncontrado = 0;
		//int criterioEncontrado = 0;

		// Busca por usuario. Si ya existe, se busca por el criterio de busqueda
		
		
		if (!consultasParcial.isEmpty()) {
			for (Iterator<ResultadoBusquedaParcialUsuario> consultaBusqueda = consultasParcial
					.iterator(); consultaBusqueda.hasNext();) {
				resultadoUsuarioParcialEncontrado = consultaBusqueda.next();

				if (resultadoUsuarioParcialEncontrado.getUsuario()
						.equalsIgnoreCase(resultado.getUsuario())) {
					usuarioEncontrado = 1;
					break;
				}
			}

			if (usuarioEncontrado == 1) {

//				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuarioParcialEncontrado
//						.getResultados().iterator(); consultaBusquedaParcial
//						.hasNext();) {
//					ResultadoBusquedaParcial resultadoParcialEncontrado = consultaBusquedaParcial
//							.next();
//					// Revisar esta condicion, cuando guardar un nuevo criterio
//					if (resultadoParcialEncontrado.getCriterioBusqueda()
//							.equalsIgnoreCase(
//									resultadoParcial.getCriterioBusqueda())) {
//						criterioEncontrado = 1;
//						break;
//					}
//					// Si el criterio de busqueda ya existe para ese usuario, no
//					// se agrega nada
//					// Tampoco se cambia Total
//				}

//				if (criterioEncontrado == 0) {
					resultadoUsuarioParcialEncontrado.getResultados().add(
							resultadoParcial);
					
					//Buscar en la lista consultasParcial y agregarlo al listado
					
					resultadoParcial.setResultado(resultadoUsuarioParcialEncontrado);
					//Se agrega resultado nuevo en la base
					DaoRelacional.mergeEntidad(resultadoParcial);	
					resultadoUsuarioParcialEncontrado.getResultados().add(resultadoParcial);
					
					int i = buscarUsuarioIndice(resultadoUsuarioParcialEncontrado);
					
					consultasParcial.remove(i);
					consultasParcial.add(resultadoUsuarioParcialEncontrado);
					
					//repositorio.modificarEntidad(resultadoUsuarioParcialEncontrado);	
					
					AgregarResultadoTotal(resultado);
//				}

			} else {// Si el usuario no existe se crea un resultado nuevo con
					// ese usuario y la busqueda realizada
				ResultadoBusquedaParcialUsuario resultadoUsuarioParcialNuevo = new ResultadoBusquedaParcialUsuario();
				resultadoUsuarioParcialNuevo.setUsuario(resultado.getUsuario());
				
//				List<ResultadoBusquedaParcial> resultados = new ArrayList<ResultadoBusquedaParcial>();
//				resultados.add(resultadoParcial);
//				
//				resultadoUsuarioParcialNuevo.setResultados(resultados);					
							
				resultadoUsuarioParcialNuevo.agregarResultado(resultadoParcial);
				resultadoParcial.setResultado(resultadoUsuarioParcialNuevo);
				consultasParcial.add(resultadoUsuarioParcialNuevo);
				
				//Se agrega resultado nuevo en la base
				DaoRelacional.crearEntidadIdLong(resultadoParcial);	

				// Se crea el usuario nuevo en Total
				AgregarResultadoTotalNuevo(resultado);
			}

		} else {
			ResultadoBusquedaParcialUsuario resultadoUsuarioParcialNuevo = new ResultadoBusquedaParcialUsuario();
			resultadoUsuarioParcialNuevo.setUsuario(resultado.getUsuario());
			resultadoUsuarioParcialNuevo.agregarResultado(resultadoParcial);
			resultadoParcial.setResultado(resultadoUsuarioParcialNuevo);
			consultasParcial.add(resultadoUsuarioParcialNuevo);			
			
			//Se agrega resultado nuevo en la base
			DaoRelacional.crearEntidadIdLong(resultadoParcial);	
			
			AgregarResultadoTotalNuevo(resultado);

		}

	}
	
	private static final List<POI> listaPOIsAuxiliar =
			new ArrayList<POI>();

	public List<POI> getPoiList() {
		return listaPOIsAuxiliar;
	}
	
	public List<POI> filtrarRepetidos(List<POI> listaPois){
		
		listaPOIsAuxiliar.clear();
		
		for (int j = 0; j < (listaPois.size()); j++) {
			Long idPOI = listaPois.get(j).getId();
				int cantResultado= 0;
				for (int m = 0; m < (listaPOIsAuxiliar.size()); m++) {
					if(listaPOIsAuxiliar.get(m).getId()==(idPOI)){
						cantResultado =1;
					}								
				}
				if(cantResultado==0){
					listaPOIsAuxiliar.add(listaPois.get(j));								
				}						
		}		
		
		return listaPOIsAuxiliar;		
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
