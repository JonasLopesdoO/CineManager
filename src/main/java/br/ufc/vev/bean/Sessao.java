package br.ufc.vev.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "sessao")
public class Sessao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataInicio;
	@NotNull
	private LocalDate dataFim;
	@NotNull
	private LocalTime horario;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "filme_id")
	private Filme filme;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "sala_id")
	private Sala sala;
	
	public Sessao(LocalTime horario, LocalDate inicio, LocalDate fim) {
		this(horario, inicio, fim, null, null);
	}
	
	public Sessao(LocalTime horario, LocalDate inicio, LocalDate fim, Filme filme, Sala sala) {
		this.filme = filme;
		this.sala = sala;
		this.horario = horario;
		this.dataInicio = inicio;
		this.dataFim = fim;
	}
	
	public Sessao() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalTime getHorario() {
		return horario;
	}
	
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	@Override
	public String toString() {
		return "Sessao [id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", horario=" + horario
				+ ", filme=" + filme + ", sala=" + sala + "]";
	}

	
}
