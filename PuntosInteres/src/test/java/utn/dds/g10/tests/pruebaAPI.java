package utn.dds.g10.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.json.JSONException;
import org.junit.Test;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.mappers.BancosJSON;

public class pruebaAPI {

	@Test
	public void testTraerBancoSantanderSoloPagos() throws MalformedURLException, IOException, JSONException {
		imprimirListadoBancos(BancosJSON.obtenerBancos("Santander", "Pagos"));
	}
	
	@Test
	public void testTraerTodosBancos() throws MalformedURLException, IOException, JSONException {
		imprimirListadoBancos(BancosJSON.obtenerBancos());
	}
	
	
	public void imprimirListadoBancos(List<SucursalBanco> bancos)
	{
			
		for (SucursalBanco sucursalBanco : bancos) {
			System.out.println("nombre banco:" + sucursalBanco.getNombre());
			System.out.println("nombre sucursal:" + sucursalBanco.getNombreSucursal());
			System.out.println("nombre gerente:" + sucursalBanco.getNombreGerente());
			
			System.out.println("Servicios:");
			for (String servicioBanco : sucursalBanco.getServicios()) {
				System.out.println("\t" +  servicioBanco);	
			}
		}
	}
	
}
