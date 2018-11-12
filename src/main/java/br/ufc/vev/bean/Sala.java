package br.ufc.vev.bean;

import java.util.List;

import javax.annotation.Nonnegative;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sala")
public class Sala {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty @NotNull
	private String nome;
	private TipoSala tipo;
	@Nonnegative
	private int capacidade;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<Sessao> sessoes;
	
	public Sala(String nome, TipoSala tipo, int capacidade) {
		this.nome = nome;
		this.tipo = tipo;
		this.capacidade = capacidade;
	}
	
	public Sala() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoSala getTipo() {
		return tipo;
	}

	public void setTipo(TipoSala tipo) {
		this.tipo = tipo;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public List<Sessao> getSessoes() {
		return sessoes;
	}
	
	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
	
	public boolean removeSessao(Sessao sessao) {
		sessao.setSala(null);
		return this.sessoes.remove(sessao);
	}
}
