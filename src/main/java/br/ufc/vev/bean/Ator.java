package br.ufc.vev.bean;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ator extends Pessoa{

	public Ator(String nome, String sobre) {
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
