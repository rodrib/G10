package utn.dds.g10.tests;

import java.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.Kiosco;
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.ServicioCGP;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.gestores.GestorPoi;

public class CalculoDisponibilidadTest {
	
	GestorPoi miGestor;
	POI puntoUno;
	POI puntoDos;
	CGP cgp1;
	ServicioCGP servicioCGP1;
	ServicioCGP servicioCGP2;
	ServicioCGP servicioCGP3;
	LocalComercial localComercial;
	Libreria libreria;
	Kiosco kiosco;
	
	
	private void InicializarTest() {		
		//Inicializo POI
		this.puntoUno = new POI();
		this.puntoDos = new POI();
		
		//Inicializo Gestor
		this.miGestor = new GestorPoi();
		
		//Inicializo CGP
		this.cgp1 = new CGP();
		this.servicioCGP1 = new ServicioCGP();
		this.servicioCGP2 = new ServicioCGP();
		this.servicioCGP3 = new ServicioCGP();
		
		//Inicializo Locales Comerciales
		this.localComercial= new LocalComercial();
		this.libreria = new Libreria();
		this.kiosco = new Kiosco();
		
	}
	
	
	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}

	
	@Test //1
	public void disponibilidadColectivo (){		
		puntoUno.setTipo(new ParadaColectivo());
		String strDatewithTime = "2016-08-04T10:11:30";
		LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);		
		Assert.assertTrue("El colectivo siempre esta disponible",miGestor.EstaDisponible(puntoUno, aLDT,""));		
	}
	
	@Test //2a
	public void estaDisponibleUnCGPConNombreServicio(){
	
		servicioCGP1.setNombre("Rentas1");
		servicioCGP2.setNombre("Rentas2");
		servicioCGP3.setNombre("Rentas3");
		
		cgp1.getServicios().add(servicioCGP1);
		cgp1.getServicios().add(servicioCGP2);
		cgp1.getServicios().add(servicioCGP3);
		
		puntoUno.setTipo(cgp1);
		
		String strDatewithTime = "2016-06-28T19:19:19";
		LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);		
		Assert.assertTrue("Es dia de semana, y es horario disponible e ingreso el nombre del servicio",miGestor.EstaDisponible(puntoUno, aLDT,"Rentas2"));
		}
	
	@Test //2b
	public void estaDisponibleUnCGPSinNombreServicio(){
	
		cgp1.getServicios().add(servicioCGP1);
		cgp1.getServicios().add(servicioCGP2);
		
		puntoDos.setTipo(cgp1);
		
		String strDatewithTime = "2016-06-28T11:11:11";
		LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);		
		Assert.assertTrue("Es dia de semana, y es horario disponible y no ingreso nombre servicio",miGestor.EstaDisponible(puntoDos, aLDT,""));
	}
	
	@Test //3
	public void disponibilidadBanco (){
		
		puntoDos.setTipo(new SucursalBanco());
		
		String strDatewithTime = "2016-06-28T11:11:11";
		LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);	
		Assert.assertTrue("Es dia de semana, y es horario disponible",miGestor.EstaDisponible(puntoDos, aLDT,""));
	}
	
	@Test //4a
	public void disponibilidadKiosco (){
		
		localComercial.setRubro(kiosco);
		puntoUno.setTipo(localComercial);
		
		String strDatewithTime = "2016-06-30T20:10:00";
		LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);	
		Assert.assertTrue("Es dia de semana, y es horario disponible",miGestor.EstaDisponible(puntoUno, aLDT,""));
	}
	
	@Test //4b
	public void disponibilidadLibreria(){
		
		localComercial.setRubro(libreria);
		puntoDos.setTipo(localComercial);
		
		String strDatewithTime = "2016-08-03T19:15:00";
		LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);	
		Assert.assertTrue("Es dia de semana, y es horario disponible",miGestor.EstaDisponible(puntoDos, aLDT,""));
	}
}
