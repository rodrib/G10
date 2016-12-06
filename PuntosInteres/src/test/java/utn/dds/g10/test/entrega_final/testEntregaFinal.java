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
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.ServicioCGP;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.entidades.administracion.Rol;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;

public class testEntregaFinal {
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
		usuarioAlta = new Usuario();
		usuarioAlta.setNombre("admin");
		usuarioAlta.setPassword("admin");

		Rol rolUsuarioPersistible = new RolAdministrador();
		usuarioAlta.setRol(rolUsuarioPersistible);

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

	@Test
	public void cargaInicialDatosTest() {
		repositorio.crearEntidad(usuarioAlta);

		Coordenada coordenada = new Coordenada(1, 2);

		Locacion locacion = new Locacion();
		locacion.setCoordenada(coordenada);
		poiBanco.setLocacion(locacion);
		poiBanco.setNombre("Banco Rio");
		poiBanco.getPalabrasClaves().add("suc principal");
		SucursalBanco banco = new SucursalBanco();
		banco.getServicios().add("cheques");
		banco.setNombreGerente("Carlos");
		poiBanco.setTipo(banco);
		DaoRelacional.crearEntidadIdLong(banco);

		coordenada.setLocacion(locacion);
		DaoRelacional.crearEntidadIdLong(coordenada);
		DaoRelacional.crearEntidadIdLong(locacion);
		Long id = DaoRelacional.crearEntidadIdLong(poiBanco);

		poiBanco.setNombre("Banco Frances");
		SucursalBanco bancoFrances = new SucursalBanco();
		bancoFrances.getServicios().add("cheques");
		poiBanco.setTipo(bancoFrances);

		locaciontest.setEntreCalles("Entre calles");
		locaciontest.setPoi(poiBanco);
		poiBanco.setLocacion(locaciontest);

		coordenadatest.setLocacion(locaciontest);
		DaoRelacional.crearEntidadIdLong(coordenadatest);
		DaoRelacional.crearEntidadIdLong(locaciontest);
		DaoRelacional.crearEntidadIdLong(bancoFrances);
		DaoRelacional.crearEntidadIdLong(poiBanco);

		ParadaColectivo parada = new ParadaColectivo();
		POI poiParada = new POI();
		poiParada.setTipo(parada);
		poiParada.setNombre("Parada 10");
		DaoRelacional.crearEntidadIdLong(parada);
		DaoRelacional.crearEntidadIdLong(poiParada);

		Libreria libreria = new Libreria();
		LocalComercial local = new LocalComercial();
		POI poiLibreria = new POI();
		local.setRubro(libreria);
		poiLibreria.setTipo(local);
		poiLibreria.setNombre("Libreria Lapiz");
		DaoRelacional.crearEntidadIdLong(libreria);
		DaoRelacional.crearEntidadIdLong(local);
		DaoRelacional.crearEntidadIdLong(poiLibreria);

		CGP cgp = new CGP();
		cgp.setComuna("Comuna 9");
		cgp.setDirector("Ramon");
		cgp.setDomicilio("Rivadavia 4500");
		cgp.setZonas("Caballito");

		ServicioCGP servicio = new ServicioCGP();

		POI poiCGP = new POI();
		servicio.setNombre("ABL");
		servicio.setCgp(cgp);
		cgp.getServicios().add(servicio);

		poiCGP.setTipo(cgp);
		poiCGP.setNombre("CGP Comuna 9");

		DaoRelacional.crearEntidadIdLong(servicio);
		DaoRelacional.crearEntidadIdLong(poiCGP);
	}

}
