package br.ufc.vev.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Filme {
	@Id
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	
	public Filme() {
		
	}
	
	public Filme(int id) {
		this.id = id;
	}
}
