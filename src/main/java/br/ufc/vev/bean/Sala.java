package br.ufc.vev.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sala {
	
	@Id
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	
	public Sala(int id) {
		
	}
	
	
}
