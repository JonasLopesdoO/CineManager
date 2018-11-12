package br.ufc.vev.test.filme;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.service.AtorService;
import br.ufc.vev.service.DiretorService;
import br.ufc.vev.service.FilmeService;
import br.ufc.vev.service.GeneroService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmeServiceTest {
	@Autowired
	private FilmeService service;
	
	@Autowired
	private AtorService atorService;
	@Autowired
	private DiretorService diretorService;
	@Autowired
	private GeneroService genService;
	
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
	public void buscaFilmePorIdServiceTest() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		Filme FilmeBuscado = new Filme();
		
		FilmeBuscado = service.salvarFilme(Filme);
		
		assertTrue(service.existsById(FilmeBuscado.getId()));
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
	public void getAllFilmeServiceTest() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool 2");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		service.salvarFilme(Filme);
		
		assertTrue(service.getAllFilme().size() >= 1);
	}
	
	@Test
	public void vinculaAtorAoFilme() {
		
		Filme filme = new Filme();
		filme.setNome("DeadPool");
		filme.setSinopse("Filme Top");
		filme.setDuracao(90);
		
		Ator ator = new Ator();
		ator.setNome("Ryan Reynolds");
		ator.setSobre("alguma coisa sobre o ator");
		
		Filme filmeRecebido = service.salvarFilme(filme);
		Ator atorRecebido = atorService.salvarAtor(ator);
		
		service.vinculaAtorAoFilme(filmeRecebido.getId(), atorRecebido.getId());
	}
	
	@Test
	public void desvinculaAtorDoFilme() {
		
		Filme filme = new Filme();
		filme.setNome("DeadPool");
		filme.setSinopse("Filme Top");
		filme.setDuracao(90);
		
		Ator ator = new Ator();
		ator.setNome("Ryan Reynolds");
		ator.setSobre("alguma coisa sobre o ator");
		
		Filme filmeRecebido = service.salvarFilme(filme);
		Ator atorRecebido = atorService.salvarAtor(ator);
		
		service.vinculaAtorAoFilme(filmeRecebido.getId(), atorRecebido.getId());
		service.desvinculaAtorDoFilme(filmeRecebido.getId(), atorRecebido.getId());
	}
	
	@Test
	public void vinculaDiretorAoFilme() {
		Filme filme = new Filme();
		filme.setNome("DeadPool");
		filme.setSinopse("Filme Top");
		filme.setDuracao(90);
		
		Diretor diretor = new Diretor();
		diretor.setNome("Tim Miller");
		diretor.setSobre("Diretor de cinema e especialista em efeitos especiais americano. Tornou-se conhecido por dirigir o filme Deadpool");
		
		Filme filmeRecebido = service.salvarFilme(filme);
		Diretor diretorRecebido = diretorService.salvarDiretor(diretor);
		
		service.vinculaDiretorAoFilme(filmeRecebido.getId(), diretorRecebido.getId());
	}
	
	@Test
	public void desvinculaDiretorDoFilme() {
		Filme filme = new Filme();
		filme.setNome("DeadPool");
		filme.setSinopse("Filme Top");
		filme.setDuracao(90);
		
		Diretor diretor = new Diretor();
		diretor.setNome("Tim Miller");
		diretor.setSobre("Diretor de cinema e especialista em efeitos especiais americano. Tornou-se conhecido por dirigir o filme Deadpool");
		
		Filme filmeRecebido = service.salvarFilme(filme);
		Diretor diretorRecebido = diretorService.salvarDiretor(diretor);
		
		service.vinculaDiretorAoFilme(filmeRecebido.getId(), diretorRecebido.getId());
		service.desvinculaDiretorDoFilme(filmeRecebido.getId(), diretorRecebido.getId());
	}
	
	@Test
	public void vinculaGeneroAoFilme() {
		Filme filme = new Filme();
		filme.setNome("DeadPool");
		filme.setSinopse("Filme Top");
		filme.setDuracao(90);
		
		Genero genero = new Genero();
		genero.setNome("Comedia");
		
		Filme filmeRecebido = service.salvarFilme(filme);
		Genero generoRecebido = genService.salvarGenero(genero);
		
		service.vinculaGeneroAoFilme(filmeRecebido.getId(), generoRecebido.getId());
	}
	
	@Test
	public void desvinculaGeneroDoFilme() {
		Filme filme = new Filme();
		filme.setNome("DeadPool");
		filme.setSinopse("Filme Top");
		filme.setDuracao(90);
		
		Genero genero = new Genero();
		genero.setNome("Comedia");
		
		Filme filmeRecebido = service.salvarFilme(filme);
		Genero generoRecebido = genService.salvarGenero(genero);
		
		service.vinculaGeneroAoFilme(filmeRecebido.getId(), generoRecebido.getId());
		service.desvinculaGeneroDoFilme(filmeRecebido.getId(), generoRecebido.getId());
	}
}
