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
import br.ufc.vev.bean.TipoSala;
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
		
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		sala.setTipo(TipoSala.D2);
		
		Sala salaRecebida = repository.save(sala);
		
		assertNotNull(salaRecebida);
	}
	
	@Test
	public void buscaSalaRepositoryTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		sala.setTipo(TipoSala.D3);
		
		Sala salaRecebida = new Sala(); 
		salaRecebida = repository.save(sala);
		
		int id = salaRecebida.getId();
		
		assertTrue(repository.existsById(id));
	}
	
	@Test
	public void excluiSalaRepositoryTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		sala.setTipo(TipoSala.D2);
		
		Sala salaRecebida = new Sala();
		
		salaRecebida =  repository.save(sala);
		
		repository.delete(salaRecebida);
		
		assertFalse(repository.existsById(salaRecebida.getId()));

	}
	
	@Test
	public void alteraSalaRepositoryTest(){
		//Sala atual
		String nome = "Sala A1";
		int capacidade = 150;
		
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		sala.setTipo(TipoSala.D3);
				
		//Sala atualizada
		String nomeUp = "Sala A2";
		int capacidadeUp = 120;
		Sala salaAtualizada = new Sala();
		
		
		salaAtualizada = repository.save(sala);
		salaAtualizada.setNome(nomeUp);
		salaAtualizada.setCapacidade(capacidadeUp);
		salaAtualizada.setTipo(TipoSala.D2);
		
		repository.save(salaAtualizada);
		
		assertTrue(repository.existsById(salaAtualizada.getId()));
	}
}
