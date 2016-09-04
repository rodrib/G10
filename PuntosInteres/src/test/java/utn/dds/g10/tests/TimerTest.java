package utn.dds.g10.tests;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.junit.Assert;


import org.junit.Test;

public class TimerTest {
	
	/**
	 * @web http://www.jc-mouse.net
	 * @author Mouse
	 */
	public class Tiempo  {

	    private Timer timer = new Timer(); 
	    private int segundos=0;

	    class Contador extends TimerTask {
	        public void run() {
	            segundos++;
	     System.out.println("segundo: " + segundos);
	        }
	    }
	    public void Contar()
	    {
	        this.segundos=0;
	        timer = new Timer();
	        timer.schedule(new Contador(), 0, 1000);
	    }
	    
	    public void Detener() {
	        timer.cancel();
	    }
	    public int getSegundos()
	    {
	        return this.segundos;
	    }
	}
	
	
	
	@Test
	public void PruebaTimer()   {
		
		Tiempo t = new Tiempo();
		t.Contar();
		
		for (long i = 0; i < 1900000000; i++) {
			
		}
		
		t.Detener();
		
		//System.out.println(t.getSegundos());
	}

}




