package br.ufc.vev.test.filme;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.controller.AtorController;
import br.ufc.vev.controller.DiretorController;
import br.ufc.vev.controller.FilmeController;
import br.ufc.vev.controller.GeneroController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmeControllerTest {
	
	@Autowired
	FilmeController filmeController;
	@Autowired
	AtorController atorController;
	@Autowired
	DiretorController diretorController;
	@Autowired
	GeneroController generoController;
	
	@Test
	public void salvaFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		assertNotNull(filmeController.salvaFilme(nome, sinopse, duracao));
	}
	
	@Test
	public void salvaFilmeComNomeVazioControllerTest() {
		String nome = "";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		assertNull(filmeController.salvaFilme(nome, sinopse, duracao));
	}
	
	@Test
	public void salvaFilmeComNomeNuloControllerTest() {
		String nome = null;
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		assertNull(filmeController.salvaFilme(nome, sinopse, duracao));
	}
	
	@Test
	public void salvaFilmeComSinopseVaziaControllerTest() {
		String nome = "DeadPool";
		String sinopse = "";
		int duracao = 90;		
		
		assertNull(filmeController.salvaFilme(nome, sinopse, duracao));
	}
	
	@Test
	public void salvaFilmeComSinopseNulaControllerTest() {
		String nome = "DeadPool";
		String sinopse = null;
		int duracao = 90;		
		
		assertNull(filmeController.salvaFilme(nome, sinopse, duracao));
	}
	
	@Test
	public void salvaFilmeComDuracaoNegativaControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = -1;		
		
		assertNull(filmeController.salvaFilme(nome, sinopse, duracao));
	}
	
	@Test
	public void salvaFilmeComDuracaoZeroControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 0;		
		
		assertNull(filmeController.salvaFilme(nome, sinopse, duracao));
	}
	
	@Test 
	public void buscaFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		Filme FilmeBuscado = new Filme();
		
		FilmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		
		assertNotNull(filmeController.buscaFilme(FilmeBuscado.getId()));
	}
	
	@Test 
	public void buscaFilmePassandoIdZeroControllerTest() {
		assertNull(filmeController.buscaFilme(0));
	}
	
	@Test 
	public void buscaFilmePassandoIdNegativoControllerTest() {
		assertNull(filmeController.buscaFilme(-1));
	}
	
	@Test
	public void atualizaFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		Filme FilmeUp = new Filme();
		FilmeUp = filmeController.salvaFilme(nome, sinopse, duracao);
		
		FilmeUp.setNome("DeadPool 2");
		FilmeUp.setSinopse("Filme Top 2");
		FilmeUp.setDuracao(100);		
		
		assertNotNull(filmeController.atualizaFilme(FilmeUp));
	}
	
	@Test
	public void atualizaFilmeComNomeVazioControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		Filme FilmeUp = new Filme();
		FilmeUp = filmeController.salvaFilme(nome, sinopse, duracao);
		
		FilmeUp.setNome("");
		FilmeUp.setSinopse("Filme Top 2");
		FilmeUp.setDuracao(100);		
		
		assertNull(filmeController.atualizaFilme(FilmeUp));
	}
	
	@Test
	public void atualizaFilmeComNomeNuloControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		Filme FilmeUp = new Filme();
		FilmeUp = filmeController.salvaFilme(nome, sinopse, duracao);
		
		FilmeUp.setNome(null);
		FilmeUp.setSinopse("Filme Top 2");
		FilmeUp.setDuracao(100);		
		
		assertNull(filmeController.atualizaFilme(FilmeUp));
	}
	
	@Test
	public void atualizaFilmeComSinopseVazioControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		Filme FilmeUp = new Filme();
		FilmeUp = filmeController.salvaFilme(nome, sinopse, duracao);
		
		FilmeUp.setNome("DeadPoll 2");
		FilmeUp.setSinopse("");
		FilmeUp.setDuracao(100);		
		
		assertNull(filmeController.atualizaFilme(FilmeUp));
	}
	
	@Test
	public void atualizaFilmeComSinopseNuloControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		Filme FilmeUp = new Filme();
		FilmeUp = filmeController.salvaFilme(nome, sinopse, duracao);
		
		FilmeUp.setNome("DeadPool 2");
		FilmeUp.setSinopse(null);
		FilmeUp.setDuracao(100);		
		
		assertNull(filmeController.atualizaFilme(FilmeUp));
	}
	
	@Test
	public void excluiFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		filmeController.excluiFilme(filmeBuscado.getId());
		
		assertFalse(filmeController.existsByIdFilme(filmeBuscado.getId()));
	}
	
	@Test
	public void excluiFilmePassandoIdZeroControllerTest() {
		assertFalse(filmeController.excluiFilme(0));
	}
	
	@Test
	public void excluiFilmePassandoIdNegativoControllerTest() {
		assertFalse(filmeController.excluiFilme(-2));
	}
	
	@Test
	public void vinculaAtorAoFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		
		String nomeAtor = "Ryan Reynolds";
		String sobre = "ator canadense. Ele é conhecido por seus papeis em National "
				+ "Lampoon's Van Wilder, Waiting..., The Amityville Horror, Just Friends, "
				+ "Definitely, Maybe, The Proposal e Buried, bem como Wade Wilson/Deadpool "
				+ "interpretando o papel do mercenário tagarela.";
		Ator ator = new Ator();
		ator.setNome(nomeAtor);
		ator.setSobre(sobre);
		
		ator = (Ator) atorController.salvaAtor(ator).getModel().get("atorRetorno");
		
		assertTrue(filmeController.vinculaAtorAoFilme(filmeBuscado.getId(), ator.getId()));
		
	}
	
	@Test
	public void vinculaAtorJaVinculadoAoFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		
		String nomeAtor = "Ryan Reynolds";
		String sobre = "ator canadense. Ele é conhecido por seus papeis em National "
				+ "Lampoon's Van Wilder, Waiting..., The Amityville Horror, Just Friends, "
				+ "Definitely, Maybe, The Proposal e Buried, bem como Wade Wilson/Deadpool "
				+ "interpretando o papel do mercenário tagarela.";
		Ator ator = new Ator();
		ator.setNome(nomeAtor);
		ator.setSobre(sobre);
		
		ator = (Ator) atorController.salvaAtor(ator).getModel().get("atorRetorno");
		
		assertTrue(filmeController.vinculaAtorAoFilme(filmeBuscado.getId(), ator.getId()));
		assertFalse(filmeController.vinculaAtorAoFilme(filmeBuscado.getId(), ator.getId()));
		
	}
	
	@Test
	public void desvinculaAtorVinculadoAoFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		
		String nomeAtor = "Ryan Reynolds";
		String sobre = "ator canadense. Ele é conhecido por seus papeis em National "
				+ "Lampoon's Van Wilder, Waiting..., The Amityville Horror, Just Friends, "
				+ "Definitely, Maybe, The Proposal e Buried, bem como Wade Wilson/Deadpool "
				+ "interpretando o papel do mercenário tagarela.";
		Ator ator = new Ator();
		ator.setNome(nomeAtor);
		ator.setSobre(sobre);
		
		ator = (Ator) atorController.salvaAtor(ator).getModel().get("atorRetorno");
		
		assertTrue(filmeController.vinculaAtorAoFilme(filmeBuscado.getId(), ator.getId()));
		filmeController.desvinculaAtorDoFilme(filmeBuscado.getId(), ator.getId());
		assertFalse(filmeController.atorPertenceAoFilme(filmeBuscado.getId(), ator.getId()));
	}
	
	@Test
	public void desvinculaAtorNaoVinculadoAoFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		
		String nomeAtor = "Ryan Reynolds";
		String sobre = "ator canadense. Ele é conhecido por seus papeis em National "
				+ "Lampoon's Van Wilder, Waiting..., The Amityville Horror, Just Friends, "
				+ "Definitely, Maybe, The Proposal e Buried, bem como Wade Wilson/Deadpool "
				+ "interpretando o papel do mercenário tagarela.";
		Ator ator = new Ator();
		ator.setNome(nomeAtor);
		ator.setSobre(sobre);
		
		ator = (Ator) atorController.salvaAtor(ator).getModel().get("atorRetorno");
		
		assertFalse(filmeController.desvinculaAtorDoFilme(filmeBuscado.getId(), ator.getId()));
		
	}
	
	@Test
	public void vinculaDiretorAoFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		
		String nomeDir = "Tim Miller";
		String sobre = "diretor de cinema e especialista em efeitos especiais americano."
				+ " Tornou-se conhecido por dirigir o filme Deadpool, de 2016, sendo "
				+ "também cofundador e diretor criativo da Blur Studio, uma empresa "
				+ "especializada em efeitos especiais.";
		
		Diretor diretor = new Diretor();
		diretor = diretorController.salvaDiretor(nomeDir, sobre);
		
		assertTrue(filmeController.vinculaDiretorAoFilme(filmeBuscado.getId(), diretor.getId()));
		
	}
	
	@Test
	public void vinculaDiretorJaVinculadoAoFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		
		String nomeDir = "Tim Miller";
		String sobre = "diretor de cinema e especialista em efeitos especiais americano."
				+ " Tornou-se conhecido por dirigir o filme Deadpool, de 2016, sendo "
				+ "também cofundador e diretor criativo da Blur Studio, uma empresa "
				+ "especializada em efeitos especiais.";
		
		Diretor diretor = new Diretor();
		diretor = diretorController.salvaDiretor(nomeDir, sobre);
		
		assertTrue(filmeController.vinculaDiretorAoFilme(filmeBuscado.getId(), diretor.getId()));
		assertFalse(filmeController.vinculaDiretorAoFilme(filmeBuscado.getId(), diretor.getId()));
		
	}
	
	@Test
	public void desvinculaDiretorVinculadoAoFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		
		String nomeDir = "Tim Miller";
		String sobre = "diretor de cinema e especialista em efeitos especiais americano."
				+ " Tornou-se conhecido por dirigir o filme Deadpool, de 2016, sendo "
				+ "também cofundador e diretor criativo da Blur Studio, uma empresa "
				+ "especializada em efeitos especiais.";
		
		Diretor diretor = new Diretor();
		diretor = diretorController.salvaDiretor(nomeDir, sobre);
		
		assertTrue(filmeController.vinculaDiretorAoFilme(filmeBuscado.getId(), diretor.getId()));
		filmeController.desvinculaDiretorDoFilme(filmeBuscado.getId(), diretor.getId());
		assertFalse(filmeController.diretorPertenceAoFilme(filmeBuscado.getId(), diretor.getId()));
	}
	
	@Test
	public void desvinculaDiretorNaoVinculadoAoFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		
		String nomeDir = "Tim Miller";
		String sobre = "diretor de cinema e especialista em efeitos especiais americano."
				+ " Tornou-se conhecido por dirigir o filme Deadpool, de 2016, sendo "
				+ "também cofundador e diretor criativo da Blur Studio, uma empresa "
				+ "especializada em efeitos especiais.";
		
		Diretor diretor = new Diretor();
		diretor = diretorController.salvaDiretor(nomeDir, sobre);
		
		assertFalse(filmeController.desvinculaDiretorDoFilme(filmeBuscado.getId(), diretor.getId()));
		
	}
	
	@Test
	public void vinculaGeneroAoFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		
		String nomeGen = "Fantasia";
		
		Genero genero = new Genero();
		genero.setNome(nomeGen);
		genero = (Genero) generoController.salvaGenero(genero).getModel().get("generoRetorno");
		
		assertTrue(filmeController.vinculaAtorAoFilme(filmeBuscado.getId(), genero.getId()));
		
	}
	
	@Test
	public void desvinculaGeneroDeFilmeControllerTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = filmeController.salvaFilme(nome, sinopse, duracao);
		
		String nomeGen = "Fantasia";
		
		Genero genero = new Genero();
		genero.setNome(nomeGen);
		
		genero = (Genero) generoController.salvaGenero(genero).getModel().get("generoRetorno");
		
		assertTrue(filmeController.vinculaAtorAoFilme(filmeBuscado.getId(), genero.getId()));
		filmeController.desvinculaGeneroDoFilme(filmeBuscado.getId(), genero.getId());
		assertFalse(filmeController.generoPertenceAoFilme(filmeBuscado.getId(), genero.getId()));
	}
	
}
