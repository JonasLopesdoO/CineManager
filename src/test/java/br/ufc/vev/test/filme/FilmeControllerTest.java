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
	FilmeController controller;
	@Autowired
	AtorController atorController;
	@Autowired
	DiretorController diretorController;
	@Autowired
	GeneroController generoController;
	
	@Test
	public void salvaFilmeRepositoryTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		assertNotNull(controller.salvaFilme(nome, sinopse, duracao));
	}
	
	@Test 
	public void buscaFilmeRepositoryTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		Filme FilmeBuscado = new Filme();
		
		FilmeBuscado = controller.salvaFilme(nome, sinopse, duracao);
		
		assertNotNull(controller.buscaFilme(FilmeBuscado.getId()));
	}
	
	@Test
	public void atualizaFilmeRepositoryTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		Filme FilmeUp = new Filme();
		FilmeUp = controller.salvaFilme(nome, sinopse, duracao);
		
		FilmeUp.setNome("DeadPool 2");
		FilmeUp.setSinopse("Filme Top 2");;
		FilmeUp.setDuracao(100);		
		
		assertThat(controller.atualizaFilme(FilmeUp));
	}
	
	@Test
	public void excluiFilmeRepositoryTest() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;		
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = controller.salvaFilme(nome, sinopse, duracao);
		controller.excluiFilme(filmeBuscado.getId());
		
		assertNotNull(controller.buscaFilme(filmeBuscado.getId()));
	}
	
	@Test
	public void vinculaAtorAoFilme() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = controller.salvaFilme(nome, sinopse, duracao);
		
		String nomeAtor = "Ryan Reynolds";
		String sobre = "ator canadense. Ele é conhecido por seus papeis em National "
				+ "Lampoon's Van Wilder, Waiting..., The Amityville Horror, Just Friends, "
				+ "Definitely, Maybe, The Proposal e Buried, bem como Wade Wilson/Deadpool "
				+ "interpretando o papel do mercenário tagarela.";
		Ator ator = new Ator();
		ator = atorController.salvaAtor(nomeAtor, sobre);
		
		assertTrue(controller.vinculaAtorAoFilme(filmeBuscado.getId(), ator.getId()));
		
	}
	
	@Test
	public void desvinculaAtorDeFilme() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = controller.salvaFilme(nome, sinopse, duracao);
		
		String nomeAtor = "Ryan Reynolds";
		String sobre = "ator canadense. Ele é conhecido por seus papeis em National "
				+ "Lampoon's Van Wilder, Waiting..., The Amityville Horror, Just Friends, "
				+ "Definitely, Maybe, The Proposal e Buried, bem como Wade Wilson/Deadpool "
				+ "interpretando o papel do mercenário tagarela.";
		Ator ator = new Ator();
		ator = atorController.salvaAtor(nomeAtor, sobre);
		
		assertTrue(controller.vinculaAtorAoFilme(filmeBuscado.getId(), ator.getId()));
		controller.desvinculaAtorDoFilme(filmeBuscado.getId(), ator.getId());
		assertFalse(controller.atorPertenceAoFilme(filmeBuscado.getId(), ator.getId()));
	}
	
	@Test
	public void vinculaDiretorAoFilme() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = controller.salvaFilme(nome, sinopse, duracao);
		
		String nomeDir = "Tim Miller";
		String sobre = "diretor de cinema e especialista em efeitos especiais americano."
				+ " Tornou-se conhecido por dirigir o filme Deadpool, de 2016, sendo "
				+ "também cofundador e diretor criativo da Blur Studio, uma empresa "
				+ "especializada em efeitos especiais.";
		
		Diretor diretor = new Diretor();
		diretor = diretorController.salvaDiretor(nomeDir, sobre);
		
		assertTrue(controller.vinculaAtorAoFilme(filmeBuscado.getId(), diretor.getId()));
		
	}
	
	@Test
	public void desvinculaDiretorDeFilme() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = controller.salvaFilme(nome, sinopse, duracao);
		
		String nomeDir = "Tim Miller";
		String sobre = "diretor de cinema e especialista em efeitos especiais americano."
				+ " Tornou-se conhecido por dirigir o filme Deadpool, de 2016, sendo "
				+ "também cofundador e diretor criativo da Blur Studio, uma empresa "
				+ "especializada em efeitos especiais.";
		
		Diretor diretor = new Diretor();
		diretor = diretorController.salvaDiretor(nomeDir, sobre);
		
		assertTrue(controller.vinculaAtorAoFilme(filmeBuscado.getId(), diretor.getId()));
		controller.desvinculaDiretorDoFilme(filmeBuscado.getId(), diretor.getId());
		assertFalse(controller.diretorPertenceAoFilme(filmeBuscado.getId(), diretor.getId()));
	}
	
	@Test
	public void vinculaGeneroAoFilme() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = controller.salvaFilme(nome, sinopse, duracao);
		
		String nomeGen = "Fantasia";
		
		Genero genero = new Genero();
		genero = generoController.salvaGenero(nomeGen);
		
		assertTrue(controller.vinculaAtorAoFilme(filmeBuscado.getId(), genero.getId()));
		
	}
	
	@Test
	public void desvinculaGeneroDeFilme() {
		String nome = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		Filme filmeBuscado = new Filme();
		filmeBuscado = controller.salvaFilme(nome, sinopse, duracao);
		
		String nomeGen = "Fantasia";
		
		Genero genero = new Genero();
		genero = generoController.salvaGenero(nomeGen);
		
		assertTrue(controller.vinculaAtorAoFilme(filmeBuscado.getId(), genero.getId()));
		controller.desvinculaGeneroDoFilme(filmeBuscado.getId(), genero.getId());
		assertFalse(controller.generoPertenceAoFilme(filmeBuscado.getId(), genero.getId()));
	}
	
}
