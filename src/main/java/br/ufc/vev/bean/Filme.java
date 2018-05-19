package br.ufc.vev.bean;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "filme")
public class Filme {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String sinopse;
	private LocalTime duracao;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "filme")
	@JsonManagedReference
	private List<Ator> atores;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "filme")
	@JsonManagedReference
 	private List<Diretor> diretores;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "filme")
	@JsonManagedReference
	private List<Genero> generos;
	
	public Filme(String nome, String sinopse, LocalTime duracao, List<Ator> atores, List<Diretor> diretores, List<Genero> generos){
		this.setNome(nome);
		this.setSinopse(sinopse);
		this.setDuracao(duracao);
		this.setAtores(atores);
		this.setDiretores(diretores);
		this.setGeneros(generos);
	}
	public Filme(Integer id) {
		this.setId(id);
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

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public LocalTime getDuracao() {
		return duracao;
	}

	public void setDuracao(LocalTime duracao) {
		this.duracao = duracao;
	}

	public List<Ator> getAtores() {
		return atores;
	}

	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}

	public List<Diretor> getDiretores() {
		return diretores;
	}

	public void setDiretores(List<Diretor> diretores) {
		this.diretores = diretores;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
	
	@Override
	public String toString() {
		return "Filme [id=" + id + ", nome=" + nome + ", sinopse =" + sinopse + ", duração=" + duracao
				+ ", atores=" + atores + ", diretores =" + diretores + ", generos =" + generos + "]";
	}
}
