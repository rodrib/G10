package utn.dds.g10.beans;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
//import utn.dds.g10.entidades.*;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.*;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.json.JSONException;

@ManagedBean(name="order")
@SessionScoped
public class BusquedaBean implements Serializable{
	
	Usuario usuario;
	GestorPoi gestorPoi;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static ArrayList<Order> getOrderlist() {
		return orderList;
	}

	private static final long serialVersionUID = 1L;

	String orderNo;
	String productName;
	BigDecimal price;
	int qty;

	//getter and setter methods

	private static final ArrayList<Order> orderList =
		new ArrayList<Order>();

	public ArrayList<Order> getOrderList() {

		return orderList;

	}
		
	//agregado mio//
	private static final ArrayList<POI> poiList =
			new ArrayList<POI>();

		public ArrayList<POI> getPoiList() {

			return poiList;

		}
	//Fin agregado mio//
	public void addAction() {
		
		if(getOrderNo()!=""){
			Order order = new Order(this.orderNo, this.productName,
					this.price, this.qty);
			orderList.add(order);
		}		
	}
	
	public void searchAction(String nombreUsuario) throws MalformedURLException, JSONException, IOException {
		
		
		usuario = new Usuario();
		RolAdministrador rolAdministrador = new RolAdministrador();
		usuario.setRol(rolAdministrador);
		
		//Inicializo Gestor
		gestorPoi = new GestorPoi(usuario);
		ResultadoConsulta resultado;
		for (Order criterioBusqueda:getOrderlist()){
			
			resultado = gestorPoi.BuscarPoi(criterioBusqueda.getOrderNo(), nombreUsuario);
			List<POI> listadoPoi = resultado.getPuntos();
			if (resultado.getCantidadResultados() != 0){
				System.out.println(resultado.getCantidadResultados());
				System.out.println(resultado.getPuntos().get(0).getNombre());			
					
					for (int j = 0; j < (resultado.getCantidadResultados()); j++) {
						SegundaTabla st = new SegundaTabla(listadoPoi.get(j).getNombre());
							int cantResultado= 0;
							for (int m = 0; m < (poiList.size()); m++) {
								if(poiList.get(m).getNombre().equals(st.getStringNombre())){
									cantResultado =1;
								}								
							}
							if(cantResultado==0){
								poiList.add(listadoPoi.get(j));								
							}						
					}					
			}
			
		}
		
		repartirPOIenListas(poiList);
		
	}
	
	public void repartirPOIenListas(ArrayList<POI> listaPois){
		
		reiniciarListas();
	
		for (POI poi: listaPois){
			
			String tipoPOI = poi.getTipo().tipoPOI();
			
				if(tipoPOI.equals("CGP")){
					cgpList.add(poi);
				}else if(tipoPOI.equals("SucursalBanco")){
					bancoList.add(poi);
					}else if(tipoPOI.equals("ParadaColectivo")){
						paradaColectivoList.add(poi);
				}else if(tipoPOI.equals("LocalComercial")){
					localComercialList.add(poi);
				}
		}
	
	}
	
	public void reiniciarListas(){
		cgpList.clear();
		bancoList.clear();
		paradaColectivoList.clear();
		localComercialList.clear();
	}
	
	public String deleteAction(Order order) {

		orderList.remove(order);
		return null;
	}

	public static class Order{

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public int getQty() {
			return qty;
		}

		public void setQty(int qty) {
			this.qty = qty;
		}

		String orderNo;
		String productName;
		BigDecimal price;
		int qty;

		public Order(String orderNo, String productName,
				BigDecimal price, int qty) {
			this.orderNo = orderNo;
			this.productName = productName;
			this.price = price;
			this.qty = qty;
		}

		//getter and setter methods
	}
	
	public static class SegundaTabla{
	
		String stringNombre;

		public String getStringNombre() {
			return stringNombre;
		}

		public void setStringNombre(String stringNombre) {
			this.stringNombre = stringNombre;
		}

		public SegundaTabla(String nombre){
			
			this.stringNombre=nombre;
		
		}	
	}
	
	//agregado mio//
		private static final ArrayList<POI> bancoList =
				new ArrayList<POI>();

		public ArrayList<POI> getBancoList() {
			return bancoList;
		}

		private static final ArrayList<POI> cgpList =
				new ArrayList<POI>();

		public ArrayList<POI> getCgpList() {
			return cgpList;
		}

		private static final ArrayList<POI> localComercialList =
				new ArrayList<POI>();

		public ArrayList<POI> getLocalComercialList() {
			return localComercialList;
		}
		private static final ArrayList<POI> paradaColectivoList =
				new ArrayList<POI>();

		public ArrayList<POI> getParadaColectivoList() {
			return paradaColectivoList;
		}
}