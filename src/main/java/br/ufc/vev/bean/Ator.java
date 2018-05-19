package br.ufc.vev.bean;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ator")
public class Ator extends Pessoa{

	public Ator(String nome, String sobre) {
		super(nome, sobre);
	}
	
	@ManyToOne
	@JoinColumn(name="id" ,referencedColumnName="id", insertable=false, updatable=false)
	Filme filme;

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
}
