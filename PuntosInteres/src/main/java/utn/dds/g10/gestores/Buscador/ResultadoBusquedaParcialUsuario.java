package utn.dds.g10.gestores.Buscador;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class ResultadoBusquedaParcialUsuario {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RESULTADOPARCIALUSUARIO_ID")
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column
	private String usuario;
	
	@OneToMany(mappedBy = "resultado")
	private List<ResultadoBusquedaParcial> resultados = new ArrayList<ResultadoBusquedaParcial>();
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public List<ResultadoBusquedaParcial> getResultados() {
		return resultados;
	}
	public void setResultados(List<ResultadoBusquedaParcial> resultados) {
		this.resultados = resultados;
	}
	
	public void agregarResultado(ResultadoBusquedaParcial resultado){
		resultados.add(resultado);
	}
	
	
}
