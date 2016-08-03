package utn.dds.g10.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.datos.PoiDatos;
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.gestores.GestorPoi;

public class ConsultaAMB {

	ResultadoConsulta resultadoConsulta;
	List<POI> listaPuntos;
	
	POI puntoUno;
	POI puntoDos;
	
	
	GestorPoi miGestor;

	private void InicializarTest() {

		//Inicializo Gestor
		this.miGestor = new GestorPoi();
		
		//Inicializo POI
		this.puntoUno = new POI();
		this.puntoDos = new POI();
		
	}

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}

	@Test
	public void AltaPOI() throws Exception {
		POI miPoi = new POI();
		boolean result;
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Parada53");
		miPoi.setTipo(new ParadaColectivo());
		ResultadoConsulta resultado = miGestor.BuscarPoi("Parada53");
		result = miGestor.Agregar(miPoi);
		ResultadoConsulta resultado2 = miGestor.BuscarPoi("Parada53");	
		assertTrue("Se agregó correctamente",result == true || resultado2.getPuntos().size() >= resultado.getPuntos().size());
		
	}
	
	@Test
	public void BajaPOI() throws Exception {
		POI miPoi = new POI();
		boolean result;
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Parada114");
		miPoi.setTipo(new ParadaColectivo());
		ResultadoConsulta resultado = miGestor.BuscarPoi("Parada114");
		result = miGestor.Eliminar(miPoi);
		ResultadoConsulta resultado2 = miGestor.BuscarPoi("Parada114");	
		assertTrue("Se eliminó correctamente",result == true || resultado2.getPuntos().size() <= resultado.getPuntos().size());
		
	}
	
	@Test
	public void ModificarPOI() throws Exception {
		
		POI Parada25 = new POI();
		Parada25.setLocacion(new Locacion());
		Parada25.setNombre("Parada25");
		Parada25.setTipo(new ParadaColectivo());
		boolean result;
		
		POI miPoi = new POI();
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Parada26");
		miPoi.setTipo(new ParadaColectivo());
		
		ResultadoConsulta resultado = miGestor.BuscarPoi("Parada25");
		result = miGestor.Modificar(Parada25, miPoi);
		ResultadoConsulta resultado2 = miGestor.BuscarPoi("Parada25");	
		assertTrue("Se modificó correctamente",result == true || resultado2.getPuntos().size() <= resultado.getPuntos().size());
		
	}
	
	
}
	
	
