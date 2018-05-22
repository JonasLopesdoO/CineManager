package br.ufc.vev.test.filme;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.repositorio.FilmeRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmeRepositorioTest {
	
	@Autowired
	FilmeRepositorio repositorio;
	
	@Test
	public void salvaFilmeRepositoryTest() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		assertNotNull(repositorio.save(Filme));
	}
	
	@Test 
	public void buscaFilmeRepositoryTest() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		Filme FilmeBuscado = new Filme();
		
		FilmeBuscado = repositorio.save(Filme);
		
		assertTrue(repositorio.existsById(FilmeBuscado.getId()));
	}
	
	@Test
	public void atualizaFilmeRepositoryTest() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		Filme FilmeUp = new Filme();
		
		FilmeUp = repositorio.save(Filme);
		
		FilmeUp.setNome("DeadPool 2");
		FilmeUp.setSinopse("Filme Top 2");;
		FilmeUp.setDuracao(100);		
		
		assertThat(repositorio.save(FilmeUp));
	}
	
	@Test
	public void excluiFilmeRepositoryTest() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		Filme FilmeBuscado = new Filme();
		
		FilmeBuscado = repositorio.save(Filme);
		repositorio.delete(FilmeBuscado);
		
		assertFalse(repositorio.existsById(FilmeBuscado.getId()));
	}
	

	

}
