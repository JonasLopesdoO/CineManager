package br.ufc.vev.test.sala;

import br.ufc.vev.service.SalaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.controller.SalaController;

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

		Sala salaRecebida;
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
	
//	@Test
//	public void adicionaSalaControllerTest() {
//		String nome = "Sala A1";
//		int capacidade = 150;
//		
//		Sala salaRecebida = new Sala();
//		salaRecebida = controller.salvaSala(nome, capacidade);
//		assertNotNull(salaRecebida);
//		
//	}
//	
//	@Test
//	public void adicionaSalaComNomeNullControllerTest() {
//		assertNull("Nome não pode ser nulo", controller.salvaSala(null , 120));
//	}
//	
//	@Test
//	public void adicionaSalaComNomeVazioControllerTest() {
//		assertNull("Nome não pode ser vazio", controller.salvaSala("", 100));
//	}
//	
//	@Test
//	public void adicionaSalaComCapacidadeZeroControllerTest() {
//		assertNull("Quantidades de lugares não pode ser menor ou igual a zero", 
//				controller.salvaSala("Sala B1", 0));
//	}
//	
//	@Test
//	public void adicionaSalaComCapacidadeNegativaControllerTest() {
//		assertNull("Quantidades de lugares não pode ser menor ou igual a zero", 
//				controller.salvaSala("Sala B1", -1));
//	}
//	
//	@Test
//	public void buscaSalaControllerTest() {
//		Sala sala = new Sala();
//		sala = controller.salvaSala("Sala A3", 120);
//		
//		assertNotNull(controller.buscaSala(sala.getId()));
//	}
//	
//	@Test 
//	public void buscaSalaComIdZeroControllerTest() {
//		assertNull("Erro ID deve ser maior que zero", controller.buscaSala(0));
//	}
//	
//	@Test 
//	public void buscaSalaComIdNegativoControllerTest() {
//		assertNull("Erro ID não pode ser negativo", controller.buscaSala(-1));
//	}
//	
//	@Test
//	public void excluiSalaControllerTest() {
//		Sala sala = new Sala();
//		sala = controller.salvaSala("Sala A7", 120);
//		
//		assertTrue(controller.excluiSala(sala.getId()));
//		
//	}
//	
//	@Test 
//	public void excluiSalaComIdZeroControllerTest() {
//		assertFalse("Erro ID deve ser maior que zero", controller.excluiSala(0));
//	}
//	
//	@Test 
//	public void excluiSalaComIdNegativoControllerTest() {
//		assertFalse("Erro ID não pode ser negativo", controller.excluiSala(-1));
//	}
//	
//	@Test
//	public void listAllSalaControllerTest() {
//		assertTrue(controller.getAllSala().size() >= 0);
//	}
//	
//	@Test 
//	public void atualizaSalaControllerTest() {
//		Sala sala = new Sala();
//		sala = controller.salvaSala("Sala B1", 100);
//		
//		sala.setNome("Sala B2");
//		sala.setCapacidade(130);
//		
//		assertTrue(controller.atualizaSala(sala));	
//		
//	}
//	
//	@Test 
//	public void atualizaSalaComNomeNuloControllerTest() {
//		Sala sala = new Sala();
//		sala = controller.salvaSala("Sala B1", 100);
//		sala.setNome(null);
//		sala.setCapacidade(130);
//		
//		assertFalse("Nome não pode ser nulo", controller.atualizaSala(sala));	
//		
//	}
//	@Test 
//	public void atualizaSalaComNomeVazioControllerTest() {
//		Sala sala = new Sala();
//		sala = controller.salvaSala("Sala B1", 100);
//		
//		sala.setNome("");
//		sala.setCapacidade(130);
//		
//		assertFalse("Nome não pode ser vazio", controller.atualizaSala(sala));	
//		
//	}
//	@Test 
//	public void atualizaSalaComCapacidadeZeroControllerTest() {
//		Sala sala = new Sala();
//		sala = controller.salvaSala("Sala B1", 100);
//		
//		sala.setNome("Sala B2");
//		sala.setCapacidade(0);
//		
//		assertFalse("Erro ID deve ser maior que zero", controller.atualizaSala(sala));	
//		
//	}
//	
//	@Test 
//	public void atualizaSalaComCapacidadeNegativaControllerTest() {
//		Sala sala = new Sala();
//		sala = controller.salvaSala("Sala B1", 100);
//		
//		sala.setNome("Sala B2");
//		sala.setCapacidade(-1);
//		
//		assertFalse("Erro ID não pode ser negativo", controller.atualizaSala(sala));	
//		
//	}
			
}
