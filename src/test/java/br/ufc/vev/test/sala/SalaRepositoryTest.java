package br.ufc.vev.test.sala;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.repositorio.SalaRepositorio;

public class SalaRepositoryTest {
	
	@Autowired
	private SalaRepositorio repository;
	
	@Test
	public void salvaSalaRepositoryTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala(nome, capacidade);
		Sala salaRecebida = repository.save(sala);
		
		int id = salaRecebida.getId();
		System.out.println("ID: "+id);
		
		assertNotNull(salaRecebida);
	}
	
	@Test
	public void buscaSalaRepositoryTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala(nome, capacidade);
		Sala salaRecebida = repository.save(sala);
		
		int id = salaRecebida.getId();
		System.out.println("ID: "+id);
		
		assertNotNull(repository.getOne(id));
	}
}
