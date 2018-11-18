package br.ufc.vev.test.sala;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.service.SalaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaServiceTest {
	
	@Autowired
	SalaService service;

	@Test
	public void adicionarSalaServiceTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Sala salaRecebida = service.salvarSala(sala);
		
		assertNotNull(salaRecebida);
	}
	
	@Test
	public void adicionarSalaComNomeNuloServiceTest() {
		String nome = null;
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		assertNull(service.salvarSala(sala));
		
	}
	
	@Test
	public void adicionarSalaComNomeVazioServiceTest() {
		String nome = "";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		assertNull(service.salvarSala(sala));
	}
	
	@Test
	public void buscaSalaServiceTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Sala salaRecebida = new Sala(); 
		salaRecebida = service.salvarSala(sala);
	
		assertNotNull(service.buscarSala(salaRecebida.getId()));
	}
	
	@Test
	public void excluiSalaServiceTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Sala salaRecebida = new Sala();
		
		salaRecebida =  service.salvarSala(sala);
		
		service.excluirSala(salaRecebida);
		
		service.buscarSala(salaRecebida.getId());

	}
	
	@Test
	public void alteraSalaRepositoryTest(){
		//Sala atual
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		//Sala atualizada
		String nomeUp = "Sala A2";
		int capacidadeUp = 120;
		Sala salaAtualizada = new Sala();
		
		salaAtualizada = service.salvarSala(sala);
		salaAtualizada.setNome(nomeUp);
		salaAtualizada.setCapacidade(capacidadeUp);
		
		service.atualizaSala(salaAtualizada);
		
		service.buscarSala(salaAtualizada.getId());
	}
	
	@Test
	public void getAllSalaServiceTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		service.salvarSala(sala);
				
		assertTrue(service.getAllSala().size() >= 1);
	}
	
}
