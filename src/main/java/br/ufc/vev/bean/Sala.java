package br.ufc.vev.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sala")
public class Sala {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private TipoSala tipo;
	private int capacidade;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id", nullable = false)
	private Sessao sessao;
	
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
	
	public Integer setId() {
		return id;
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
	
	public Sessao getSessao() {
		return sessao;
	}
	
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
}
