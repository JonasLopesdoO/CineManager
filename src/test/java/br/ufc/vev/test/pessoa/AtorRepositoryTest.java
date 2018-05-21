package br.ufc.vev.test.pessoa;

import static org.junit.Assert.assertNotNull;

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
import junit.framework.Assert;

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
	public void addAtorFilme() {
		Ator ator = new Ator("GAJHGKGKAG", "Sobre teste");
		Filme filme = new Filme("NGOAGOAQEQ", "sinopse teste", 180);
		
		ator.addFilme(filme);
		Filme filmeResponse = filmeRepositorio.save(filme);

		assertNotNull(filmeResponse);		
	}
	
	@Test
	public void removerAtorFilme() {//voltar depois para ver
		Ator ator = atorRepositorio.getOne(2);
		Filme filme = filmeRepositorio.getOne(2);
		
		ator.removerFilme(filme);
//		Filme filmeResponse = filmeRepositorio.save(filme);
		Ator atorResponse = atorRepositorio.save(ator);

//		assertNotNull(filmeResponse);		
		assertNotNull(atorResponse);
	}
	
}
