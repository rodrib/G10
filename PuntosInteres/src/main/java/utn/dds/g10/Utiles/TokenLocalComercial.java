package utn.dds.g10.Utiles;

import java.util.ArrayList;

public class TokenLocalComercial {
	
	public static ArrayList<String> obtenerPalabrasClave(String Contenido) {
		ArrayList<String> ListaPalabrasClave = new ArrayList<String>();    
        
        char[] c = Contenido.toCharArray();
        StringBuilder p = new StringBuilder();
        int tokenEncontrado = 0;
        for (int i = 0; i < c.length;i++) {
            int code = Character.codePointAt(c, i);
            if ((code==59)||(code==894))
            	tokenEncontrado=1;
            if ((code!=59)&&(code!=894)&&(tokenEncontrado==1))
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
           
            if ((code!=59)&&(code!=894))
            	p.append(c[i]);
            else
            	break;
        }	
		
		return p.toString();
    }

}
