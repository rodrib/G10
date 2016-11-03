package utn.dds.g10.DAO;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.model.ProcesoListener;
import utn.dds.g10.modelo.DAO;


public class DaoBase {
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
			setSession(DAO.getSessionFactory().openSession());
		}
	}

	public static int crearEntidad(Object entidad) {
		Session session = DAO.getSessionFactory().openSession();
		session.beginTransaction();
		Integer i = (Integer) session.save(entidad);
		System.out.println("Id de entidad creada:" + i);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	public static void modificarEntidad(Object entidadModificada)
	{
		Session asession = DAO.getSessionFactory().openSession();
		asession .beginTransaction();
		asession .update(entidadModificada);
		asession .getTransaction().commit();
		asession .close();
	}

	public static Object obtenerEntidadPorId(int idUsuario, Class<Usuario> clase) {
		Session asession = DAO.getSessionFactory().openSession();
		Object entidad = (Usuario) asession.get(clase, idUsuario);
		asession.close();
		return entidad;
	}
	
	public static Object obtenerPOI(int id) {
		Session asession = DAO.getSessionFactory().openSession();
		Object entidad = (POI) asession.get(POI.class, id);
		asession.close();
		return entidad;
	}
	
	public static Object obtenerBanco(int id) {
		Session asession = DAO.getSessionFactory().openSession();
		Object entidad = (SucursalBanco) asession.get(SucursalBanco.class, id);
		
		asession.close();
		return entidad;
	}
	
	public ProcesoListener getProcesoListener() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return (ProcesoListener) getClass().getClassLoader().loadClass(getClass().getName()+"Listener").newInstance();
	}

	@SuppressWarnings("unchecked")
	public static List<POI> obtenerPois() {
		
		iniciar();
		
		
		@SuppressWarnings("deprecation")
		List<POI> pois = (List<POI>) session.createCriteria(POI.class).list();

	      //Si lo quito da pete ya que no lo carga en la sesion
	      for(POI p: pois){
	       Hibernate.initialize(p.getTipo());
	      }
	      return pois;
	     }

	@SuppressWarnings("unchecked")
	public static List<SucursalBanco> obtenerBancos() {
		iniciar();
		
		@SuppressWarnings("deprecation")
		List<SucursalBanco> bancos = (List<SucursalBanco>) session.createCriteria(SucursalBanco.class).list();

	      //Si lo quito da pete ya que no lo carga en la sesion
	      for(SucursalBanco b: bancos){
	       Hibernate.initialize(b.getServicios());
	      }
	      return bancos;
	     }
	
	
	public static void eliminarEntidad(Object entidadEliminar) throws Exception
	{
		try {
			Session asession = DAO.getSessionFactory().openSession();
			asession .beginTransaction();
			asession .delete(entidadEliminar);
			asession .getTransaction().commit();
			asession .close();
			
		} catch (Exception e) {
			throw new Exception("Ocurrió un error al intentar eliminar la entidad: " + e.getMessage());
		}
	}
}