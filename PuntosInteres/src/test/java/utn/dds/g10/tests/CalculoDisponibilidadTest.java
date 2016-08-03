package utn.dds.g10.tests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	CGP cgp2;
	ServicioCGP servicioCGP1;
	ServicioCGP servicioCGP2;
	ServicioCGP servicioCGP3;
	ServicioCGP servicioCGP4;
	LocalComercial localComercial;
	Libreria libreria;
	Kiosco kiosco;
	List<ServicioCGP> listaServiciosCGP1;
	List<ServicioCGP> listaServiciosCGP2;
	
	
	private void InicializarTest() {		
		//Inicializo POI
		this.puntoUno = new POI();
		this.puntoDos = new POI();
		
		//Inicializo Gestor
		this.miGestor = new GestorPoi();
		
		//Inicializo CGP
		this.cgp1 = new CGP();
		this.cgp2 = new CGP();
		
		this.listaServiciosCGP1 = new ArrayList<ServicioCGP>();
		this.listaServiciosCGP2 = new ArrayList<ServicioCGP>();
		this.servicioCGP1 = new ServicioCGP();
		this.servicioCGP2 = new ServicioCGP();
		this.servicioCGP3 = new ServicioCGP();
		this.servicioCGP4 = new ServicioCGP();
		
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
		
		listaServiciosCGP1.add(servicioCGP1);
		listaServiciosCGP1.add(servicioCGP2);
		listaServiciosCGP1.add(servicioCGP3);
				
//		cgp1.setServicios(listaServiciosCGP1);
		
		puntoUno.setTipo(cgp1);
		
		String strDatewithTime = "2016-06-28T19:19:19";
		LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);		
		Assert.assertTrue("Es dia de semana, y es horario disponible e ingreso el nombre del servicio",miGestor.EstaDisponible(puntoUno, aLDT,"Rentas2"));
		}
	
	@Test //2b
	public void estaDisponibleUnCGPSinNombreServicio(){
	
		listaServiciosCGP2.add(servicioCGP3);
		listaServiciosCGP2.add(servicioCGP4);
		
//		cgp2.setServicios(listaServiciosCGP2);
		
		puntoDos.setTipo(cgp2);
		
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
