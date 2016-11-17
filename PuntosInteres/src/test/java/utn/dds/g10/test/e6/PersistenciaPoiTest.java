package utn.dds.g10.test.e6;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.DAO.Dao;
import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.DAO.DepartamentDao;
import utn.dds.g10.DAO.PoiDao;
import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.Coordenada;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.POI;
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
	
	
//	Obtener   un   POI,   modificar   sus   coordenadas   geográficas,   persistirlo,   recuperarlo   y  
//	verificar que las coordenadas sean las ingresadas en la última modificación. 
	@Test
	public void modificarPoiTest()
	{
		
		Coordenada coordenada = new Coordenada(1, 2);
		
		Locacion locacion = new Locacion();
		locacion.setCoordenada(coordenada);
		poiBanco.setLocacion(locacion);
		poiBanco.setNombre("Banco GGG");
		SucursalBanco banco = new SucursalBanco();
		banco.getServicios().add("cheques");
		poiBanco.setTipo(banco);
		DaoRelacional.crearEntidadIdLong(banco);
		
		Long id = DaoRelacional.crearEntidadIdLong(poiBanco);
		
		DaoRelacional.crearEntidadIdLong(locacion);
		
		coordenada.setLocacion(locacion);
				
		DaoRelacional.crearEntidadIdLong(coordenada);
		
			
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
		
//		banco.getServicios().add("plazo fijo");
		Coordenada coordenadaModificada = new Coordenada(2, 3);
		Locacion locacionModificada = new Locacion();
		locacion.setCoordenada(coordenadaModificada);
		poiObtenido.setLocacion(locacionModificada);
//		poiObtenido.setTipo(banco);
		repositorio.modificarEntidad(poiObtenido);
		
		POI poiModificado = new POI();
		poiModificado = (POI) PoiDao.obtenerPoiPorId(id, POI.class);
		
		System.out.println("Latitud Mod"+poiModificado.getLocacion().getCoordenada().getLatitud()+" Longitud Mod"+poiModificado.getLocacion().getCoordenada().getLongitud());
		
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
//	recuperación, la respuesta deberá ser que no existe (null). 
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
		Long id = DaoRelacional.crearEntidadIdLong(poiBanco);	
	
		
		POI poiObtenido = new POI();
		poiObtenido = (POI) PoiDao.obtenerPoiPorId(id, POI.class);
		DaoRelacional.eliminarPoi(poiObtenido);
		
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