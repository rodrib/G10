package utn.dds.g10.pruebaPersistencia;

import javax.persistence.Entity;

@Entity
public class Tecnologo extends Persona
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int aniosDeEstudios;
    
    public Tecnologo()
    {
    }

    public Tecnologo(String nombre, int edad, int aniosDeEstudios)
    {
        super(nombre, edad);
        this.aniosDeEstudios = aniosDeEstudios;
    }

    public int getAniosDeEstudios()
    {
        return aniosDeEstudios;
    }

    public void setAniosDeEstudios(int aniosDeEstudios)
    {
        this.aniosDeEstudios = aniosDeEstudios;
    }
}
