package br.ufc.vev.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sala {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private TipoSala tipo;
	private int capacidade;
	
	
	public Integer getId() {
		return id;
	}
	
	public Sala(int id) {
		this.id = id;
	}
	
	public Sala() {
		
	}
	
	
}
