package utn.dds.g10.pruebaPersistencia;

import javax.persistence.Entity;


@Entity
public class Normal extends Persona
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ocupacion;

    public Normal()
    {
    }

    public Normal(String nombre, int edad, String ocupacion)
    {
        super(nombre, edad);
        this.ocupacion = ocupacion;
    }

    public String getOcupacion()
    {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion)
    {
        this.ocupacion = ocupacion;
    }
}