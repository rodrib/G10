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
		new ArrayList<Order>(Arrays.asList(

		new Order("Banco Frances", "Banco Frances",
				new BigDecimal("700.00"), 1)		
	));

	public ArrayList<Order> getOrderList() {

		return orderList;

	}
	//agregado mio//
	private static final ArrayList<SegundaTabla> stringList =
			new ArrayList<SegundaTabla>();

		public ArrayList<SegundaTabla> getStringList() {

			return stringList;

		}
	//Fin agregado mio//
		
	//agregado mio//
	private static final ArrayList<POI> poiList =
			new ArrayList<POI>();

		public ArrayList<POI> getPoiList() {

			return poiList;

		}
		//Fin agregado mio//
	public String addAction() {

		Order order = new Order(this.orderNo, this.productName,
			this.price, this.qty);

		orderList.add(order);
		return null;
	}
	
	public void searchAction() throws MalformedURLException, JSONException, IOException {
		
		
		usuario = new Usuario();
		RolAdministrador rolAdministrador = new RolAdministrador();
		usuario.setRol(rolAdministrador);
		
		//Inicializo Gestor
		gestorPoi = new GestorPoi(usuario);
		ResultadoConsulta resultado;
		for (Order criterioBusqueda:getOrderlist()){
			
			resultado = gestorPoi.BuscarPoi(criterioBusqueda.getOrderNo(), "userTest");
			List<POI> listadoPoi = resultado.getPuntos();
			if (resultado.getCantidadResultados() != 0){
				System.out.println(resultado.getCantidadResultados());
				System.out.println(resultado.getPuntos().get(0).getNombre());			
					
					for (int j = 0; j < (resultado.getCantidadResultados()); j++) {
						SegundaTabla st = new SegundaTabla(listadoPoi.get(j).getNombre());
							int cantResultado= 0;
							for (int m = 0; m < (stringList.size()); m++) {
								if(stringList.get(m).getStringNombre().equals(st.getStringNombre())){
									cantResultado =1;
								}								
							}
							if(cantResultado==0){
								stringList.add(st);
								poiList.add(listadoPoi.get(j));
								
							}						
					}					
			}
			
		}
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
}