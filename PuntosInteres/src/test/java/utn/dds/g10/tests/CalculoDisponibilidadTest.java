package utn.dds.g10.tests;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.Horarios;
import utn.dds.g10.entidades.Kiosco;
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.ServicioCGP;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.entidades.administracion.Usuario;
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
	ArrayList<ServicioCGP> listaServiciosCGP1 = new ArrayList<ServicioCGP>();
	ArrayList<ServicioCGP> listaServiciosCGP2 = new ArrayList<ServicioCGP>();
	
	
	private void InicializarTest() {		
		//Inicializo POI
		this.puntoUno = new POI();
		this.puntoDos = new POI();
		
		//Inicializo Gestor
		this.miGestor = new GestorPoi(new Usuario());
		
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
	
		Horarios horario = new Horarios();
		horario.setDiaSemana(1);
		horario.setHoraDesde(12);
		horario.setMinutosDesde(0);
		horario.setHoraHasta(20);
		horario.setMinutosHasta(0);
		ArrayList<Horarios> horarios = new ArrayList<Horarios>();
		horarios.add(horario);
		servicioCGP2.setHorarios(horarios);
		servicioCGP2.setNombre("Rentas2");
		listaServiciosCGP1.add(servicioCGP2);
		cgp1.setServicios(listaServiciosCGP1);
				
//		cgp1.setServicios(listaServiciosCGP1);
		
		puntoUno.setTipo(cgp1);
		
		LocalTime hora = LocalTime.of(17,0,0);
		LocalDate dia = LocalDate.of(2016, 8, 1);
		LocalDateTime aLDT = LocalDateTime.of(dia, hora);	
		Assert.assertTrue("Es dia de semana, y es horario disponible e ingreso el nombre del servicio",miGestor.EstaDisponible(puntoUno, aLDT,"Rentas2"));
		}
	
	@Test //2b
	public void estaDisponibleUnCGPSinNombreServicio(){
		Horarios horario = new Horarios();
		horario.setDiaSemana(1);
		horario.setHoraDesde(12);
		horario.setMinutosDesde(0);
		horario.setHoraHasta(20);
		horario.setMinutosHasta(0);
		ArrayList<Horarios> horarios = new ArrayList<Horarios>();
		horarios.add(horario);
		servicioCGP3.setHorarios(horarios);
		listaServiciosCGP2.add(servicioCGP3);
		cgp2.setServicios(listaServiciosCGP2);
		
//		cgp2.setServicios(listaServiciosCGP2);
		
		puntoDos.setTipo(cgp2);
		
		LocalTime hora = LocalTime.of(17,0,0);
		LocalDate dia = LocalDate.of(2016, 8, 1);
		LocalDateTime aLDT = LocalDateTime.of(dia, hora);		
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
