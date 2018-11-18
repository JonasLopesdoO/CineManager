package br.ufc.vev.test.pessoa;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.controller.AtorController;
import br.ufc.vev.service.AtorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtorControllerTest {
	
	@Autowired
	AtorController atorController;
	
	@Autowired
	AtorService atorService;
	
	@Test
	public void indexAtorControllerTest() {
		assertNotNull(atorController.index());
	}
	
	@Test
	public void formularioAtorControllerTest() {
		assertNotNull(atorController.formularioAtor());
	}
	
	@Test
	public void salvarAtorControllerTest() {
		String nome = "José Amadeu";
		String sobre = "Caba arretado";
		
		Ator ator = new Ator();
		
		ator.setNome(nome);
		ator.setSobre(sobre);
		
		assertNotNull(atorController.salvaAtor(ator));
	}
	
	@Test
	public void buscarAtorControllerTest() {
		String nome = "Peeter clarck";
		String sobre = "Este é um ator muito bom!";
		
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setSobre(sobre);
		
		Ator atorRecebido = new Ator();
		
		atorRecebido = atorService.salvarAtor(ator);
		
		assertNotNull(atorController.buscaAtor(atorRecebido.getId()));
	}
	
	@Test
	public void excluiAtorControllerTest() {
		String nome = "Peeter clarck";
		String sobre = "Este é um ator muito bom!";
		
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setSobre(sobre);
		
		Ator atorRecebido = new Ator();
		
		atorRecebido = atorService.salvarAtor(ator);
		
		assertNotNull(atorController.excluiAtor(atorRecebido.getId()));
	}
	
	@Test
	public void getAllAtorControllerTest() {
		assertNotNull(atorController.getAllAtor());
	}
	
	@Test
	public void atualizaAtorControllerTest() {
		String nome = "Peeter clarck";
		String sobre = "Este é um ator muito bom!";
		
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setSobre(sobre);
		
		Ator atorRecebido = new Ator();
		
		atorRecebido = atorService.salvarAtor(ator);
		
		assertNotNull(atorController.atualizaAtor(atorRecebido.getId()));
	}
	
}
