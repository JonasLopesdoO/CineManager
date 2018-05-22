package br.ufc.vev.test.pessoa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.service.DiretorService;
import br.ufc.vev.service.FilmeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class DiretorServiceTest {

	@Autowired 
	DiretorService diretorService;
	
	@Autowired 
	FilmeService filmeService;
	
	@Test
	public void adicionarDiretorServiceTest() {
		String nome = "José Jucaaa";
		String sobre = "Diretor Caba arretado";
		
		Diretor diretor = new Diretor();
		
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
		Diretor diretorRecebido = diretorService.salvarDiretor(diretor);
		
		assertNotNull(diretorRecebido);
	}
	
	@Test
	public void excluirDiretorServiceTest() {
		String nome = "Diretor josé";
		String sobre = "Este é um diretor muito do bunito!";
		
		Diretor diretor = new Diretor();
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
		Diretor diretorRecebido = diretorService.salvarDiretor(diretor);
		
		diretorService.excluirDiretor(diretorRecebido);
		
		assertNotNull(diretorRecebido);
	}
	
	@Test 
	public void buscarDiretorServiceTest() {
		String nome = "Peeter clarck";
		String sobre = "Este é um diretor muito bom!";
		
		Diretor diretor = new Diretor();
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
		Diretor diretorRecebido = new Diretor();
		
		diretorRecebido = diretorService.salvarDiretor(diretor);
		
		int idDiretor = diretorRecebido.getId();
		
		assertNotNull(diretorService.buscarDiretor(idDiretor));
	}
	
	@Test
	public void updateDiretorRepository() {
		//Diretor atual
		String nome = "Peeter clarckeeeeeerr";
		String sobre = "Este é um diretor muito bom!";
		
		Diretor diretor = new Diretor();
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
//		diretor atualizado
		String nomeNovo = "José Raimundo";
		String sobreNovo = "Este é um diretor muito arretado!";
		
		Diretor diretorAtualizado = new Diretor();
		
		diretorAtualizado = diretorService.salvarDiretor(diretor);
		diretorAtualizado.setNome(nomeNovo);
		diretorAtualizado.setSobre(sobreNovo);
		
		Diretor diretorAtualizadoRecebido = new Diretor();
		diretorAtualizadoRecebido = diretorService.atualizaDiretor(diretorAtualizado);
		
		assertEquals(diretorAtualizado.getId(), diretorAtualizadoRecebido.getId());
		assertEquals(diretorAtualizado.getNome(), diretorAtualizadoRecebido.getNome());
//		assertTrue(diretorRepositorio.existsById(diretorAtualizado.getId()));
	}
	
	@Test
	public void buscaTodosDiretorRepository() {
		String nome = "Peeter clarck";
		String sobre = "Este é um diretor muito bom!";
		
		Diretor diretor = new Diretor();
		diretor.setNome(nome);
		diretor.setSobre(sobre);
		
		diretorService.salvarDiretor(diretor);
		
		assertNotNull(diretorService.getAllDiretor());
	}
	
	@Test
	public void addDiretorFilme() {
		Diretor diretor = new Diretor("diretoootesteeeeeeeeeeeeeeee", "Sobre teste");
		Filme filme = new Filme("filmetesteeeeeeeeeeeeediretooooor", "sinopse teste", 180);
		
		diretor.addFilme(filme);
		Filme filmeRecebido = filmeService.salvarFilme(filme);

		assertNotNull(filmeRecebido);		
	}
	
	@Test
	public void removerDiretorFilme() {//voltar depois para ver
		Diretor diretor = new Diretor();
		Filme filme = new Filme();
		diretor = diretorService.buscarDiretor(16);
		filme = filmeService.buscarFilme(11);
		
		diretor.removerFilme(filme);
		
		Filme filmeResponse = new Filme();
		Diretor atorResponse = new Diretor();
		
		filmeResponse = filmeService.salvarFilme(filme);
		atorResponse = diretorService.salvarDiretor(diretor);
		
//		atorRepositorio.deleteFilmeAtores(atorResponse.getId(),filmeResponse.getId());
		
		assertNotNull(filmeResponse);		
		assertNotNull(atorResponse);
	}

}
