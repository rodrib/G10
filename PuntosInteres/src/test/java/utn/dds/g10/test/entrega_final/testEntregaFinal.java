package utn.dds.g10.test.entrega_final;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.DAO.Dao;
import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.Coordenada;
import utn.dds.g10.entidades.Kiosco;
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.RubroLocal;
import utn.dds.g10.entidades.ServicioCGP;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.entidades.TipoPoi;
import utn.dds.g10.entidades.administracion.Rol;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.RolTerminal;
import utn.dds.g10.entidades.administracion.Usuario;

public class testEntregaFinal {

	int ROL_ADMIN = 1;
	int ROL_TERMINAL = 2;
	
	long RUBRO_LIBRERIA = 1;
	long RUBRO_KIOSCO = 2;

	long LOCAL_COMERCIAL_LIBRERIA = 1;
	long LOCAL_COMERCIAL_PELUQUERIA= 2;
	long LOCAL_COMERCIAL_RESTO = 3;
	long LOCAL_COMERCIAL_FERRETERIA = 4;
	
	Usuario usuarioAlta;
	Dao repositorio;
	POI poiBanco;
	POI poiCGP;
	Locacion locaciontest;
	Coordenada coordenadatest;

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
		repositorio = new DaoRelacional();
	}

	private void InicializarTest() {

		poiBanco = new POI();
		poiCGP = new POI();
		CGP cgp = new CGP();
		ServicioCGP servicioCGP = new ServicioCGP();
		servicioCGP.setNombre("deudas");
		poiCGP.setNombre("Comuna 45");
		poiCGP.setTipo(cgp);
		servicioCGP.setCgp(cgp);
		cgp.getServicios().add(servicioCGP);

		locaciontest = new Locacion();
		locaciontest.setBarrio("caballito");
		locaciontest.setCallePrincipal("calle principal");
		locaciontest.setCodigoComuna(34);
		locaciontest.setCodigoPostal(1832);

		coordenadatest = new Coordenada(2, 34);

		locaciontest.setCoordenada(coordenadatest);
	}

	private void crearRoles() {
		RolAdministrador rolAdmin = new RolAdministrador();
		RolTerminal rolTerminal = new RolTerminal();

		rolAdmin.setNombre("Administrador");
		rolTerminal.setNombre("Terminal Medrano");

		DaoRelacional daoRel = new DaoRelacional();
		daoRel.crearEntidad(rolAdmin);
		daoRel.crearEntidad(rolTerminal);
	}

	private void crearUsuarios() {
		RolAdministrador rolAdmin = new RolAdministrador();
		RolTerminal rolTerminal = new RolTerminal();

		rolAdmin.setIdRol(ROL_ADMIN);
		rolTerminal.setIdRol(ROL_TERMINAL);

		Usuario usuarioAdmin = new Usuario();
		Usuario usuarioTerminal = new Usuario();

		usuarioAdmin.setRol(rolAdmin);
		usuarioTerminal.setRol(rolTerminal);

		usuarioAdmin.setNombre("admin");
		usuarioAdmin.setPassword("admin");

		usuarioTerminal.setNombre("user");
		usuarioTerminal.setPassword("user");

		DaoRelacional daoRel = new DaoRelacional();
		daoRel.crearEntidad(usuarioAdmin);
		daoRel.crearEntidad(usuarioTerminal);
	}

	@Test
	public void cargaInicialDatosTest() {
		crearRoles();
		crearUsuarios();
		crearPOIS();
	}

	private void crearPOIS() {
		
		Libreria libreriaRubro = new Libreria();
		DaoRelacional.crearEntidadIdLong(libreriaRubro);
		
		Libreria nuevaLib = new Libreria();
		nuevaLib.setId(RUBRO_LIBRERIA);

		LocalComercial localLibreria = new LocalComercial();
		localLibreria.setRubro(nuevaLib);
		LOCAL_COMERCIAL_LIBRERIA = DaoRelacional.crearEntidadIdLong(localLibreria);

		LocalComercial localPeluqueria = new LocalComercial();
		localPeluqueria.setRubro(nuevaLib); // ojo libreria
		LOCAL_COMERCIAL_PELUQUERIA = DaoRelacional.crearEntidadIdLong(localPeluqueria); 

		LocalComercial localRetaurante = new LocalComercial();
		localRetaurante.setRubro(nuevaLib); // ojo libreria
		LOCAL_COMERCIAL_RESTO = DaoRelacional.crearEntidadIdLong(localRetaurante); 

		LocalComercial localFerreteria = new LocalComercial();
		localFerreteria.setRubro(nuevaLib); // ojo libreria
		LOCAL_COMERCIAL_FERRETERIA = DaoRelacional.crearEntidadIdLong(localFerreteria);
		
		POI LibreriaTijeras = new POI();
		POI PeluqueriaTijeras = new POI();
		POI RestoPapaFrita = new POI();
		POI FerreteriaMiPapa = new POI();
		POI RestoSantander = new POI();

		LibreriaTijeras.setNombre("Libreria Tijeras");
		PeluqueriaTijeras.setNombre("Peluqueria Tijeras");
		RestoPapaFrita.setNombre("Restaurante Papa frita");
		FerreteriaMiPapa.setNombre("Ferreteria Mi Papa");
		RestoSantander.setNombre("Restaurante Santander");
		
		LibreriaTijeras.setTipo(localLibreria);
		PeluqueriaTijeras.setTipo(localPeluqueria);
		RestoPapaFrita.setTipo(localRetaurante);
		FerreteriaMiPapa.setTipo(localFerreteria);
		RestoSantander.setTipo(localRetaurante);

		DaoRelacional.crearEntidadIdLong(LibreriaTijeras);
		DaoRelacional.crearEntidadIdLong(PeluqueriaTijeras);
		DaoRelacional.crearEntidadIdLong(RestoPapaFrita);
		DaoRelacional.crearEntidadIdLong(FerreteriaMiPapa);
		DaoRelacional.crearEntidadIdLong(RestoSantander);
	}

}
