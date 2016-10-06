package utn.dds.g10.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Test;

public class Token {
	
	@Test
	public void testearProcesoActualizadorLocales() throws Exception {

    String LocalComercial;
    List<String> ListaPalabrasClave = new ArrayList<String>();
	
	String[] tokens = "Carrousel;colegio escolar uniformes modas".split("[;]");
	
	LocalComercial = tokens[0];
	System.out.println(tokens[0]);     
	System.out.println(tokens[1]); 
    String ContenidoPalabrasClave = tokens[1];
    System.out.println(ContenidoPalabrasClave);
    StringTokenizer tokensPalabrasClave=new StringTokenizer(ContenidoPalabrasClave);
    while(tokensPalabrasClave.hasMoreTokens()){
    	ListaPalabrasClave.add((String) tokensPalabrasClave.nextElement());
    }
    
    
    
	}
	
}
