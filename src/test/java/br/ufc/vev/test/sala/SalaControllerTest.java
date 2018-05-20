package br.ufc.vev.test.sala;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.controller.SalaController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaControllerTest {

	@Autowired
	SalaController controller;
	
	@Test
	public void adicionaSalaControllerTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala salaRecebida = new Sala();
		salaRecebida = controller.salvaSala(nome, capacidade);
		
		assertNotNull(salaRecebida);
		
	}
	
	@Test
	public void adicionaSalaComNomeNullControllerTest() {
		assertNull("Nome não pode ser nulo", controller.salvaSala(null , 120));
	}
	
	@Test
	public void adicionaSalaComNomeVazioControllerTest() {
		assertNull("Nome não pode ser vazio", controller.salvaSala("", 100));
	}
	
	@Test
	public void adicionaSalaComCapacidadeZeroControllerTest() {
		assertNull("Quantidades de lugares não pode ser menor ou igual a zero", 
				controller.salvaSala("Sala B1", 0));
	}
	
	@Test
	public void adicionaSalaComCapacidadeNegativaControllerTest() {
		assertNull("Quantidades de lugares não pode ser menor ou igual a zero", 
				controller.salvaSala("Sala B1", -1));
	}
	
	@Test
	public void buscaSalaControllerTest() {
		Sala sala = new Sala();
		sala = controller.salvaSala("Sala A3", 120);
		
		assertNotNull(controller.buscaSala(sala.getId()));
	}
	
	@Test 
	public void buscaSalaComIdZeroControllerTest() {
		assertNull("Erro ID deve ser maior que zero", controller.buscaSala(0));
	}
	
	@Test 
	public void buscaSalaComIdNegativoControllerTest() {
		assertNull("Erro ID não pode ser negativo", controller.buscaSala(-1));
	}
	
	@Test
	public void excluiSalaControllerTest() {
		Sala sala = new Sala();
		sala = controller.salvaSala("Sala A7", 120);
		
		assertTrue(controller.excluiSala(sala.getId()));
		
	}
	
	@Test 
	public void excluiSalaComIdZeroControllerTest() {
		assertFalse("Erro ID deve ser maior que zero", controller.excluiSala(0));
	}
	
	@Test 
	public void excluiSalaComIdNegativoControllerTest() {
		assertFalse("Erro ID não pode ser negativo", controller.excluiSala(-1));
	}
	
//	@Test
//	public void adicionaSalaComNomeNuloControllerTest() {
//		String nome = null;
//		int capacidade = 150;
//		
//		controller.salvaSala(nome, capacidade);
//		
//		assertThatException(controller.salvaSala(nome, capacidade));
//		
//	}
}
