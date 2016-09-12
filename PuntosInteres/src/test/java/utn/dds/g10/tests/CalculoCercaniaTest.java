package utn.dds.g10.tests;

import org.junit.Assert;

import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.Coordenada;
import utn.dds.g10.entidades.Kiosco;
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.GestorPoi;

import org.junit.Before;
import org.junit.Test;

public class CalculoCercaniaTest {

	Locacion locacionA;
	Locacion locacionB;
	GestorPoi miGestor;
	POI puntoUno;
	POI puntoDos;
	LocalComercial localComercial;
	Libreria libreria;
	Kiosco kiosco;
	
	private void InicializarTest() {
		
		//Inicializo locacion personaA
		this.locacionA = new Locacion();
		//Inicializo locacion personaB
		this.locacionB = new Locacion();
		
		//Inicializo locales comerciales
		this.libreria = new Libreria();
		this.kiosco = new Kiosco();
		this.localComercial = new LocalComercial();
		
		//Inicializo Gestor
		this.miGestor = new GestorPoi(new Usuario());
		
		//Inicializo los POI
		this.puntoUno = new POI();
		this.puntoDos = new POI();
		
	}

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}

	@Test
	public void poiMenorACincoCuadras() throws Exception {
		
		//455 metros entre locacion A y B
		locacionA.setCoordenada(new Coordenada((float) -34.626533, (float) -58.441344));
		locacionB.setCoordenada(new Coordenada((float) -34.627963, (float) -58.436710));
				
		puntoUno.setNombre("ICBC");
		puntoUno.setTipo(new SucursalBanco());
		puntoUno.setLocacion(locacionA);
		
		Assert.assertTrue("Estamos cerca cuando la distancia es menor a 5 cuadras salvo excepciones",miGestor.EstaCerca(puntoUno,locacionB));
	}

	@Test
	public void poiColectivoCerca() throws Exception {
		
		//92 metros entre locacion A y B
		locacionA.setCoordenada(new Coordenada((float) -34.593605, (float) -58.412660));
		locacionB.setCoordenada(new Coordenada((float) -34.592907, (float) -58.412123));

		puntoUno.setNombre("Linea 60");
		puntoUno.setTipo(new ParadaColectivo());
		puntoUno.setLocacion(locacionA);	
		
		Assert.assertTrue("La parada de colectivo esta a menos de 1 cuadra",miGestor.EstaCerca(puntoUno, locacionB));		
	}
	
	@Test
	public void poiCGPCerca() throws Exception {		
		
		locacionA.setCodigoComuna(5);
		locacionB.setCodigoComuna(5);
		
		puntoUno.setNombre("CGP1");	
		puntoUno.setTipo(new CGP());
		puntoUno.setLocacion(locacionA);		
		
		Assert.assertTrue("La coordenada esta dentro de la zona delimitada por la comuna",miGestor.EstaCerca(puntoUno, locacionB));		
	}
	
	
	@Test
	public void poiComercioLibreria() throws Exception{
		
		//400 metros entre locacion A y B
		locacionA.setCoordenada(new Coordenada((float) -34.589468, (float) -58.406040));
		locacionB.setCoordenada(new Coordenada((float) -34.587101, (float) -58.402810));	
		
		localComercial.setRubro(libreria);
		
		puntoUno.setNombre("Libreria El Ateneo");	
		puntoUno.setTipo(localComercial);
		puntoUno.setLocacion(locacionA);				
		
		Assert.assertTrue("La libreria esta dentro de un radio de 5 cuadras", miGestor.EstaCerca(puntoUno, locacionB));		
		
	}
	
	@Test
	public void poiComercioKiosko() throws Exception{
		 
		//150 metros entre locacion A y B
		locacionA.setCoordenada(new Coordenada((float) -34.627310, (float) -58.446237));
		locacionB.setCoordenada(new Coordenada((float) -34.627045, (float) -58.444649));	
		//miGestor.CalcularDistanciaEntrePuntos(puntoUno, puntoDos)
		
		//Seteo los propiedades del Kiosco
		localComercial.setRubro(kiosco);
		puntoDos.setNombre("King Kios Kong");	
		puntoDos.setTipo(localComercial);
		//La locacionA es la locacion del Kiosco.
		puntoDos.setLocacion(locacionA);		
	
		Assert.assertTrue("El kiosko esta dentro de un radio de 2 cuadras", miGestor.EstaCerca(puntoDos, locacionB));
	}
}
