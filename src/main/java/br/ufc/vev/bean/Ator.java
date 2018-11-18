package br.ufc.vev.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ator")
public class Ator extends Pessoa{
	
	
	@ManyToMany(mappedBy = "atores")
	private List<Filme> filmes;
	
	public Ator(String nome, String sobre) {
		super(nome, sobre);
		this.filmes = new ArrayList<>();
	}
	
	public Ator() {	}

	public List<Filme> getFilmes(){
		return filmes;
	}
	
	@Override
	public String toString() {
		return "Ator [id=" + super.getId() + ", "
				+ "nome=" + super.getNome() + ", sobre=" + super.getSobre() + "]";
	}
	
	
}
