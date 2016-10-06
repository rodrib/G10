package utn.dds.g10.Utiles;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class TokenLocalComercial {
	
	public static List<String> obtenerPalabrasClave(String Contenido) {
        List<String> ListaPalabrasClave = new ArrayList<String>();    
        
        char[] c = Contenido.toCharArray();
        StringBuilder p = new StringBuilder();
        int tokenEncontrado = 0;
        for (int i = 0; i < c.length;i++) {
            int code = Character.codePointAt(c, i);
            
            if (code==59)
            	tokenEncontrado=1;
            if ((code!=59)&&(tokenEncontrado==1))
            	p.append(c[i]);
        }
        String ContenidoPalabrasClave=p.toString(); 
        
        String[] tokensPalabrasClave = ContenidoPalabrasClave.split(" ");
        for (String token : tokensPalabrasClave)
        {
        	ListaPalabrasClave.add(token);
        }
        
		return ListaPalabrasClave;
    }
	
	public static String obtenerLocalComercial(String Contenido) {   
        
        char[] c = Contenido.toCharArray();
        StringBuilder p = new StringBuilder();
        for (int i = 0; i < c.length;i++) {
            int code = Character.codePointAt(c, i);
           
            if (code!=59)
            	p.append(c[i]);
            else
            	break;
        }	
		
		return p.toString();
    }

}
