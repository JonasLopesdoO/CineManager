package br.ufc.vev.test.genero;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Genero;
import br.ufc.vev.controller.FilmeController;
import br.ufc.vev.controller.GeneroController;
import br.ufc.vev.service.GeneroService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class GeneroControllerTest {

	@Autowired
	GeneroController generoController;
	
	@Autowired
	GeneroService generoService;
	
	@Autowired
	FilmeController filmeController;
	
	@Test
	public void indexGeneroTestController() {
		assertNotNull(generoController.index());
	}
	
	@Test
	public void formularioGeneroTestController() {
		assertNotNull(generoController.formularioGenero());
	}
	
	@Test 
	public void salvarGeneroControllerTest() {
		String nome = "Romance";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		assertNotNull(generoController.salvaGenero(genero));
	}
	
	@Test
	public void excluirGeneroControllerTest() {
		String nome = "Ação";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero generoRecebido = generoService.salvarGenero(genero);
		
		assertNotNull(generoController.excluiGenero(generoRecebido.getId()));
	}
	
	@Test 
	public void buscarGeneroControllerTest() {
		String nome = "Drama";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero generoRecebido = new Genero();
		
		generoRecebido = generoService.salvarGenero(genero);
		
		int idGenero = generoRecebido.getId();
		
		assertNotNull(generoController.buscaGenero(idGenero));
	}
	
	@Test 
	public void existByGeneroControllerTest() {
		String nome = "Drama";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero generoRecebido = new Genero();
		
		generoRecebido = generoService.salvarGenero(genero);
		
		int idGenero = generoRecebido.getId();
		
		assertTrue(generoController.existsByIdGenero(idGenero));
	}
	
	@Test
	public void updateGeneroControllerTest() {
		String nome = "Terror";
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero novoGenero = generoService.salvarGenero(genero);
		
		String nomeNovo = "Suspense";
		novoGenero.setNome(nomeNovo);
		
		assertNotNull(generoController.atualizaGenero(novoGenero.getId()));
	}
	
	@Test
	public void buscaTodosGeneroControllerTest() {
		String nome = "Terror";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		generoController.salvaGenero(genero);
		
		assertNotNull(generoController.getAllGenero());
	}
	
}
