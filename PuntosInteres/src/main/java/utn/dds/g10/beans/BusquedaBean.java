package utn.dds.g10.beans;
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

		new Order("A0001", "Intel CPU",
				new BigDecimal("700.00"), 1),
		new Order("A0002", "Harddisk 10TB",
				new BigDecimal("500.00"), 2),
		new Order("A0003", "Dell Laptop",
				new BigDecimal("11600.00"), 8),
		new Order("A0004", "Samsung LCD",
				new BigDecimal("5200.00"), 3),
		new Order("A0005", "A4Tech Mouse",
				new BigDecimal("100.00"), 10)
	));

	public ArrayList<Order> getOrderList() {

		return orderList;

	}

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
		
		
		for (Order criterioBusqueda:getOrderlist()){
		gestorPoi.BuscarPoi(criterioBusqueda.getOrderNo(), "usuario");
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
}