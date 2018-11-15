package br.ufc.vev.test.sala;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.controller.SalaController;
import br.ufc.vev.service.SalaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaControllerTest {

	@Autowired
	SalaController controller;
	@Autowired
	SalaService service;
	
	@Test
	public void indexSalaController() {
		assertNotNull(controller.index());
	}
	
	@Test
	public void formularioSalaController() {
		assertNotNull(controller.formularioGenero());
	}
	
	@Test
	public void adicionarSalaControllerTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		assertNotNull(controller.salvaSala(sala));
	}
	
	@Test
	public void buscaSalaControllerTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Sala salaRecebida = new Sala(); 
		salaRecebida = service.salvarSala(sala);
	
		assertNotNull(controller.buscaSala(salaRecebida.getId()));
		assertTrue(controller.existsByIdSala(salaRecebida.getId()));
		
	}
	
	@Test
	public void excluiSalaControllerTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Sala salaRecebida = new Sala();
		salaRecebida =  service.salvarSala(sala);
		assertNotNull(controller.excluiSala(salaRecebida.getId()));
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
	
		assertNotNull(controller.atualizaSala(salaAtualizada.getId()));
	}
	
	@Test
	public void getAllSalaController() {
		assertNotNull(controller.getAllSala());
	}
			
}
