package utn.dds.g10.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.Reportes;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.entidades.administracion.RolTerminal;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.GestorPoi;

public class ReportesTest {

	ResultadoConsulta resultadoConsulta;
	List<POI> listaPuntos;
	
	POI puntoUno;
	POI puntoDos;
	String usuario;
	
	Usuario usuarioLogueado;
	
	GestorPoi miGestor;

	private void InicializarTest() {

		usuarioLogueado  = new Usuario();

		RolTerminal terminal = new RolTerminal();
		usuarioLogueado.setRol(terminal);
		
		//Inicializo Gestor
		this.miGestor = new GestorPoi(usuarioLogueado);
		
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
	public void ImprimirReportes() throws Exception {
		
		miGestor.BuscarPoi("Banco Frances",usuario);
		miGestor.BuscarPoi("Kiosco",usuario);
		miGestor.BuscarPoi("114",usuario);
		
		Reportes reportes = new Reportes();	
		
		reportes.setHistoConUsuario(miGestor.getHistorialUsuario());
		reportes.imprimirReporteParcial();
		
		reportes.setHistoConUsuario(miGestor.getHistorialUsuario());
		reportes.imprimirReporteTotal();
		
		reportes.setHistoConFecha(miGestor.getHistorialFecha());
		reportes.imprimirReporteCantBusquedas();		
				
		assertTrue("Existen Reportes Por Fecha", miGestor.getHistorialFecha().getConsultas()!=null );
		assertTrue("Existen Reportes Parcial", miGestor.getHistorialUsuario().getConsultasParcial()!=null );
		assertTrue("Existen Reportes Total", miGestor.getHistorialUsuario().getConsultasTotal()!=null );
	}
	
}
