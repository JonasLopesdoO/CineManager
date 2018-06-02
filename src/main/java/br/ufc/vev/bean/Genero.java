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

@Entity
@Table(name = "genero")
public class Genero {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 20)
	@NotBlank(message = "Nome é uma informação obrigatória.")
	private String nome;
	
	@ManyToMany(mappedBy = "generos")
	private List<Filme> filmes;
	
	public Genero(String nome) {
		this.setNome(nome);
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
	
	public boolean addFilme(Filme filme) {
		this.filmes.add(filme);
		return filme.getGeneros().add(this);
	}
	
	public boolean removerFilme(Filme filme) {
		this.filmes.remove(filme);
		return filme.getGeneros().remove(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Genero genero = (Genero) obj;
		
		return id == genero.id;
	}

	@Override
	public String toString() {
		return "Genero [id=" + id + ", nome=" + nome + "]";
	}
	
	
	
}
