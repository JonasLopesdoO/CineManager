package br.ufc.vev.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ator")
public class Ator{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String sobre;
	
	@ManyToMany(mappedBy = "atores")
	private List<Filme> filmes;
	
	public Ator(String nome, String sobre) {
		this.setNome(nome);
		this.setSobre(sobre);
		this.filmes = new ArrayList<>();
	}
	
	public Ator() {
		
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
	public String getSobre() {
		return sobre;
	}
	public void setSobre(String sobre) {
		this.sobre = sobre;
	}
	
	public List<Filme> getFilmes(){
		return filmes;
	}
	
	public boolean addFilme(Filme filme) {
		this.filmes.add(filme);
		return filme.getAtores().add(this);
	}
	
	public boolean removerFilme(Filme filme) {
		this.filmes.remove(filme);
		return filme.getAtores().remove(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Ator ator = (Ator) obj;
		
		return id == ator.id;
	}

	@Override
	public String toString() {
		return "Ator [id=" + id + ", nome=" + nome + ", sobre=" + sobre + "]";
	}
	
	
}
