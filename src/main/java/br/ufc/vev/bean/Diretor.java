package br.ufc.vev.bean;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Diretor extends Pessoa{

	public Diretor(String nome, String sobre) {
		super(nome, sobre);
	}
	
	@ManyToOne
	@JoinColumn(name = "id")
	Filme filme;

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	
}
