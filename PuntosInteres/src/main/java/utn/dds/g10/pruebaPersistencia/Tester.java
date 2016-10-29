package utn.dds.g10.pruebaPersistencia;

import javax.persistence.Entity;

@Entity
public class Tester extends Tecnologo
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String herramientaDeTesteo;
    
    public Tester()
    {
    }

    public Tester(String nombre, int edad, int aniosDeEstudios, String herramientaDeTesteo)
    {
        super(nombre, edad, aniosDeEstudios);
        this.herramientaDeTesteo = herramientaDeTesteo;
    }
    
    public String getHerramientaDeTesteo()
    {
        return herramientaDeTesteo;
    }

    public void setHerramientaDeTesteo(String herramientaDeTesteo)
    {
        this.herramientaDeTesteo = herramientaDeTesteo;
    }
}