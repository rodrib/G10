package utn.dds.g10.pruebaPersistencia;

import javax.persistence.*;


@Entity
public class Order {
	
	public Order(int id, String desc)
	{
		this.setId(id);
		this.setNumber(desc);
		
	}
   @Id @GeneratedValue public Integer getId() { return id; }
   public void setId(Integer id) { this.id = id; }
   private Integer id;

   public String getNumber() { return number; }
   public void setNumber(String number) { this.number = number; }
   private String number;

   @ManyToOne
   public Customer getCustomer() { return customer; }
   public void setCustomer(Customer customer) { this.customer = customer; }
   private Customer customer;
}