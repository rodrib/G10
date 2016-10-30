package utn.dds.g10.entidades;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Horarios")
@Access(value = AccessType.FIELD)
public class Horarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "horariosID")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column
	private int diaSemana;
	@Column
	private int horaDesde;
	@Column
	private int minutosDesde;
	@Column
	private int horaHasta;
	@Column
	private int minutosHasta;
	
	public int getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}
	public int getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(int horaDesde) {
		this.horaDesde = horaDesde;
	}
	public int getMinutosDesde() {
		return minutosDesde;
	}
	public void setMinutosDesde(int minutosDesde) {
		this.minutosDesde = minutosDesde;
	}
	public int getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(int horaHasta) {
		this.horaHasta = horaHasta;
	}
	public int getMinutosHasta() {
		return minutosHasta;
	}
	public void setMinutosHasta(int minutosHasta) {
		this.minutosHasta = minutosHasta;
	}
	
}
