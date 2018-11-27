package br.ufc.vev.test.filme;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.controller.FilmeController;
import br.ufc.vev.controller.GeneroController;
import br.ufc.vev.service.AtorService;
import br.ufc.vev.service.DiretorService;
import br.ufc.vev.service.FilmeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmeControllerTest {
	
	@Autowired
	FilmeController filmeController;
	@Autowired
	FilmeService filmeService;
	@Autowired
	AtorService atorService;
	@Autowired
	DiretorService diretorService;
	@Autowired
	GeneroController generoController;
	
	@Test
	public void indexFilmeControllerTest() {
		assertNotNull(filmeController.index());
	}
	
	@Test
	public void formularioFilmeControllerTest() {
		assertNotNull(filmeController.formularioFilme());
	}
	
	@Test
	public void salvaFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		assertNotNull(filmeController.salvaFilme(nome, sinopse, duracao));
	}
	
	@Test
	public void detalhesFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		Filme filme = new Filme(nome, sinopse, duracao);
		Filme filmeSalvo = filmeService.salvarFilme(filme);
		assertNotNull(filmeController.detalhesFilme(filmeSalvo.getId()));
	}
	
//	@Test
//	public void salvaFilmeComNomeVazioControllerTest() {
//		String nome = "";
//		String sinopse = "Filme Top";
//		int duracao = 90;		
//		
//		Filme filme = new Filme(nome, sinopse, duracao);
//		assertNotNull(filmeController.salvaFilme(filme));
//	}
	
//	@Test
//	public void salvaFilmeComNomeNuloControllerTest() {
//		String nome = null;
//		String sinopse = "Filme Top";
//		int duracao = 90;		
//		
//		Filme filme = new Filme(nome, sinopse, duracao);
//		assertNotNull(filmeController.salvaFilme(filme));
//	}
	
//	@Test
//	public void salvaFilmeComSinopseVaziaControllerTest() {
//		String nome = "DeadPool";
//		String sinopse = "";
//		int duracao = 90;		
//		
//		Filme filme = new Filme(nome, sinopse, duracao);
//		assertNotNull(filmeController.salvaFilme(filme));
//	}
	
//	@Test
//	public void salvaFilmeComSinopseNulaControllerTest() {
//		String nome = "DeadPool";
//		String sinopse = null;
//		int duracao = 90;		
//		
//		Filme filme = new Filme(nome, sinopse, duracao);
//		assertNotNull(filmeController.salvaFilme(filme));
//	}
	
//	@Test
//	public void salvaFilmeComDuracaoNegativaControllerTest() {
//		String nome = "DeadPool";
//		String sinopse = "Filme Top";
//		int duracao = -1;		
//		
//		Filme filme = new Filme(nome, sinopse, duracao);
//		assertNotNull(filmeController.salvaFilme(filme));
//	}
	
//	@Test
//	public void salvaFilmeComDuracaoZeroControllerTest() {
//		String nome = "DeadPool";
//		String sinopse = "Filme Top";
//		int duracao = 0;		
//		
//		Filme filme = new Filme(nome, sinopse, duracao);
//		assertNotNull(filmeController.salvaFilme(filme));
//	}
	
	@Test 
	public void buscaFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		Filme filme = new Filme(nome, sinopse, duracao);
		
		Filme filmeBuscado = new Filme();		
		filmeBuscado = filmeService.salvarFilme(filme);
		
		assertNotNull(filmeController.buscaFilme(filmeBuscado.getId()));
	}
	
//	@Test 
//	public void buscaFilmePassandoIdZeroControllerTest() {
//		assertNull(filmeController.buscaFilme(0));
//	}
//	
//	@Test 
//	public void buscaFilmePassandoIdNegativoControllerTest() {
//		assertNull(filmeController.buscaFilme(-1));
//	}
	
	@Test
	public void atualizaFilmeControllerTest() {
		Filme filme = new Filme("DeadPool", "Filme Top", 90);
				
		Filme filmeUp = new Filme();
		
		filmeUp = filmeService.salvarFilme(filme);
		
		filmeUp.setNome("DeadPool 2");
		filmeUp.setSinopse("Filme Top 2");
		filmeUp.setDuracao(100);		
		
		assertNotNull(filmeController.atualizaFilme(filmeUp.getId()));
	}
	
//	@Test
//	public void atualizaFilmeComNomeVazioControllerTest() {
//		String nome = "DeadPool";
//		String sinopse = "Filme Top";
//		int duracao = 90;		
//		
//		Filme FilmeUp = new Filme();
//		Filme filme = new Filme(nome, sinopse, duracao);
//		assertNotNull(filmeController.salvaFilme(filme));
//		
//		FilmeUp.setNome("");
//		FilmeUp.setSinopse("Filme Top 2");
//		FilmeUp.setDuracao(100);		
//		
//		assertNull(filmeController.atualizaFilme(FilmeUp));
//	}
//	
//	@Test
//	public void atualizaFilmeComNomeNuloControllerTest() {
//		String nome = "DeadPool";
//		String sinopse = "Filme Top";
//		int duracao = 90;		
//		
//		Filme FilmeUp = new Filme();
//		FilmeUp = filmeController.salvaFilme(nome, sinopse, duracao);
//		
//		FilmeUp.setNome(null);
//		FilmeUp.setSinopse("Filme Top 2");
//		FilmeUp.setDuracao(100);		
//		
//		assertNull(filmeController.atualizaFilme(FilmeUp));
//	}
//	
//	@Test
//	public void atualizaFilmeComSinopseVazioControllerTest() {
//		String nome = "DeadPool";
//		String sinopse = "Filme Top";
//		int duracao = 90;		
//		
//		Filme FilmeUp = new Filme();
//		FilmeUp = filmeController.salvaFilme(nome, sinopse, duracao);
//		
//		FilmeUp.setNome("DeadPoll 2");
//		FilmeUp.setSinopse("");
//		FilmeUp.setDuracao(100);		
//		
//		assertNull(filmeController.atualizaFilme(FilmeUp));
//	}
//	
//	@Test
//	public void atualizaFilmeComSinopseNuloControllerTest() {
//		String nome = "DeadPool";
//		String sinopse = "Filme Top";
//		int duracao = 90;		
//		
//		Filme FilmeUp = new Filme();
//		FilmeUp = filmeController.salvaFilme(nome, sinopse, duracao);
//		
//		FilmeUp.setNome("DeadPool 2");
//		FilmeUp.setSinopse(null);
//		FilmeUp.setDuracao(100);		
//		
//		assertNull(filmeController.atualizaFilme(FilmeUp));
//	}
//	
//	@Test
//	public void excluiFilmeControllerTest() {
//		String nome = "DeadPool";
//		String sinopse = "Filme Top";
//		int duracao = 90;		
//		
//		Filme filmeBuscado = new Filme();
//		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
//		filmeController.excluiFilme(filmeBuscado.getId());
//		
//		assertFalse(filmeController.existsByIdFilme(filmeBuscado.getId()));
//	}
//	
//	@Test
//	public void excluiFilmePassandoIdZeroControllerTest() {
//		assertFalse(filmeController.excluiFilme(0));
//	}
//	
//	@Test
//	public void excluiFilmePassandoIdNegativoControllerTest() {
//		assertFalse(filmeController.excluiFilme(-2));
//	}
//	
	@Test
	public void vinculaEDesvinculaAtorAoFilmeControllerTest() {
		Filme filme = new Filme("DeadPool", "Filme Top", 90);
		Filme filmeSalvo = filmeService.salvarFilme(filme);
		
		String nomeAtor = "Ryan Reynolds";
		String sobre = "ator canadense. Ele é conhecido por seus papeis em National "
				+ "Lampoon's Van Wilder, Waiting..., The Amityville Horror, Just Friends, "
				+ "Definitely, Maybe, The Proposal e Buried, bem como Wade Wilson/Deadpool "
				+ "interpretando o papel do mercenário tagarela.";
		Ator ator = new Ator();
		ator.setNome(nomeAtor);
		ator.setSobre(sobre);
		
		Ator atorSalvo = atorService.salvarAtor(ator);
		
		assertNotNull(filmeController.vincularAtorAoFilme(filmeSalvo.getId(), atorSalvo.getId()));
		assertNotNull(filmeController.desvinculaAtorDoFilme(filmeSalvo.getId(), atorSalvo.getId()));
	}
	
	@Test
	public void vinculaEDesvinculaDiretorAoFilmeControllerTest() {
		Filme filme = new Filme("DeadPool", "Filme Top", 90);
		Filme filmeSalvo = filmeService.salvarFilme(filme);
		
		String nomeDir = "Tim Miller";
		String sobre = "diretor do Deadpool";
		
		Diretor diretor = new Diretor();
		diretor.setNome(nomeDir);
		diretor.setSobre(sobre);
		Diretor diretorSalvo = diretorService.salvarDiretor(diretor); 
		
		assertNotNull(filmeController.vincularDiretorAoFilme(filmeSalvo.getId(), diretorSalvo.getId()));
		assertNotNull(filmeController.desvinculaDiretorDoFilme(filmeSalvo.getId(), diretorSalvo.getId()));
	}

	@Test
	public void vinculaEDesvinculaGeneroAoFilmeControllerTest() {
		Filme filme = new Filme("DeadPool", "Filme Top", 90);
		Filme filmeSalvo = filmeService.salvarFilme(filme);
		
		String nomeGen = "Fantasia";
		
		Genero genero = new Genero();
		
		genero = (Genero) generoController.salvaGenero(nomeGen).getModel().get("generoRetorno");
		
		assertNotNull(filmeController.vincularGeneroAoFilme(filmeSalvo.getId(), genero.getId()));
		assertNotNull(filmeController.desvinculaGeneroDoFilme(filmeSalvo.getId(), genero.getId()));
	}
//	
//	@Test
//	public void desvinculaGeneroDeFilmeControllerTest() {
//		String nome = "DeadPool";
//		String sinopse = "Filme Top";
//		int duracao = 90;
//		
//		Filme filmeBuscado = new Filme();
//		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
//		
//		String nomeGen = "Fantasia";
//		
//		Genero genero = new Genero();
//		genero.setNome(nomeGen);
//		
//		genero = (Genero) generoController.salvaGenero(genero).getModel().get("generoRetorno");
//		
//		assertTrue(filmeController.vinculaAtorAoFilme(filmeBuscado.getId(), genero.getId()));
//		filmeController.desvinculaGeneroDoFilme(filmeBuscado.getId(), genero.getId());
//		assertFalse(filmeController.generoPertenceAoFilme(filmeBuscado.getId(), genero.getId()));
//	}
	
}
