package utn.dds.g10.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.modelo.DAO;

public class DaoBase {
	static Session session;
	static Transaction transaccion;

	public Transaction getTransaccion() {
		return transaccion;
	}

	public static void setTransaccion(Transaction tran) {
		transaccion = tran;
	}

	public Session getSession() {
		return session;
	}

	public static void setSession(Session sess) {
		session = sess;
	}

	public static void iniciar() {
		setSession(DAO.getSessionFactory().openSession());
		setTransaccion(session.beginTransaction());
	}

	public static void confirmarYTerminar() {
		transaccion.commit();
		session.disconnect();
		DAO.shutdown();
	}

	public static int crearEntidad(Object entidad) {
		iniciar();
		Integer id = (Integer) session.save(entidad);
		confirmarYTerminar();
		return id;
	}
}