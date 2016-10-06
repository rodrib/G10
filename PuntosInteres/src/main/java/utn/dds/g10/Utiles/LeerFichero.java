package utn.dds.g10.Utiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
public class LeerFichero {
    
    public static List<String> devuelveContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        List<String> ListaContenido = new ArrayList<String>();
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            ListaContenido.add(cadena);
        }
        b.close();
        return ListaContenido;
    }
    public static String devuelveJSON(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        String StringJSON="";
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
        	StringJSON = StringJSON + cadena;
        }
        b.close();
        return StringJSON;
    } 
}