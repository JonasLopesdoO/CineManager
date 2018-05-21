package br.ufc.vev.test.pessoa;

import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Diretor;
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
	public void addDiretorFilme() {
		Diretor diretor = new Diretor("J K Rowling",  "Diretora mito legends!");
		Filme filme = new Filme("FIlme testaaaandoo", "sinopse testandooooo", 200);
		
		diretor.addFilme(filme);
		Filme filmeRecebido = filmeRepositorio.save(filme);
		
		assertNotNull(filmeRecebido);
		
	}
	
	@Test
	public void removerDiretorFilme() {//voltar depois para ver
		Diretor diretor = new Diretor();
		Filme filme = new Filme();
		diretor = diretorRepositorio.getOne(1);
		filme = filmeRepositorio.getOne(8);
		
		diretor.removerFilme(filme);
		
		Filme filmeResponse = new Filme();
		Diretor diretorResponse = new Diretor();
		
		filmeResponse = filmeRepositorio.save(filme);
		diretorResponse = diretorRepositorio.save(diretor);
		
		assertNotNull(filmeResponse);		
		assertNotNull(diretorResponse);
	}
}
