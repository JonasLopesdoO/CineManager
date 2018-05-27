package br.ufc.vev.test.filme;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.service.FilmeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmeServiceTest {
	@Autowired
	private FilmeService service;
	
	
	@Test
	public void salvaFilmeServiceTest() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		assertNotNull(service.salvarFilme(Filme));
	}
	
	@Test 
	public void buscaFilmeServiceTest() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		Filme FilmeBuscado = new Filme();
		
		FilmeBuscado = service.salvarFilme(Filme);
		
		assertNotNull(service.buscarFilme(FilmeBuscado.getId()));
	}
	
	@Test
	public void atualizaFilmeServiceTest() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		Filme FilmeUp = new Filme();
		
		FilmeUp = service.salvarFilme(Filme);
		
		FilmeUp.setNome("DeadPool 2");
		FilmeUp.setSinopse("Filme Top 2");;
		FilmeUp.setDuracao(100);		
		
		assertThat(service.atualizaFilme(FilmeUp));
	}
	
	@Test
	public void excluiFilmeServiceTest() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		Filme FilmeBuscado = new Filme();
		
		FilmeBuscado = service.salvarFilme(Filme);
		service.excluirFilme(FilmeBuscado);		
	}
	
	@Test
	public void getAllFilmeRepositoryTest() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool 2");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		service.salvarFilme(Filme);
		
		assertTrue(service.getAllFilme().size() >= 1);
	}
}
