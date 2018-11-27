package br.ufc.vev.test.pessoa;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Diretor;
import br.ufc.vev.controller.DiretorController;
import br.ufc.vev.service.DiretorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiretorControllerTest {
	@Autowired
	DiretorController diretorController;
	
	@Autowired
	DiretorService diretorService;
	
	@Test
	public void indexAtorControllerTest() {
		assertNotNull(diretorController.index());
	}
	
	@Test
	public void formularioAtorControllerTest() {
		assertNotNull(diretorController.formularioDiretor());
	}
	
	@Test
	public void salvarAtorControllerTest() {
		String nome = "José Jucaaa";
		String sobre = "Diretor Caba arretado";
		
		assertNotNull(diretorController.salvaDiretor(nome, sobre));
	}
	
	@Test
	public void buscarAtorControllerTest() {
		String nome = "José Jucaaa";
		String sobre = "Diretor Caba arretado";
		
		Diretor diretor = new Diretor();
		
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
		Diretor diretorRecebido = new Diretor();
		
		diretorRecebido = diretorService.salvarDiretor(diretor);
		
		assertNotNull(diretorController.buscaDiretor(diretorRecebido.getId()));
	}
	
	@Test
	public void excluiAtorControllerTest() {
		String nome = "José Jucaaa";
		String sobre = "Diretor Caba arretado";
		
		Diretor diretor = new Diretor();
		
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
		Diretor diretorRecebido = new Diretor();
		
		diretorRecebido = diretorService.salvarDiretor(diretor);
		
		assertNotNull(diretorController.excluiDiretor(diretorRecebido.getId()));
	}
	
	@Test
	public void getAllAtorControllerTest() {
		assertNotNull(diretorController.getAllDiretores());
	}
	
	@Test
	public void atualizaAtorControllerTest() {
		String nome = "José Jucaaa";
		String sobre = "Diretor Caba arretado";
		
		Diretor diretor = new Diretor();
		
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
		Diretor diretorRecebido = new Diretor();
		
		diretorRecebido = diretorService.salvarDiretor(diretor);
		
		assertNotNull(diretorController.atualizaDiretor(diretorRecebido.getId()));
	}
}
