package utn.dds.g10.modelo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexionDB {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			
			Configuration config = new Configuration().addResource("hibernate.cfg.xml").configure();
			SessionFactory sess = config.buildSessionFactory();
			return sess;
			
		} catch (Exception ex) {
			return null; // NO se pudo conectar a la base.
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
