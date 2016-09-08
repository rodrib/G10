package utn.dds.g10.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.entidades.HistorialConsultas;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.Reportes;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.gestores.GestorPoi;

public class BusquedaDePuntosTest {

	ResultadoConsulta resultadoConsulta;
	List<POI> listaPuntos;
	
	POI puntoUno;
	POI puntoDos;
	String usuario;
	
	
	GestorPoi miGestor;

	private void InicializarTest() {

		//Inicializo Gestor
		this.miGestor = new GestorPoi();
		
		//Inicializo POI
		this.puntoUno = new POI();
		this.puntoDos = new POI();
		
		usuario = "userTest";
	}

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}

	@Test
	public void ExisteAlMenosUnBancoFrances() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Banco Frances",usuario);
//		LocalDate date;
//		resultado.setFechaHora("2016-09-07");
		//ResultadoConsulta resultado1 = miGestor.BuscarPoi("Kiosco",usuario);
		//ResultadoConsulta resultado2 = miGestor.BuscarPoi("114",usuario);
		HistorialConsultas historial = miGestor.listadoHistorialConsultas();
		Reportes reporte = new Reportes(historial) ;
		reporte.imprimirReportePorFecha();
		
		assertTrue("Existe al menos un banco Frances en la lista de Puntos de Interes", resultado.getPuntos().size() > 0 );

	}
	
	@Test
	public void ExistenKioscos() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Kiosco",usuario);
		HistorialConsultas historial = miGestor.listadoHistorialConsultas();
		Reportes reporte = new Reportes(historial) ;
		reporte.imprimirReportePorFecha();
		Assert.assertTrue("Existen kioscos", resultado.getPuntos().size() > 0);	
	}
	
	@Test
	public void BusquedaParadaColectivo() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("114",usuario);
		HistorialConsultas historial = miGestor.listadoHistorialConsultas();
		Reportes reporte = new Reportes(historial) ;
		reporte.imprimirReportePorFecha();
		Assert.assertTrue("Encontro la parada 114", resultado.getPuntos().size() > 0);		
	}
	
	///Busco un local comercial de tipo libreria.
	@Test
	public void BuscarLocalLibreria() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Libreria",usuario);
		HistorialConsultas historial = miGestor.listadoHistorialConsultas();
		Reportes reporte = new Reportes(historial) ;
		reporte.imprimirReportePorFecha();
		Assert.assertTrue("Existe al menos un poi de tipo libreria", resultado.getPuntos().size() > 0);	
	}
	
//	@Test
//	public void BuscarPorPalabraClave() throws Exception {
//		ResultadoConsulta resultado = miGestor.BuscarPoi("Descuentos",usuario);
//		
//		Assert.assertTrue("Existe poi con la palabra clave ingresada", resultado.getPuntos().size() > 0);	
//	}
	
}
