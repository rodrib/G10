package utn.dds.g10.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexion {

	public static void main(String[] argv) throws SQLException {

		System.out.println("-------- Prueba de conexi�n JDBC a MySQL ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// El Jar Connector/J debe ser parte del build path
		} catch (ClassNotFoundException e) {
			System.out.println("No se puede cargar el driver JDBC");
			e.printStackTrace();
			return;
		}

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DDS", "root", "ddsistemas");
			// Par�metros: ("string de conexi�n a la base", "usuario", "contrase�a") autorizados para acceder a la base sys

		} catch (SQLException e) {
			System.out.println("Error de conexi�n, ver mensaje:");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("Conectado a la base de datos");

			System.out.println("Creando consulta...");
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM miTablaPrueba";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("Registros de la columna VARIABLE de la tabla SYS_CONFIG:");
			while (rs.next()) {
				System.out.println("\t" + rs.getNString("variable"));
			}

		} else {
			System.out.println("Fall� la conexi�n");
		}
	}
}