package utn.dds.g10.tests;

import static org.junit.Assert.*;
import utn.dds.g10.entidades.Coordenada;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.gestores.GestorPoi;

import org.junit.Before;
import org.junit.Test;

public class PoiTest {

	Locacion locacionBanco;
	GestorPoi miGestor;
	POI puntoUno;
	POI puntoDos;

	private void InicializarTest() {
		
		//Inicilizo locacion
		this.locacionBanco = new Locacion();
		locacionBanco.setBarrio("Caballito");
		locacionBanco.setCallePrincipal("Acoyte");
		locacionBanco.setCodigoComuna(5);
		locacionBanco.setCodigoPostal(1234);
		locacionBanco.setCoordenada(new Coordenada((float) 10.25, (float) 34.4));
		locacionBanco.setDepartamento('A');
		locacionBanco.setDireccion("Acoyte 2434");
		locacionBanco.setEntreCalles("Rivadavia y Boyacá");
		locacionBanco.setNumero(2434);
		locacionBanco.setPais("Argentina");
		locacionBanco.setPiso(1);
		locacionBanco.setProvincia("Buenos Aires");

		//Inicializo Gestor
		this.miGestor = new GestorPoi();
		
		//Inicializo los POI
		this.puntoUno = new POI();
		this.puntoDos = new POI();
	}

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}

	@Test
	public void DistanciaEntreMismoPuntoTest() throws Exception {

		puntoUno.setNombre("Banco");
		puntoUno.setTipo(new SucursalBanco());
		puntoUno.setLocacion(locacionBanco);

		float distancia = -1;
		distancia = miGestor.CalcularDistanciaEntrePuntos(puntoUno, puntoUno);
		assertTrue("La distacia entre puntos iguales es cero", distancia == 0);
	}

}
