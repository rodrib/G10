package utn.dds.g10.DAO;

import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.entidades.POI;

public class PoiDao extends DaoRelacional {

	public static POI obtenerDeparmentPorId(Long id) throws Exception {
		try {
			POI objDB = (POI)DaoRelacional.obtenerPoiPorId(id, POI.class);
			return objDB;
		} catch (Exception e) {
			throw new Exception("Ocurrió un error al intentar obtener el usuario");
		}
	}
}