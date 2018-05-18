package br.ufc.vev.test.sala;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.repositorio.SalaRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaRepositoryTest {
	
	@Autowired
	private SalaRepositorio repository;
	
	@Test
	public void salvaSalaRepositoryTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala(nome, capacidade);
		Sala salaRecebida = repository.save(sala);
		
		assertNotNull(salaRecebida);
	}
	
	@Test
	public void buscaSalaRepositoryTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala(nome, capacidade);
		Sala salaRecebida = repository.save(sala);
		
		int id = salaRecebida.getId();
		
		assertTrue(repository.existsById(id));
	}
	
	@Test
	public void excluiSalaRepositoryTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala salaRecebida = repository.save(new Sala(nome, capacidade));
		
		int id = salaRecebida.getId();
		Sala excluir = repository.getOne(id);
		
		repository.delete(excluir);
		assertFalse(repository.existsById(id));
	}
	@Test
	public void alteraSalaRepositoryTest(){
		//Sala atual
		String nome = "Sala A1";
		int capacidade = 150;
		Sala salaAtual = new Sala(nome, capacidade);
		
		//Sala atualizada
		String nomeUp = "Sala A2";
		int capacidadeUp = 120;
		
		Sala salaAtualizada = repository.save(salaAtual);
		salaAtualizada.setNome(nomeUp);
		System.out.println(salaAtualizada.getNome());
		salaAtualizada.setCapacidade(capacidadeUp);
		
		repository.save(salaAtualizada);
	}
}
