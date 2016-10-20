package utn.dds.g10.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import utn.dds.g10.gestores.Buscador.HistorialConsultasUsuario;
import utn.dds.g10.gestores.Buscador.ResultadoBusquedaParcial;
import utn.dds.g10.gestores.Buscador.ResultadoBusquedaParcialUsuario;

@ManagedBean(name="historial")
@SessionScoped
public class HistorialBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String fechaDesde="";
	private String fechaHasta="";
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	private String usuario="";
	private String parametros;
	private int cantidadPois;
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getParametros() {
		return parametros;
	}
	public void setParametros(String parametros) {
		this.parametros = parametros;
	}
	public int getCantidadPois() {
		return cantidadPois;
	}
	public void setCantidadPois(int cantidadPois) {
		this.cantidadPois = cantidadPois;
	}

	//getter and setter methods

	private static final ArrayList<resultadoHistorial> resultadoList =
		new ArrayList<resultadoHistorial>();

	public ArrayList<resultadoHistorial> getresultadoList() {

		return resultadoList;

	}

	public String agregaResultadoListaConsulta() {
		LocalDateTime fechaDesde=null;
		LocalDateTime fechaHasta=null;
		
		if (!this.fechaDesde.equalsIgnoreCase("")){
			fechaDesde = obtenerFecha(this.fechaDesde);
		}
		if (!this.fechaHasta.equalsIgnoreCase("")){
			fechaHasta = obtenerFecha(this.fechaHasta);
		}
		
		int soloUsuario=0;
		ResultadoBusquedaParcialUsuario resultadoUsuario = new ResultadoBusquedaParcialUsuario();
		ResultadoBusquedaParcialUsuario resultadoUsuarioFiltrado = new ResultadoBusquedaParcialUsuario();
		//Con usuario
		if (!this.usuario.equalsIgnoreCase("")) {
			resultadoUsuario = HistorialConsultasUsuario.buscarUsuario(usuario);
			// Solo usuario
			if (this.fechaDesde.equalsIgnoreCase("")&& this.fechaHasta.equalsIgnoreCase("")) {
				soloUsuario=1;
			}
			// Usuario y fecha Desde
			else if (!this.fechaDesde.equalsIgnoreCase("")&&this.fechaHasta.equalsIgnoreCase("")){
				resultadoUsuarioFiltrado = HistorialConsultasUsuario.filtrarUsuarioFechaDesde(fechaDesde, resultadoUsuario);
			}
			// Usuario y fecha Hasta
			else if (this.fechaDesde.equalsIgnoreCase("")&&!this.fechaHasta.equalsIgnoreCase("")){
				resultadoUsuarioFiltrado = HistorialConsultasUsuario.filtrarUsuarioFechaHasta(fechaHasta, resultadoUsuario);
			}
			// Usuario, fechaDesde y fechaHasta
			else if (!this.fechaDesde.equalsIgnoreCase("")&&!this.fechaHasta.equalsIgnoreCase("")){
				resultadoUsuarioFiltrado = HistorialConsultasUsuario.filtrarUsuarioFechaDesdeHasta(fechaDesde,fechaHasta, resultadoUsuario);
			}
			
			if (soloUsuario==1){			
				ResultadoBusquedaParcial resultadoBusquedaParcialEncontrado = new ResultadoBusquedaParcial();
				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuario.getResultados()
						.iterator(); consultaBusquedaParcial.hasNext();) {
					
					resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();

					resultadoHistorial elemLista = new resultadoHistorial(resultadoUsuario.getUsuario(), resultadoBusquedaParcialEncontrado.getFecha().toString(),
							resultadoBusquedaParcialEncontrado.getCriterioBusqueda(), resultadoBusquedaParcialEncontrado.getCantidadResultados()); 
					resultadoList.add(elemLista);
				}						
			}else{
				ResultadoBusquedaParcial resultadoBusquedaParcialEncontrado = new ResultadoBusquedaParcial();
				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuarioFiltrado.getResultados()
						.iterator(); consultaBusquedaParcial.hasNext();) {
					
					resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();

					resultadoHistorial elemLista = new resultadoHistorial(resultadoUsuario.getUsuario(), resultadoBusquedaParcialEncontrado.getFecha().toString(),
							resultadoBusquedaParcialEncontrado.getCriterioBusqueda(), resultadoBusquedaParcialEncontrado.getCantidadResultados()); 
					resultadoList.add(elemLista);
				}	
			}
		
		//Sin Usuario
		}else{
			List<ResultadoBusquedaParcialUsuario> ListaUsuarioObtenida = new ArrayList<ResultadoBusquedaParcialUsuario>();
			//Solo fecha Desde
			if (!this.fechaDesde.equalsIgnoreCase("")&&this.fechaHasta.equalsIgnoreCase("")){
				ListaUsuarioObtenida = HistorialConsultasUsuario.buscarFechaDesde(fechaDesde);
			}
			//Solo fecha Hasta
			else if (this.fechaDesde.equalsIgnoreCase("")&&!this.fechaHasta.equalsIgnoreCase("")){
				ListaUsuarioObtenida = HistorialConsultasUsuario.buscarFechaHasta(fechaHasta);
			}
			//Sin usuario, fechaDesde y fechaHasta
			else if (!this.fechaDesde.equalsIgnoreCase("")&&!this.fechaHasta.equalsIgnoreCase("")){
				ListaUsuarioObtenida = HistorialConsultasUsuario.buscarFechaDesdeHasta(fechaDesde,fechaHasta);
			
			}
			
			ResultadoBusquedaParcialUsuario resultadoUsuarioParcialEncontrado = new ResultadoBusquedaParcialUsuario();

			for (Iterator<ResultadoBusquedaParcialUsuario> consultaBusqueda = ListaUsuarioObtenida
					.iterator(); consultaBusqueda.hasNext();) {
				resultadoUsuarioParcialEncontrado = consultaBusqueda.next();	
				ResultadoBusquedaParcial resultadoBusquedaParcialEncontrado = new ResultadoBusquedaParcial();
				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuarioFiltrado.getResultados()
						.iterator(); consultaBusquedaParcial.hasNext();) {	
					resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();
					resultadoHistorial elemLista = new resultadoHistorial(resultadoUsuarioParcialEncontrado.getUsuario(), resultadoBusquedaParcialEncontrado.getFecha().toString(),
					resultadoBusquedaParcialEncontrado.getCriterioBusqueda(), resultadoBusquedaParcialEncontrado.getCantidadResultados()); 
					resultadoList.add(elemLista);
				}
			}		
		}		
	return null;	
	}
	
	public static LocalDateTime obtenerFecha(String fechaString) {   
        int contador = 0;
        int dia=0,mes=0,anio=0;
        char[] c = fechaString.toCharArray();
        StringBuilder p = new StringBuilder();
        for (int i = 0; i < c.length;i++) {
            int code = Character.codePointAt(c, i);
            if (code!=47){
            	p.append(c[i]);
            }else{
            	contador++;
            	if (contador==1){
            		dia = Integer.parseInt(p.toString());
            		p.delete(0, p.length());
            	}else if (contador==2){
            		mes = Integer.parseInt(p.toString());
            		p.delete(0, p.length());
            	}
            }
        }
        anio = Integer.parseInt(p.toString());
        LocalDateTime fecha = LocalDateTime.of(anio,mes,dia,0,0,0,0);
		return fecha;
    }
	
	public class resultadoHistorial {
		private String fecha;
		private String usuario;
		private String parametros;
		private int cantidadPois;
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getUsuario() {
			return usuario;
		}
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
		public String getParametros() {
			return parametros;
		}
		public void setParametros(String parametros) {
			this.parametros = parametros;
		}
		public int getCantidadPois() {
			return cantidadPois;
		}
		public void setCantidadPois(int cantidadPois) {
			this.cantidadPois = cantidadPois;
		}
		
		public resultadoHistorial(String usuario, String fecha,
				String parametros, int cantidadPois) {
			this.usuario = usuario;
			this.fecha = fecha;
			this.parametros = parametros;
			this.cantidadPois = cantidadPois;
		}
		
	}
}