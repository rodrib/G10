package utn.dds.g10.procesos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import utn.dds.g10.Utiles.LeerFichero;
import utn.dds.g10.Utiles.TokenLocalComercial;
import utn.dds.g10.datos.Repositorio;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.gestores.Buscador.BuscadorLocalComercial;

public class ActualizadorLocales implements Job, Proceso {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		List<String> ListaContenido;
				
		try {
			//Lee el archivo de texto plano
			ListaContenido = LeerFichero.devuelveContenido("/home/dds/Escritorio/LocalComercial.txt");
			
			for (String linea : ListaContenido) {
				System.out.println("Obtener local");
				String LocalComercialNombre = TokenLocalComercial.obtenerLocalComercial(linea);	
				System.out.println("Obtener palabra clave");
				List<String> ListaPalabrasClave = TokenLocalComercial.obtenerPalabrasClave(linea);
				BuscadorLocalComercial buscador = new BuscadorLocalComercial();
				System.out.println("Busca comercial");
				ResultadoConsulta resultado = buscador.BuscarLocalComercial(LocalComercialNombre);
				
				if (resultado.getCantidadResultados()!=0){
					System.out.println("Resultados !=0");
					//Toma el primer Local de los Resultados
					POI localComercialPOI = resultado.getPuntos().get(0);
					localComercialPOI.setPalabrasClaves(ListaPalabrasClave);
					Repositorio.ModificarPOI(localComercialPOI);
				}else{ //No existe, el Local se da de Alta
					System.out.println("Resultados 0");
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
		
	}

	public JobDetail obtenerJobDetail() {
		return JobBuilder.newJob(ActualizadorLocales.class).withIdentity("JobActulizacionLocales", "grupo1").build();
	}
}
