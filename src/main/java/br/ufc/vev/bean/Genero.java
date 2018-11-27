package br.ufc.vev.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "genero")
public class Genero {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 20)
	@NotBlank(message = "Nome é uma informação obrigatória.")
	@NotNull @NotEmpty
	private String nome;
	
	@ManyToMany(mappedBy = "generos")
	private List<Filme> filmes;
	
	public Genero(String nome) {
		this.nome = nome;
		this.filmes = new ArrayList<>();
	}
	
	public Genero() {
		
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
	
	public List<Filme> getFilmes() {
		return filmes;
	}
	
	@Override
	public String toString() {
		return "Genero [id=" + id + ", nome=" + nome + "]";
	}
	
	
	
}
