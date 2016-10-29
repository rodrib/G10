package utn.dds.g10.pruebaPersistencia;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Persona implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private int edad;

    public Persona()
    {
    }

    public Persona(String nombre, int edad)
    {
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getEdad()
    {
        return edad;
    }

    public void setEdad(int edad)
    {
        this.edad = edad;
    }

    public long getId()
    {
        return id;
    }

    protected void setId(long id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}