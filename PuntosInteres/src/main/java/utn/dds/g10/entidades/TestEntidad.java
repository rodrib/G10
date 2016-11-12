package utn.dds.g10.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TestEntidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idTest;
	
	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	@ManyToOne
	@JoinColumn(name="rsId",  insertable=false, updatable=false, nullable=false)
	ResultadoConsulta resultadoConsulta;
	
	@Column
	String nombre;

	public ResultadoConsulta getResultadoConsulta() {
		return resultadoConsulta;
	}

	public void setResultadoConsulta(ResultadoConsulta resultadoConsulta) {
		this.resultadoConsulta = resultadoConsulta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
