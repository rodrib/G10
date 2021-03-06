package utn.dds.g10.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import utn.dds.g10.DAO.DaoRelacional;
@Entity
@Table(name = "TipoPoi_CGP")
@Access(value=AccessType.FIELD)
public class CGP extends TipoPoi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	private static final long serialVersionUID = 1L;
	@Column
	private String comuna;
	@Column
	private String zonas;
	@Column
	private String director;
	@Column
	private String domicilio;
	@Column
	private String telefono;
	
	@OneToMany(mappedBy = "cgp")
	private List<ServicioCGP> servicios = new ArrayList<ServicioCGP>();	
	
	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getZonas() {
		return zonas;
	}

	public void setZonas(String zonas) {
		this.zonas = zonas;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<ServicioCGP> getServicios() {
		return servicios;
	}
	

	public void setServicios(List<ServicioCGP> servicios) {
		this.servicios = servicios;
	}

	@Override
	public float getDistanciaMaxima() {
		return 0;
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		return miLocacion.getCodigoComuna() == otraLocacion.getCodigoComuna();
	}

	@Override
	public boolean estaDisponible(LocalDateTime fecha, String x) {
		
		if (x == ""){ // no se ingreso un valor en x
			Iterator<ServicioCGP> i = servicios.iterator();
			while(i.hasNext()){
				ServicioCGP servicio = (ServicioCGP) i.next();
				if(servicio!=null){
					if(servicio.estaDisponible(fecha)){
						return true;
					}
				}
			}
		}else{ // x es el nombre del servicio
			Iterator<ServicioCGP> i = servicios.iterator();
			while(i.hasNext()){
				ServicioCGP servicio = (ServicioCGP) i.next();
				
				if(servicio!=null){
					if(servicio.getNombre()==x){
						return servicio.estaDisponible(fecha);
					}
				}
			
			}
		}
		return false;
	}
	

	@Override
	public boolean CumpleCondicionBusqueda(String condicion) {
	
		for (Iterator<ServicioCGP> serviciosBusqueda = this.servicios.iterator(); serviciosBusqueda.hasNext();) {
			String nombreServicio =serviciosBusqueda.next().getNombre();
						
			if  (nombreServicio.contains(condicion)){
				return true;				
			}		
		}
		
//		if ((this.getZonas().indexOf(condicion)!=(-1))){			
//			return true;				
//		}
		
		return false;
	}

	@Override
	public String tipoPOI() {
		return "CGP";
	}

	@Override
	public TipoPoi obtenerPOI(int id) {
//		ArrayList<CGP> listaCGPs = new ArrayList<CGP>();
//		listaCGPs = (ArrayList<CGP>) DaoRelacional.obtenerCGPs();
//
//		for (CGP cgp : listaCGPs) {
//			if (cgp.getIdTipoPoi()==id)
//				return cgp;
//		}
		return null;
	}

	
	
}
