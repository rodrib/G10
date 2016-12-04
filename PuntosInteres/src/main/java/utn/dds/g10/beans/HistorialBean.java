package utn.dds.g10.beans;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import utn.dds.g10.entidades.POI;
import utn.dds.g10.gestores.Buscador.HistorialConsultasUsuario;
import utn.dds.g10.gestores.Buscador.ResultadoBusquedaParcial;
import utn.dds.g10.gestores.Buscador.ResultadoBusquedaParcialUsuario;

enum tipoCargaFecha {soloDesde, soloHasta, ambasCargadas, ninguna};

@ManagedBean(name="historial")
@SessionScoped
public class HistorialBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public String convertStringToDate(Date indate)
	{
	   String dateString = null;
	   SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
	   try{
		dateString = sdfr.format( indate );
	   }catch (Exception ex ){
		System.out.println(ex);
	   }
	   return dateString;
	}

	public Date getFechaDesdeDate() {
		return fechaDesdeDate;
	}
	public void setFechaDesdeDate(Date fechaDesdeDate) {
		this.fechaDesdeDate = fechaDesdeDate;
	}
	public Date getFechaHastaDate() {
		return fechaHastaDate;
	}
	public void setFechaHastaDate(Date fechaHastaDate) {
		this.fechaHastaDate = fechaHastaDate;
	}

	private Date fechaDesdeDate=null;
	private Date fechaHastaDate=null;
	
	private String fechaDesde="";
	private String fechaHasta="";
	private List<POI> listaPOIDetalle; 
	
	public List<POI> getListaPOIDetalle() {
		return listaPOIDetalle;
	}
	public void setListaPOIDetalle(List<POI> listaPOIDetalle) {
		this.listaPOIDetalle = listaPOIDetalle;
	}
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
	
	
	private boolean tieneValorCargado(LocalDateTime fecha)
	{
		return (fecha != null);
	}

	public String agregaResultadoListaConsulta() {
		
		resultadoList.clear();		
		
		LocalDateTime fechaDesde=null;
		LocalDateTime fechaHasta=null;
		
		if (this.fechaDesdeDate != null){
			this.fechaDesde = convertStringToDate(this.fechaDesdeDate);
			fechaDesde = LocalDateTime.ofInstant(this.fechaDesdeDate.toInstant(), ZoneId.systemDefault());
			
		}
		
		if (this.fechaHastaDate != null){
			this.fechaHasta = convertStringToDate(this.fechaHastaDate);
			fechaHasta = LocalDateTime.ofInstant(this.fechaHastaDate.toInstant(), ZoneId.systemDefault());
		}
		
		int soloUsuario=0;
		ResultadoBusquedaParcialUsuario resultadoUsuario = new ResultadoBusquedaParcialUsuario();
		ResultadoBusquedaParcialUsuario resultadoUsuarioFiltrado = new ResultadoBusquedaParcialUsuario();
		
		boolean tieneValorFechaDesde = tieneValorCargado(fechaDesde);
		boolean tieneValorFechaHasta = tieneValorCargado(fechaHasta);
		
		//Con usuario
		if (this.usuario != null && !this.usuario.equalsIgnoreCase("")) {
			
			tipoCargaFecha tipo = tipoCargaFecha.ninguna;
			
			if(!tieneValorFechaDesde && ! tieneValorFechaHasta)
			{
				tipo = tipoCargaFecha.ninguna;
			}
			
			if(tieneValorFechaDesde && ! tieneValorFechaHasta)
			{
				tipo = tipoCargaFecha.soloDesde;
			}
			
			if(!tieneValorFechaDesde && tieneValorFechaHasta)
			{
				tipo = tipoCargaFecha.soloHasta;
			}
			
			if(tieneValorFechaDesde &&  tieneValorFechaHasta)
			{
				tipo = tipoCargaFecha.ambasCargadas;
			}
			
			resultadoUsuario = HistorialConsultasUsuario.buscarUsuario(this.usuario);
			
			switch (tipo) {
			case ninguna:
				soloUsuario=1;
				break;
			case soloDesde:
				resultadoUsuarioFiltrado = HistorialConsultasUsuario.filtrarUsuarioFechaDesde(fechaDesde, resultadoUsuario);
				break;
			case soloHasta:
				resultadoUsuarioFiltrado = HistorialConsultasUsuario.filtrarUsuarioFechaHasta(fechaHasta, resultadoUsuario);
				break;
			case ambasCargadas:
				resultadoUsuarioFiltrado = HistorialConsultasUsuario.filtrarUsuarioFechaDesdeHasta(fechaDesde,fechaHasta, resultadoUsuario);
				break;
			default:
				break;
			}
			
			if (soloUsuario==1){			
				ResultadoBusquedaParcial resultadoBusquedaParcialEncontrado = new ResultadoBusquedaParcial();
				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuario.getResultados()
						.iterator(); consultaBusquedaParcial.hasNext();) {
					
					resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();

				List<POI> poisFiltrados = (filtrarRepetidos(resultadoBusquedaParcialEncontrado.getListaPOISbusquedaParcial()));
				resultadoBusquedaParcialEncontrado.setCantidadResultados(poisFiltrados.size());
					
					resultadoHistorial elemLista = new resultadoHistorial(resultadoUsuario.getUsuario(), resultadoBusquedaParcialEncontrado.getFecha().toString(),
							resultadoBusquedaParcialEncontrado.getCriterioBusqueda(), resultadoBusquedaParcialEncontrado.getCantidadResultados(),resultadoBusquedaParcialEncontrado.getListaPOISbusquedaParcial()); 
					resultadoList.add(elemLista);
				}						
			}else{
				ResultadoBusquedaParcial resultadoBusquedaParcialEncontrado = new ResultadoBusquedaParcial();
				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuarioFiltrado.getResultados()
						.iterator(); consultaBusquedaParcial.hasNext();) {
					
					resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();

					resultadoHistorial elemLista = new resultadoHistorial(resultadoUsuario.getUsuario(), resultadoBusquedaParcialEncontrado.getFecha().toString(),
							resultadoBusquedaParcialEncontrado.getCriterioBusqueda(), resultadoBusquedaParcialEncontrado.getCantidadResultados(),resultadoBusquedaParcialEncontrado.getListaPOISbusquedaParcial()); 
					resultadoList.add(elemLista);
				}	
			}
		
		if (this.fechaDesde.equalsIgnoreCase("")&&this.fechaHasta.equalsIgnoreCase(""))
			return null;
		
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
				for (Iterator<ResultadoBusquedaParcial> consultaBusquedaParcial = resultadoUsuarioParcialEncontrado.getResultados()
						.iterator(); consultaBusquedaParcial.hasNext();) {	
					resultadoBusquedaParcialEncontrado = consultaBusquedaParcial.next();
					resultadoHistorial elemLista = new resultadoHistorial(resultadoUsuarioParcialEncontrado.getUsuario(), resultadoBusquedaParcialEncontrado.getFecha().toString(),
					resultadoBusquedaParcialEncontrado.getCriterioBusqueda(), resultadoBusquedaParcialEncontrado.getCantidadResultados(),resultadoBusquedaParcialEncontrado.getListaPOISbusquedaParcial()); 
					resultadoList.add(elemLista);
				}
			}		
		}		
	return null;	
	}
	
public List<POI> filtrarRepetidos(List<POI> listaPois){
		
		listaPOIsAuxiliar.clear();
		
		for (int j = 0; j < (listaPois.size()); j++) {
			Long idPOI = listaPois.get(j).getId();
				int cantResultado= 0;
				for (int m = 0; m < (listaPOIsAuxiliar.size()); m++) {
					if(listaPOIsAuxiliar.get(m).getId()==(idPOI)){
						cantResultado =1;
					}								
				}
				if(cantResultado==0){
					listaPOIsAuxiliar.add(listaPois.get(j));								
				}						
		}		
		
		return listaPOIsAuxiliar;		
	}
	
	private static final List<POI> listaPOIsAuxiliar =
	new ArrayList<POI>();
	
	public List<POI> getPoiList() {
	return listaPOIsAuxiliar;
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
		private List<POI> listaPOIs;
//		ArrayList<resultadoHistorial> resultadoList;
		
		public List<POI> getListaPOIs() {
			return listaPOIs;
		}
		public void setListaPOIs(List<POI> listaPOIs) {
			this.listaPOIs = listaPOIs;
		}
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
				String parametros, int cantidadPois,List<POI> listaPOIs) {
			this.usuario = usuario;
			this.fecha = fecha;
			this.parametros = parametros;
			this.cantidadPois = cantidadPois;
			this.listaPOIs =listaPOIs;
		}
		
	}
}