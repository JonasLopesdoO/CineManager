package br.ufc.vev.test.genero;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.service.FilmeService;
import br.ufc.vev.service.GeneroService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class GeneroServiceTest {

	@Autowired 
	GeneroService generoService;
	
	@Autowired 
	FilmeService filmeService;
	
	@Test 
	public void salvarGeneroRepositoryTest() {
		String nome = "Romance";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero generoRecebido = generoService.salvarGenero(genero);
		
		assertNotNull(generoRecebido);
	}
	
	@Test
	public void excluirGeneroRepositoryTest() {
		String nome = "Ação";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero generoRecebido = generoService.salvarGenero(genero);
		
		generoService.excluirGenero(generoRecebido);
		
		assertNotNull(generoRecebido);
	}
	
	@Test 
	public void buscarGeneroRepository() {
		String nome = "Drama";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero generoRecebido = new Genero();
		
		generoRecebido = generoService.salvarGenero(genero);
		
		int idGenero = generoRecebido.getId();
		
		assertNotNull(generoService.buscarGenero(idGenero));
	}
	
	@Test 
	public void buscaGeneroRepository() {
		String nome = "Drama";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero generoRecebido = new Genero();
		
		generoRecebido = generoService.salvarGenero(genero);
		
		int idGenero = generoRecebido.getId();
		
		assertTrue(generoService.buscaGenero(idGenero));
	}
	
	@Test
	public void updateGeneroRepository() {
		String nome = "Terror";
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero novoGenero = generoService.salvarGenero(genero);
		
		String nomeNovo = "Suspense";
		novoGenero.setNome(nomeNovo);
		
		Genero novoGen = new Genero();
		novoGen = generoService.atualizaGenero(novoGenero);
		
		assertEquals(novoGenero, novoGen);
	}
	
	@Test
	public void buscaTodosGeneroRepository() {
		String nome = "Terror";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		generoService.salvarGenero(genero);
		
		assertNotNull(generoService.getAllGenero());
	}
	
	@Test
	public void addGeneroFilme() {
		Genero genero = new Genero("Comedia");
		Filme filme = new Filme("FIlmeGeneeeerooooooo", "sinopse teste", 100);
		
		genero.addFilme(filme);
		Filme filmeRecebido = filmeService.salvarFilme(filme);

		assertNotNull(filmeRecebido);		
	}
	
	@Test
	public void removerGeneroFilme() {//voltar depois para ver
		Genero genero = new Genero();
		Filme filme = new Filme();
		genero = generoService.buscarGenero(11);
		filme = filmeService.buscarFilme(15);
		
		genero.removerFilme(filme);
		
		Filme filmeResponse = new Filme();
		Genero generoResponse = new Genero();
		
		filmeResponse = filmeService.salvarFilme(filme);
		generoResponse = generoService.salvarGenero(genero);
				
		assertNotNull(filmeResponse);		
		assertNotNull(generoResponse);
	}

}
