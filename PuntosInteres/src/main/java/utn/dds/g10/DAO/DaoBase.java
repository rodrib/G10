package utn.dds.g10.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import utn.dds.g10.entidades.administracion.Usuario;
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