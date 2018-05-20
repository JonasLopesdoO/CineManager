package br.ufc.vev.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "diretor")
public class Diretor{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String sobre;
	
	public Diretor(String nome, String sobre) {
		this.setNome(nome);
		this.setSobre(sobre);
	}
	
	public Diretor() {
		
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
	
	
}
