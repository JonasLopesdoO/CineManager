package br.ufc.vev.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "diretor")
public class Diretor extends Pessoa{
	
	
	@ManyToMany(mappedBy = "diretores")
	List<Filme> filmes;
	
	public Diretor(String nome, String sobre) {
		super(nome, sobre);
		this.filmes = new ArrayList<>();
	}
	
	public Diretor() {
		
	}

	public List<Filme> getFilmes(){
		return filmes;
	}
	
	@Override
	public String toString() {
		return "Diretor [id=" + super.getId() + "," + 
						" nome=" + super.getNome() + ", sobre=" + super.getSobre() + "]";
	}
	
}
