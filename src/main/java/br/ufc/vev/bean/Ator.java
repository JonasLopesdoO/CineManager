package br.ufc.vev.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ator")
public class Ator extends Pessoa{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String sobre;
	
	@ManyToMany(mappedBy = "atores")
	private List<Filme> filmes;
	
	public Ator(String nome, String sobre) {
		this.nome = nome;
		this.sobre = sobre;
		this.filmes = new ArrayList<>();
	}
	
	public Ator() {	}
		
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
