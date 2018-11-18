package br.ufc.vev.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.MappedSuperclass
public abstract class Pessoa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	private String nome;
	private String sobre;
	
	public Pessoa(String nome, String sobre) {
		this.setNome(nome);
		this.setSobre(sobre);
	}
	
	public Pessoa() {
		
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
