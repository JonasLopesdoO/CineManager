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

import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.repositorio.DiretorRepositorio;
import br.ufc.vev.repositorio.FilmeRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)

public class DiretorRepositoryTest {
	
	@Autowired
	DiretorRepositorio diretorRepositorio;
	
	@Autowired
	FilmeRepositorio filmeRepositorio;

	@Test 
	public void salvarDiretorRepositoryTest() {
		String nome = "Julio alcantara";
		String sobre = "Este é um diretoar pistola!";
		
		Diretor diretor = new Diretor();
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
		Diretor diretorRecebido = diretorRepositorio.save(diretor);
		
		assertNotNull(diretorRecebido);
	}
	
	
	@Test
	public void excluirDiretorRepositoryTest() {
		String nome = "Peeter swift";
		String sobre = "Este é um ator muito bom!";
		
		Diretor diretor = new Diretor();
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
		Diretor dietorRecebido = diretorRepositorio.save(diretor);
		
		diretorRepositorio.delete(dietorRecebido);
		
		assertFalse(diretorRepositorio.existsById(dietorRecebido.getId()));
	}
	
	@Test 
	public void buscarDiretorRepository() {
		String nome = "Peeter clarck";
		String sobre = "Este é um diretor muuuuuuuuuuuuuuuito bom!";
		
		Diretor diretor = new Diretor();
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
		Diretor diretorRecebido = new Diretor();
		
		diretorRecebido = diretorRepositorio.save(diretor);
		
		int idDiretor = diretorRecebido.getId();
		
		assertTrue(diretorRepositorio.existsById(idDiretor));
	}
	
	@Test
	public void updateAtorRepository() {
		//ator atual
		String nome = "Peeter london";
		String sobre = "Este é um diretor muito bom!";
		
		Diretor diretor = new Diretor();
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
//		ator atualizado
		String nomeNovo = "José Juca";
		String sobreNovo = "Este é um diretor muito arretado!";
		
		Diretor diretorAtualizado = new Diretor();
		
		diretorAtualizado = diretorRepositorio.save(diretor);
		diretorAtualizado.setNome(nomeNovo);
		diretorAtualizado.setSobre(sobreNovo);
		
		Diretor diretorAtualizadoRecebido = new Diretor();
		diretorAtualizadoRecebido = diretorRepositorio.getOne(diretorAtualizado.getId());
		
		assertEquals(diretorAtualizado.getId(), diretorAtualizadoRecebido.getId());
		assertEquals(diretorAtualizado.getNome(), diretorAtualizadoRecebido.getNome());
//		assertTrue(atorRepositorio.existsById(atorAtualizado.getId()));
	}
	
	@Test
	public void buscaTodosDiretorRepository() {
		String nome = "Peeter check";
		String sobre = "Este é um diretor habilidoso!";
		
		Diretor diretor = new Diretor();
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
		diretorRepositorio.save(diretor);
		
		assertNotNull(diretorRepositorio.findAll());
	}
	
	@Test
	public void addDiretorFilme() {
		Diretor diretor = new Diretor("J K Rowling",  "Diretora mito legends!");
		Filme filme = new Filme("FIlme testaaaandoo", "sinopse testandooooo", 200);
		
		diretor.getFilmes().add(filme);
		Filme filmeRecebido = filmeRepositorio.save(filme);
		
		assertNotNull(filmeRecebido);
		
	}

}
