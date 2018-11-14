package br.ufc.vev.test.genero;

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

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.repositorio.FilmeRepositorio;
import br.ufc.vev.repositorio.GeneroRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class GeneroRepositorioTest {
	
	@Autowired
	FilmeRepositorio filmeRepositorio;
	
	@Autowired
	GeneroRepositorio generoRepositorio;
	
	@Test 
	public void salvarGeneroRepositoryTest() {
		String nome = "Romance";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero generoRecebido = generoRepositorio.save(genero);
		
		assertNotNull(generoRecebido);
	}
	
	@Test
	public void excluirGeneroRepositoryTest() {
		String nome = "Peeter clarck";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero generoRecebido = generoRepositorio.save(genero);
		
		generoRepositorio.delete(generoRecebido);
		
		assertFalse(generoRepositorio.existsById(generoRecebido.getId()));
	}
	
	@Test 
	public void buscarGeneroRepository() {
		String nome = "Drama";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		Genero generoRecebido = new Genero();
		
		generoRecebido = generoRepositorio.save(genero);
		
		int idGenero = generoRecebido.getId();
		
		assertTrue(generoRepositorio.existsById(idGenero));
	}
	
	@Test
	public void updateGeneroRepository() {
		//genero atual
		String nome = "Terror";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
//		genero atualizado
		String nomeNovo = "Suspense";
		
		Genero generoAtualizado = new Genero();
		
		generoAtualizado = generoRepositorio.save(genero);
		generoAtualizado.setNome(nomeNovo);
		
		Genero generoAtualizadoRecebido = new Genero();
		generoAtualizadoRecebido = generoRepositorio.getOne(generoAtualizado.getId());
		
		assertEquals(generoAtualizado.getId(), generoAtualizadoRecebido.getId());
		assertEquals(generoAtualizado.getNome(), generoAtualizadoRecebido.getNome());
	}
	
	@Test
	public void buscaTodosGeneroRepository() {
		String nome = "Terror";
		
		Genero genero = new Genero();
		genero.setNome(nome);
		
		generoRepositorio.save(genero);
		
		assertNotNull(generoRepositorio.findAll());
	}
	
	@Test
	public void addGeneroFilme() {
		Genero genero = new Genero("Comedia");
		Filme filme = new Filme("FIlmeGeneeeerooooooo", "sinopse teste", 100);
		
		genero.getFilmes().add(filme);
		Filme filmeRecebido = filmeRepositorio.save(filme);

		assertNotNull(filmeRecebido);		
	}
	
	@Test
	public void removerGeneroFilme() {//voltar depois para ver
		Genero genero = new Genero();
		Filme filme = new Filme();
		genero = generoRepositorio.getOne(11);
		filme = filmeRepositorio.getOne(15);
		
		genero.getFilmes().remove(filme);
		
		Filme filmeResponse = new Filme();
		Genero generoResponse = new Genero();
		
		filmeResponse = filmeRepositorio.save(filme);
		generoResponse = generoRepositorio.save(genero);
		
//		generoRepositorio.deleteFilmegeneroes(generoResponse.getId(),filmeResponse.getId());
		
		assertNotNull(filmeResponse);		
		assertNotNull(generoResponse);
	}
}
