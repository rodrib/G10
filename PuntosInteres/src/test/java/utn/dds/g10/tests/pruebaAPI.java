package utn.dds.g10.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;

import utn.dds.g10.entidades.POI;
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
	
	public void imprimirListadoBancos(List<POI> poiBancos)
	{
		for (POI bancoPoi : poiBancos) {
			System.out.println("nombre banco:" + bancoPoi.getNombre());
			System.out.println("nombre sucursal:" + bancoPoi.getLocacion().getBarrio() );
			System.out.println("nombre gerente:" + ((SucursalBanco)bancoPoi.getTipo()).getNombreGerente());
			
			System.out.println("Servicios:");
			for (String servicioBanco : ((SucursalBanco)bancoPoi.getTipo()).getServicios()) {
				System.out.println("\t" +  servicioBanco);	
			}
		}
	}
	
}
