package utn.dds.g10.DAO;

public interface Dao {

	public int crearEntidad(Object entidad);
	public void modificarEntidad(Object entidadModificada);
	public void eliminarEntidad(Object entidadEliminar) throws Exception;
}
