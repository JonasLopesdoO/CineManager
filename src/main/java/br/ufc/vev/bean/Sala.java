package br.ufc.vev.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sala")
public class Sala {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
//	private TipoSala tipo;
	private int capacidade;
	
	
	public Integer getId() {
		return id;
	}
	
	public Sala(int id) {
		this.id = id;
	}
	
	public Sala(String nome, int capacidade) {
		setNome(nome);
		setCapacidade(capacidade);
	}
	
	public Sala() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public TipoSala getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(TipoSala tipo) {
//		this.tipo = tipo;
//	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		Sala sala = (Sala) obj;
		if (this.getId() == sala.getId()) {
			return true;
		} else
			return false;
	}
	
}
