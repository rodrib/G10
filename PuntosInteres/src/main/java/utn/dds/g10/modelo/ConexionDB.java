package utn.dds.g10.modelo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexionDB {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().addResource("hibernate.cfg.xml").configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Error al intentarse conectar a la base de datos: " + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
