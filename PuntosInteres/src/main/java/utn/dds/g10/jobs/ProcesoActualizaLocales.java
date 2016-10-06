package utn.dds.g10.jobs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import utn.dds.g10.Utiles.LeerFichero;
import utn.dds.g10.Utiles.TokenLocalComercial;
import utn.dds.g10.datos.Repositorio;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.gestores.Buscador.BuscadorLocalComercial;
import utn.dds.g10.model.ProcesoPoi;


public class ProcesoActualizaLocales extends ProcesoPoi {
	
	// En el constructor es donde queda definido el proceso siguiente
	public ProcesoActualizaLocales(){
		setSiguienteProceso(ProcesoBajaPois.class);
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<String> ListaContenido;
		
		try {
			//Lee el archivo de texto plano
			ListaContenido = LeerFichero.devuelveContenido("/home/dds/git/G10/PuntosInteres/LocalComercial.txt");
			
			for (String linea : ListaContenido) {
				String LocalComercialNombre = TokenLocalComercial.obtenerLocalComercial(linea);	
				List<String> ListaPalabrasClave = TokenLocalComercial.obtenerPalabrasClave(linea);
				BuscadorLocalComercial buscador = new BuscadorLocalComercial();
				ResultadoConsulta resultado = buscador.BuscarLocalComercial(LocalComercialNombre);
				
				if (resultado.getCantidadResultados()!=0){
					//Toma el primer Local de los Resultados
					POI localComercialPOI = resultado.getPuntos().get(0);
					localComercialPOI.setPalabrasClaves(ListaPalabrasClave);
					Repositorio.ModificarPOI(localComercialPOI);
				}else{ //No existe, el Local se da de Alta
					POI localComercialPOI = new POI();
					localComercialPOI.setPalabrasClaves(ListaPalabrasClave);
					localComercialPOI.setNombre(LocalComercialNombre);
					Repositorio.AgregarPOI(localComercialPOI);
				}
			}	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		int x = 1 / 0; // Borrar comentario para forzar una excepci�n

	}

}
