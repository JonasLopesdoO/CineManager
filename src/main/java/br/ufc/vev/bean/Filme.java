package br.ufc.vev.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "filme")
public class Filme {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String sinopse;
	private int duracao;
	

	@OneToMany(fetch=FetchType.LAZY)
	private List<Sessao> sessoes;
	
	@ManyToMany
	(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "filme_atores",
	           joinColumns = @JoinColumn(name = "filme_id", referencedColumnName = "id"),
	           inverseJoinColumns = @JoinColumn(name = "ator_id", referencedColumnName = "id"))
	private List<Ator> atores;
	
	@ManyToMany
	(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "filme_diretores",
			   joinColumns = @JoinColumn(name = "filme_id", referencedColumnName = "id"),
			   inverseJoinColumns = @JoinColumn(name = "diretor_id", referencedColumnName = "id"))
 	private List<Diretor> diretores;
	
	@ManyToMany
	(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "filme_generos",
			   joinColumns = @JoinColumn(name = "filme_id", referencedColumnName = "id"),
			   inverseJoinColumns = @JoinColumn(name = "genero_id", referencedColumnName = "id"))
	private List<Genero> generos;
	

	
	public Filme(String nome, String sinopse, int duracao){
		this.nome = nome;
		this.sinopse = sinopse;
		this.duracao = duracao;
		this.atores = new ArrayList<>();
		this.diretores = new ArrayList<>();
		this.generos = new ArrayList<>();
	}

	public Filme() {
		
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

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
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
	
	public List<Sessao> getSessoes() {
		return sessoes;
	}
	
	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
	
	
	@Override
	public String toString() {
		return "Filme [id=" + id + ", nome=" + nome + ", sinopse =" + sinopse + ", duração=" + duracao
				+ ", atores=" + atores + ", diretores =" + diretores + ", generos =" + generos + "]";
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Filme filme = (Filme) obj;
		
		return id == filme.id;
	}

	public boolean removeSessao(Sessao sessao) {
		sessao.setFilme(null);
		return this.sessoes.remove(sessao);
	}
}
