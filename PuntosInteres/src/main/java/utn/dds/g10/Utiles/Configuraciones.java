package utn.dds.g10.Utiles;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.fasterxml.jackson.databind.util.Converter;

public class Configuraciones {
	
	private  static String  nombreConfig = "web.config";
	private static String claveMailAdmin = "mailadmin";
	private static String claveUrlBancos = "bancos";
	private static String claveUrlCGP = "bancos";
	
	public static String obtenerMailAdministrador() {
		return obtenerConfiguracionPorClave(claveMailAdmin);
	}
	
	public static String obtenerUrlBancos() {
		return obtenerConfiguracionPorClave(claveUrlBancos);
	}
	
	public static String obtenerUrlCGP() {
		return obtenerConfiguracionPorClave(claveUrlCGP);
	}
	
	private static int obtenerConfiguracionTipoEntero(String clave)
	{
		return Integer.parseInt(obtenerConfiguracionPorClave(clave));
	}

	private static String obtenerConfiguracionPorClave(String clave) {
		Properties prop = new Properties();
		InputStream input = null;
		String valorConfig = null;

		try {
			input = new FileInputStream(nombreConfig);
			prop.load(input);
			valorConfig = prop.getProperty(clave);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return valorConfig;
	}
}