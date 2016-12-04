package utn.dds.g10.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.Buscador.ResultadoBusquedaParcialUsuario;
import utn.dds.g10.modelo.ConexionDB;
import utn.dds.g10.pruebaPersistencia.Department;

public class DaoRelacional implements Dao {
	static Session session;
	static Transaction transaccion;

	public static Transaction getTransaccion() {
		return transaccion;
	}

	public static void setTransaccion(Transaction tran) {
		transaccion = tran;
	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session sess) {
		session = sess;
	}

	public static void iniciar() {
		if (session == null) {
			setSession(ConexionDB.getSessionFactory().openSession());
		}
	}

	@Override
	public int crearEntidad(Object entidad) {
		Session session = ConexionDB.getSessionFactory().openSession();
		session.beginTransaction();
		Integer i = (Integer) session.save(entidad);
		System.out.println("Id de entidad creada:" + i);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	public static void mergeEntidad(Object entidad) {
		Session session = ConexionDB.getSessionFactory().openSession();
		session.beginTransaction();
		session.merge(entidad);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void modificarEntidad(Object entidadModificada) {
		Session asession = ConexionDB.getSessionFactory().openSession();
		asession.beginTransaction();
		asession.update(entidadModificada);
		asession.getTransaction().commit();
		asession.close();
	}

	public static Usuario obtenerEntidadPorId(int idUsuario,
			Class<Usuario> clase) {
		Session asession = ConexionDB.getSessionFactory().openSession();
		Usuario entidad = (Usuario) asession.get(clase, idUsuario);
		asession.close();
		return entidad;
	}
	
	public static Usuario obtenerUsuariosPorNombre(String nombre, Class<Usuario> clase) {
		Session asession = ConexionDB.getSessionFactory().openSession();
		//Usuario entidad = (Usuario) asession.get(clase, idUsuario);
		//asession.close();
		
		Criteria criteria = asession.createCriteria(Usuario.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		
		//Obtiene el primero
		List<Usuario> usuarios = criteria.list();
		
		if(usuarios.size() > 0)
		{
			return (Usuario)criteria.list().get(0);	
		}
		else	
		{
			return new Usuario();
		}
	}
	
	public static Department obtenerDeparmentPorId(Long idUsuario,
			Class<Department> clase) {
		Session asession = ConexionDB.getSessionFactory().openSession();
		Department entidad = (Department) asession.get(clase, idUsuario);
		asession.close();
		return entidad;
	}
	
	public static POI obtenerPoiPorId(Long id,
			Class<POI> clase) {
		Session asession = ConexionDB.getSessionFactory().openSession();
		POI entidad = (POI) asession.get(clase, id);
		asession.close();
		return entidad;
	}
	
	public static long crearEntidadIdLong(Object entidad) {
		Session session = ConexionDB.getSessionFactory().openSession();
		session.beginTransaction();
		Long n = (Long) session.save(entidad);
		System.out.println("Id de entidad creada:" + n);
		session.getTransaction().commit();
		session.close();
		return n;
	}

	// public static ResultadoConsulta obtenerEntidadPorId(int idUsuario,
	// Class<ResultadoConsulta> clase) {
	// Session asession = DAO.getSessionFactory().openSession();
	// Usuario entidad = (ResultadoConsulta) asession.get(clase, idUsuario);
	// asession.close();
	// return entidad;
	// }

	public static void eliminarPoi(POI entidadBorrada) {
		entidadBorrada.setEstadoAlta(false);
		Session asession = ConexionDB.getSessionFactory().openSession();
		asession.beginTransaction();
		asession.update(entidadBorrada);
		asession.getTransaction().commit();
		asession.close();
	}

	public static POI obtenerPOI(int id) {
		ArrayList<POI> lista = new ArrayList<POI>();
		lista = (ArrayList<POI>) DaoRelacional.obtenerPois();
		for (POI p : lista) {
			if (p.getId() == id && p.getEstadoAlta() != false)
				return p;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<POI> obtenerPois() {

		Session session = ConexionDB.getSessionFactory().openSession();
		session.beginTransaction();

		@SuppressWarnings("deprecation")
		List<POI> pois = (List<POI>) session.createCriteria(POI.class).list();

		for (POI p : pois) {
			Hibernate.initialize(p.getTipo());
			
			if (p.getTipo().tipoPOI().equalsIgnoreCase("CGP")){
				CGP cgp = (CGP)p.getTipo();
				Hibernate.initialize(cgp.getServicios());
				p.setTipo(cgp);
			}
		}
		session.close();
		return pois;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ResultadoBusquedaParcialUsuario> obtenerResultados() {

		Session session = ConexionDB.getSessionFactory().openSession();
		session.beginTransaction();

		@SuppressWarnings("deprecation")
		List<ResultadoBusquedaParcialUsuario> resultados = new ArrayList<ResultadoBusquedaParcialUsuario>();
		resultados = (List<ResultadoBusquedaParcialUsuario>) session.createCriteria(ResultadoBusquedaParcialUsuario.class).list();

		for (ResultadoBusquedaParcialUsuario r : resultados) {
			Hibernate.initialize(r.getResultados());
		}
		session.close();
		return resultados;
	}

	@SuppressWarnings("unchecked")
	public static List<SucursalBanco> obtenerBancos() {
		iniciar();

		@SuppressWarnings("deprecation")
		List<SucursalBanco> bancos = (List<SucursalBanco>) session
				.createCriteria(SucursalBanco.class).list();

		for (SucursalBanco b : bancos) {
			Hibernate.initialize(b.getServicios());
		}
		return bancos;
	}

	@SuppressWarnings("unchecked")
	public static List<ParadaColectivo> obtenerParadas() {
		iniciar();

		@SuppressWarnings("deprecation")
		List<ParadaColectivo> paradas = (List<ParadaColectivo>) session
				.createCriteria(ParadaColectivo.class).list();

		for (ParadaColectivo p : paradas) {
			Hibernate.initialize(p);
		}
		return paradas;
	}

	@SuppressWarnings("unchecked")
	public static List<LocalComercial> obtenerLocales() {
		iniciar();

		@SuppressWarnings("deprecation")
		List<LocalComercial> locales = (List<LocalComercial>) session
				.createCriteria(LocalComercial.class).list();

		for (LocalComercial l : locales) {
			Hibernate.initialize(l.getRubro());
		}
		return locales;
	}

	@SuppressWarnings("unchecked")
	public static List<CGP> obtenerCGPs() {
		iniciar();

		@SuppressWarnings("deprecation")
		List<CGP> cgps = (List<CGP>) session.createCriteria(CGP.class).list();

		for (CGP cgp : cgps) {
			Hibernate.initialize(cgp.getServicios());
		}
		return cgps;
	}

	@Override
	public void eliminarEntidad(Object entidadEliminar) throws Exception {
		try {
			Session asession = ConexionDB.getSessionFactory().openSession();
			asession.beginTransaction();
			asession.delete(entidadEliminar);
			asession.getTransaction().commit();
			asession.close();

		} catch (Exception e) {
			throw new Exception(
					"Ocurrió un error al intentar eliminar la entidad: "
							+ e.getMessage());
		}
	}
}