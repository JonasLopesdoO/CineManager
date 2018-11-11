package br.ufc.vev.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Cinema {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull @NotEmpty
	private String cidade;
	@NotNull @NotEmpty
	private String endereco;
	@NotNull @NotEmpty
	private String nome;
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<Sala> salas;
	
	public Cinema(String nome, String endereco, String cidade) {
		this.nome = nome;
		this.endereco = endereco;
		this.cidade = cidade;
	}
	
	public Cinema() { } 

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
	
	public boolean addSala(Sala sala) {
		sala.setCinema(this);
		return this.salas.add(sala); 
	}
	
	public boolean removeSala(Sala sala) {
		sala.setCinema(null);
		return this.salas.remove(sala); 
	}
	
	
}
