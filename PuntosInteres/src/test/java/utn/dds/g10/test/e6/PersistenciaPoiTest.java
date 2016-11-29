package utn.dds.g10.test.e6;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.DAO.Dao;
import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.DAO.DepartamentDao;
import utn.dds.g10.DAO.PoiDao;
import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.Coordenada;
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.ServicioCGP;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.entidades.TipoPoi;
import utn.dds.g10.pruebaPersistencia.Department;

public class PersistenciaPoiTest {
	
	POI poiBanco;
	POI poiCGP;
	Dao repositorio;
	
	Locacion locaciontest;
	Coordenada coordenadatest ;

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
	
	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
		repositorio = new DaoRelacional();
	}
	
	
//	Obtener   un   POI,   modificar   sus   coordenadas   geogr�ficas,   persistirlo,   recuperarlo   y  
//	verificar que las coordenadas sean las ingresadas en la �ltima modificaci�n. 
	@Test
	public void modificarPoiTest()
	{
		
		Coordenada coordenada = new Coordenada(1, 2);
		
		Locacion locacion = new Locacion();
		locacion.setCoordenada(coordenada);
		poiBanco.setLocacion(locacion);
		poiBanco.setNombre("Banco GGG");
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
			
		POI poiObtenido = new POI();
		poiObtenido = (POI) PoiDao.obtenerPoiPorId(id, POI.class);
		
//		TipoPoi tipo = new SucursalBanco();
//		tipo = poiObtenido.getTipo();
//
//		SucursalBanco banco = new SucursalBanco();
//		banco = (SucursalBanco) tipo.obtenerPOI(tipo.getIdTipoPoi());
//		
//		System.out.println("Servicio 1 "+banco.getServicios().get(0));
		System.out.println("Latitud Mod"+poiObtenido.getLocacion().getCoordenada().getLatitud()+" Longitud Mod"+poiObtenido.getLocacion().getCoordenada().getLongitud());

		Coordenada coordenadaModificada = new Coordenada(2, 3);
		locacion.setCoordenada(coordenadaModificada);
		poiObtenido.setLocacion(locacion);
//		banco.getServicios().add("plazo fijo");
		
//		poiObtenido.setTipo(banco);
		
		DaoRelacional.crearEntidadIdLong(coordenadaModificada);
		repositorio.modificarEntidad(locacion);
		repositorio.modificarEntidad(poiObtenido);
		
		POI poiModificado = new POI();
		poiModificado = (POI) PoiDao.obtenerPoiPorId(id, POI.class);
		SucursalBanco bancoPoi = (SucursalBanco)poiModificado.getTipo();
		System.out.println("Latitud Mod"+poiModificado.getLocacion().getCoordenada().getLatitud()+" Longitud Mod"+poiModificado.getLocacion().getCoordenada().getLongitud());
		System.out.println("Clave "+poiModificado.getPalabrasClaves().get(0));
		System.out.println("Gerente "+bancoPoi.getNombreGerente());
//		TipoPoi tipoModificado = new SucursalBanco();
//		tipoModificado = (TipoPoi) poiModificado.getTipo();
//
//		TipoPoi bancoModificado = new SucursalBanco();
//		bancoModificado = tipoModificado.obtenerPOI(tipoModificado.getIdTipoPoi());
//		
//		System.out.println("Servicio 2 "+((SucursalBanco) bancoModificado).getServicios().get(1));
		
//		DaoBase.iniciar();
//		int idCGP = DaoBase.crearEntidad(poiCGP);
//		POI poiCGPObtenido = new POI();
//		poiCGPObtenido = DaoBase.obtenerPOI(idCGP);
//		
//		TipoPoi tipoCGP = new CGP();
//		tipoCGP = poiObtenido.getTipo();
//
//		CGP cgp = new CGP();
//		cgp = (CGP) tipoCGP.obtenerPOI(tipoCGP.getIdTipoPoi());
//		
//		System.out.println("Nombre CGP "+poiCGPObtenido.getNombre());
//		System.out.println("Servicio CGP "+cgp.getServicios().get(0).getNombre());
		
	}
	
//	Crear   un   nuevo   PoI,   persistirlo,   recuperarlo,   eliminarlo   y   al   solicitar   nuevamente   su  
//	recuperaci�n, la respuesta deber� ser que no existe (null). 
	// Crea poi con id, con un id de tipo poi -> id
	@Test
	public void crearEliminarPoiTest() throws Exception
	{
		poiBanco.setNombre("Banco GGG");
		SucursalBanco banco = new SucursalBanco();
		banco.getServicios().add("cheques");
		poiBanco.setTipo(banco);
		
		locaciontest.setEntreCalles("Entre calles");
		
		locaciontest.setPoi(poiBanco);
		poiBanco.setLocacion(locaciontest);
		
		coordenadatest.setLocacion(locaciontest);
		
		DaoRelacional.crearEntidadIdLong(coordenadatest);	
		DaoRelacional.crearEntidadIdLong(locaciontest);	
		DaoRelacional.crearEntidadIdLong(banco);		
		Long id = DaoRelacional.crearEntidadIdLong(poiBanco);	
	
		
		POI poiObtenido = new POI();
		poiObtenido = (POI) PoiDao.obtenerPoiPorId(id, POI.class);
		DaoRelacional.eliminarPoi(poiObtenido);


	}
	
	@Test
	public void crearParadaTest() throws Exception
	{
		ParadaColectivo parada = new ParadaColectivo();
		POI poiParada = new POI();
		poiParada.setTipo(parada);
		poiParada.setNombre("Parada 103");
		DaoRelacional.crearEntidadIdLong(parada);	
		Long id = DaoRelacional.crearEntidadIdLong(poiParada);	
	
		POI poiObtenido = new POI();
		poiObtenido = (POI) PoiDao.obtenerPoiPorId(id, POI.class);
		System.out.println("Nombre Parada: "+poiObtenido.getNombre());
		
	}
	
	@Test
	public void crearLibreriaTest() throws Exception
	{
		Libreria libreria = new Libreria();
		LocalComercial local = new LocalComercial();
		POI poiLibreria = new POI();
		local.setRubro(libreria);
		poiLibreria.setTipo(local);
		poiLibreria.setNombre("Libreria Colores");
		DaoRelacional.crearEntidadIdLong(libreria);	
		DaoRelacional.crearEntidadIdLong(local);	
		Long id = DaoRelacional.crearEntidadIdLong(poiLibreria);	
	
		POI poiObtenido = new POI();
		poiObtenido = (POI) PoiDao.obtenerPoiPorId(id, POI.class);
		System.out.println("Nombre Libreria: "+poiObtenido.getNombre());
		
	}
	
	@Test
	public void crearCGPTest() throws Exception
	{
		CGP cgp = new CGP();
		ServicioCGP servicio = new ServicioCGP();
		POI poiCGP = new POI();
		servicio.setNombre("ABL");
		servicio.setCgp(cgp);
		cgp.getServicios().add(servicio);
		poiCGP.setTipo(cgp);
		poiCGP.setNombre("CGP Comuna 7");
		DaoRelacional.crearEntidadIdLong(servicio);	
		DaoRelacional.crearEntidadIdLong(cgp);	
		Long id = DaoRelacional.crearEntidadIdLong(poiCGP);	
	
		POI poiObtenido = new POI();
		poiObtenido = (POI) PoiDao.obtenerPoiPorId(id, POI.class);
		System.out.println("Nombre CGP: "+poiObtenido.getNombre());
		
	}
	
	@Test
	public void crearModificarCoordenada() throws Exception
	{
		
		Coordenada coodenadaNueva = new Coordenada(55,77);
		
		poiBanco.setNombre("Banco Nacion");
		SucursalBanco banco = new SucursalBanco();
		poiBanco.setTipo(banco);
		
		locaciontest.setEntreCalles("Entre calles");
		locaciontest.setPoi(poiBanco);
		
		poiBanco.setLocacion(locaciontest);
		coordenadatest.setLocacion(locaciontest);
		
		DaoRelacional.crearEntidadIdLong(coordenadatest);
		DaoRelacional.crearEntidadIdLong(coodenadaNueva);
		
		DaoRelacional.crearEntidadIdLong(locaciontest);
		Long id = DaoRelacional.crearEntidadIdLong(poiBanco);	
	
		POI poiObtenido = new POI();
		poiObtenido = (POI)PoiDao.obtenerPoiPorId(id, POI.class);
		
		coodenadaNueva.setLocacion(poiObtenido.getLocacion());
		poiObtenido.getLocacion().setCoordenada(coodenadaNueva);
		
		repositorio.modificarEntidad(poiObtenido.getLocacion());
		
		//DaoRelacional.eliminarPoi(poiObtenido);
	}


}