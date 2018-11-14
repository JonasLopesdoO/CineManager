package br.ufc.vev.test.pessoa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.repositorio.AtorRepositorio;
import br.ufc.vev.repositorio.FilmeRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class AtorRepositoryTest {

	@Autowired
	AtorRepositorio atorRepositorio;
	
	@Autowired
	FilmeRepositorio filmeRepositorio;
	
	@Test 
	public void salvarAtorRepositoryTest() {
		String nome = "Peeter clarck";
		String sobre = "Este é um ator muito bom!";
		
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setSobre(sobre);
		
		Ator atorRecebido = atorRepositorio.save(ator);
		
		assertNotNull(atorRecebido);
	}
	
	@Test
	public void excluirAtorRepositoryTest() {
		String nome = "Peeter clarck";
		String sobre = "Este é um ator muito bom!";
		
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setSobre(sobre);
		
		Ator atorRecebido = atorRepositorio.save(ator);
		
		atorRepositorio.delete(atorRecebido);
		
		assertFalse(atorRepositorio.existsById(atorRecebido.getId()));
	}
	
	@Test 
	public void buscarAtorRepository() {
		String nome = "Peeter clarck";
		String sobre = "Este é um ator muito bom!";
		
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setSobre(sobre);
		
		Ator atorRecebido = new Ator();
		
		atorRecebido = atorRepositorio.save(ator);
		
		int idAtor = atorRecebido.getId();
		
		assertTrue(atorRepositorio.existsById(idAtor));
	}
	
	@Test
	public void updateAtorRepository() {
		//ator atual
		String nome = "Peeter clarck";
		String sobre = "Este é um ator muito bom!";
		
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setSobre(sobre);
		
//		ator atualizado
		String nomeNovo = "José Raimundo";
		String sobreNovo = "Este é um ator muito arretado!";
		
		Ator atorAtualizado = new Ator();
		
		atorAtualizado = atorRepositorio.save(ator);
		atorAtualizado.setNome(nomeNovo);
		atorAtualizado.setSobre(sobreNovo);

	
		Ator atorAtualizadoRecebido = new Ator();
		atorAtualizadoRecebido = atorRepositorio.save(atorAtualizado);
		atorAtualizadoRecebido = atorRepositorio.getOne(atorAtualizado.getId());
		
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
		
		atorRepositorio.save(ator);
		
		assertNotNull(atorRepositorio.findAll());
		
	}
	
	@Test
	public void addAtorFilme() {
		Ator ator = new Ator("atortesteeeeeeeeeeeeeeee", "Sobre teste");
		Filme filme = new Filme("filmetesteeeeeeeeeeeee", "sinopse teste", 180);
		
		ator.getFilmes().add(filme);
		Filme filmeRecebido = filmeRepositorio.save(filme);

		assertNotNull(filmeRecebido);		
	}
	
}

	