package br.ufc.vev.test.pessoa;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.service.AtorService;
import br.ufc.vev.service.FilmeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class AtorServiceTest {
	
	@Autowired 
	AtorService atorService;
	
	@Autowired 
	FilmeService filmeService;
	
	@Test
	public void adicionarAtorServiceTest() {
		String nome = "José Amadeu";
		String sobre = "Caba arretado";
		
		Ator ator = new Ator();
		
		ator.setNome(nome);
		ator.setSobre(sobre);
		
		Ator atorRecebido = atorService.salvarAtor(ator);
		
		assertNotNull(atorRecebido);
	}
	
	@Test
	public void excluirAtorRepositoryTest() {
		String nome = "Peeter clarck";
		String sobre = "Este é um ator muito bommmmmmmmm!";
		
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setSobre(sobre);
		
		Ator atorRecebido = atorService.salvarAtor(ator);
		
		atorService.excluirAtor(atorRecebido);
		
		assertNotNull(atorRecebido);
	}
	
	@Test 
	public void buscarAtorServiceTest() {
		String nome = "Peeter clarck";
		String sobre = "Este é um ator muito bom!";
		
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setSobre(sobre);
		
		Ator atorRecebido = new Ator();
		
		atorRecebido = atorService.salvarAtor(ator);
		
		int idAtor = atorRecebido.getId();
		
		assertNotNull(atorService.buscarAtor(idAtor));
	}
	
	@Test
	public void updateAtorRepository() {
		//ator atual
		String nome = "Peeter clarckeeeeeerr";
		String sobre = "Este é um ator muito bom!";
		
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setSobre(sobre);
		
//		ator atualizado
		String nomeNovo = "José Raimundo";
		String sobreNovo = "Este é um ator muito arretado!";
		
		Ator atorAtualizado = new Ator();
		
		atorAtualizado = atorService.salvarAtor(ator);
		atorAtualizado.setNome(nomeNovo);
		atorAtualizado.setSobre(sobreNovo);
		
		Ator atorAtualizadoRecebido = new Ator();
		atorAtualizadoRecebido = atorService.atualizaAtor(atorAtualizado);
		
		assertEquals(atorAtualizado.getId(), atorAtualizadoRecebido.getId());
		assertEquals(atorAtualizado.getNome(), atorAtualizadoRecebido.getNome());
//		assertTrue(atorRepositorio.existsById(atorAtualizado.getId()));
	}
	
	@Test
	public void buscaTodosAtorRepository() {
		String nome = "Peeter clarck";
		String sobre = "Este é um ator muito bom!";
		
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setSobre(sobre);
		
		atorService.salvarAtor(ator);
		
		assertNotNull(atorService.getAllAtor());

	}

	@Test
	public void addAtorFilme() {
		Ator ator = new Ator("atortesteeeeeeeeeeeeeeee", "Sobre teste");
		Filme filme = new Filme("filmetesteeeeeeeeeeeee", "sinopse teste", 180);
		
		ator.getFilmes().add(filme);
		Filme filmeRecebido = filmeService.salvarFilme(filme);

		assertNotNull(filmeRecebido);		
	}
	
	@Test
	public void removerAtorFilme() {//voltar depois para ver
		Ator ator = new Ator();
		Filme filme = new Filme();
		ator = atorService.buscarAtor(20);
		filme = filmeService.buscarFilme(9);
		
		ator.getFilmes().remove(filme);
		
		Filme filmeResponse = new Filme();
		Ator atorResponse = new Ator();
		
		filmeResponse = filmeService.salvarFilme(filme);
		atorResponse = atorService.salvarAtor(ator);
		
//		atorRepositorio.deleteFilmeAtores(atorResponse.getId(),filmeResponse.getId());
		
		assertNotNull(filmeResponse);		
		assertNotNull(atorResponse);
	}

}
