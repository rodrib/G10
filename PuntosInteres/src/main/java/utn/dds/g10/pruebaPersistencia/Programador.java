package utn.dds.g10.pruebaPersistencia;

import javax.persistence.Entity;

@Entity
public class Programador extends Tecnologo
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lenguajeFavorito;
    private int aniosDeExperiencia;

    public Programador()
    {
    }

    public Programador(String nombre, int edad, int aniosDeEstudios, String lenguajeFavorito, int aniosDeExperiencia)
    {
        super(nombre, edad, aniosDeEstudios);
        this.lenguajeFavorito = lenguajeFavorito;
        this.aniosDeExperiencia = aniosDeExperiencia;
    }

    public int getAniosDeExperiencia()
    {
        return aniosDeExperiencia;
    }

    public void setAniosDeExperiencia(int aniosDeExperiencia)
    {
        this.aniosDeExperiencia = aniosDeExperiencia;
    }

    public String getLenguajeFavorito()
    {
        return lenguajeFavorito;
    }

    public void setLenguajeFavorito(String lenguajeFavorito)
    {
        this.lenguajeFavorito = lenguajeFavorito;
    }
}