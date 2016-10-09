package utn.dds.g10.jobs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import utn.dds.g10.Utiles.LeerFichero;
import utn.dds.g10.Utiles.TokenLocalComercial;
import utn.dds.g10.datos.Repositorio;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.gestores.Buscador.BuscadorSinReportes;
import utn.dds.g10.model.CambiosPoi;
import utn.dds.g10.model.ElementoCambioPoi;
import utn.dds.g10.model.HistorialCambios;
import utn.dds.g10.model.ProcesoPoi;


public class ProcesoActualizaLocales extends ProcesoPoi {
	
	// En el constructor es donde queda definido el proceso siguiente
	public ProcesoActualizaLocales(){
		setSiguienteProceso(ProcesoBajaPois.class);
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<String> ListaContenido;
		
		try {
			CambiosPoi cambios = new CambiosPoi();
			cambios.setProcesoEjecutado(context.getJobDetail().getKey().getName());
			System.out.println("Obteniendo Locales comerciales a actualizar");
			//Lee el archivo de texto plano
			ListaContenido = LeerFichero.devuelveContenido("LocalComercial.txt");
			System.out.println("Modificando Locales comerciales");
			for (String linea : ListaContenido) {
				ElementoCambioPoi elem = new ElementoCambioPoi();
				String LocalComercialNombre = TokenLocalComercial.obtenerLocalComercial(linea);	
				List<String> ListaPalabrasClave = TokenLocalComercial.obtenerPalabrasClave(linea);
				BuscadorSinReportes buscador = new BuscadorSinReportes();
				ResultadoConsulta resultado = buscador.buscarSinReportes(LocalComercialNombre);
				
				if (resultado.getCantidadResultados()!=0){
					//Toma el primer Local de los Resultados
					POI localComercialPOI = resultado.getPuntos().get(0);
 					POI localComercialPOICambiado = localComercialPOI;
					localComercialPOICambiado.setPalabrasClaves(ListaPalabrasClave);
					POI poiModificado = Repositorio.ModificarPOI(localComercialPOICambiado);
					if (poiModificado!=null){
						elem.setCambio("modificacion");
						elem.setPoi(poiModificado);
					}
					
				}else{ //No existe, el Local se da de Alta
					POI localComercialPOI = new POI();
					localComercialPOI.setPalabrasClaves(ListaPalabrasClave);
					localComercialPOI.setNombre(LocalComercialNombre);
					LocalComercial local = new LocalComercial();
					localComercialPOI.setTipo(local);
					elem.setCambio("alta");
					elem.setPoi(localComercialPOI);
					
					Repositorio.AgregarPOI(localComercialPOI);
				}
				//Se agrega el cambio
				cambios.getListaPoi().add(elem);
			}
			HistorialCambios.agregarCambiosPoi(cambios);
			
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
