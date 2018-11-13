package br.ufc.vev.test.sessao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.service.FilmeService;
import br.ufc.vev.service.GeneroService;
import br.ufc.vev.service.SalaService;
import br.ufc.vev.service.SessaoService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SessaoServiceTest {
	
	@Autowired
	SessaoService sessaoService;
	@Autowired
	SalaService salaService;
	
	@Autowired 
	GeneroService generoService;
	@Autowired
	FilmeService filmeSevice;
	
	@Test
	public void salvarSessao() {
		
		Sessao sessao;
		LocalDate inicio = LocalDate.of(2018, 11, 10);
		LocalDate fim = LocalDate.of(2018, 11, 20);
		LocalTime horario = LocalTime.of(20,30);
		
		sessao = new Sessao(horario, inicio, fim);
		assertNotNull(sessaoService.salvarSessao(sessao));
	}
	
	@Test
	public void atualizarSessao() {
		Sessao sessao;
		LocalDate inicio = LocalDate.of(2018, 11, 10);
		LocalDate fim = LocalDate.of(2018, 11, 20);
		LocalTime horario = LocalTime.of(20,30);
		
		sessao = new Sessao(horario, inicio, fim);
		assertNotNull(sessaoService.salvarSessao(sessao));
		
		
		LocalDate fimData = LocalDate.of(2018, 11, 30);
		sessao.setDataFim(fimData);
		assertNotNull(sessaoService.atualizarSessao(sessao));
	}
	
	@Test
	public void buscaSessao() {
		Sessao sessao;
		LocalDate inicio = LocalDate.of(2018, 11, 10);
		LocalDate fim = LocalDate.of(2018, 11, 20);
		LocalTime horario = LocalTime.of(20,30);
		
		sessao = new Sessao(horario, inicio, fim);
		
		Sessao sessaoRetornada = sessaoService.salvarSessao(sessao);
		assertNotNull(sessaoService.buscarSessao(sessaoRetornada.getId()));
	}
	
	@Test
	public void getSessaoPorId() {
		Sessao sessao;
		LocalDate inicio = LocalDate.of(2018, 11, 10);
		LocalDate fim = LocalDate.of(2018, 11, 20);
		LocalTime horario = LocalTime.of(20,30);
		
		sessao = new Sessao(horario, inicio, fim);
		
		Sessao sessaoRetornada = sessaoService.salvarSessao(sessao);
		assertTrue(sessaoService.existsById(sessaoRetornada.getId()));
	}
	
	@Test 
	public void getAllSessao() {
		sessaoService.getAllSessao();
	}
	
	@Test
	public void excluirSessao() {
		Sessao sessao;
		LocalDate inicio = LocalDate.of(2018, 11, 10);
		LocalDate fim = LocalDate.of(2018, 11, 20);
		LocalTime horario = LocalTime.of(20,30);
		
		sessao = new Sessao(horario, inicio, fim);
		
		Sessao sessaoRetornada = sessaoService.salvarSessao(sessao);
		sessaoService.excluirSessao(sessaoRetornada);
	}
	
	@Test
	public void getSessaoPorData() {
		LocalDate inicio = LocalDate.of(2018, 11, 9);
		LocalDate fim = LocalDate.of(2018, 11, 21);
		
		sessaoService.getSessaoPorData(inicio, fim);
	}
	
	@Test
	public void getSessaoPorCidade() {
		String cidade = "Quixadá";
		
		sessaoService.getSessaoPorCidade(cidade);
	}
	
	@Test
	public void getSessaoPorGenero() {
			
		Genero genero = new Genero();
		genero.setNome("Ação");
		Genero generoBuscado = generoService.salvarGenero(genero);
		
		sessaoService.getSessaoPorGenero(generoBuscado);
	}
	
	@Test
	public void getSessaoPorFilme() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		Filme filmeBuscado = filmeSevice.salvarFilme(Filme);
		
		sessaoService.getSessaoPorFilme(filmeBuscado);
	}
	
	@Test
	public void vinculaFilmeASessao() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		Sessao sessao;
		LocalDate inicio = LocalDate.of(2018, 11, 10);
		LocalDate fim = LocalDate.of(2018, 11, 20);
		LocalTime horario = LocalTime.of(20,30);
		
		sessao = new Sessao(horario, inicio, fim);
		Sessao sessaoBuscada = sessaoService.salvarSessao(sessao);
		Filme filmeBuscado = filmeSevice.salvarFilme(Filme);
		
		assertTrue(sessaoService.vinculaFilmeASessao(sessaoBuscada.getId(), filmeBuscado.getId()));
		sessaoService.getSessaoPorFilme(filmeBuscado);
	}
	
	@Test
	public void desvinculaFilmeDaSessao() {
		Filme Filme = new Filme();
		Filme.setNome("DeadPool");
		Filme.setSinopse("Filme Top");;
		Filme.setDuracao(90);		
		
		Sessao sessao;
		LocalDate inicio = LocalDate.of(2018, 11, 10);
		LocalDate fim = LocalDate.of(2018, 11, 20);
		LocalTime horario = LocalTime.of(20,30);
		
		sessao = new Sessao(horario, inicio, fim);
		Sessao sessaoBuscada = sessaoService.salvarSessao(sessao);
		Filme filmeBuscado = filmeSevice.salvarFilme(Filme);
		
		assertTrue(sessaoService.vinculaFilmeASessao(sessaoBuscada.getId(), filmeBuscado.getId()));
		sessaoService.desvinculaFilmeDaSessao(sessaoBuscada.getId(), filmeBuscado.getId());
	}
	
	@Test
	public void vinculaSalaASessao() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Sala salaRecebida = salaService.salvarSala(sala);		
		
		Sessao sessao;
		LocalDate inicio = LocalDate.of(2018, 11, 10);
		LocalDate fim = LocalDate.of(2018, 11, 20);
		LocalTime horario = LocalTime.of(20,30);
		
		sessao = new Sessao(horario, inicio, fim);
		Sessao sessaoBuscada = sessaoService.salvarSessao(sessao);
		
		assertTrue(sessaoService.vinculaSalaASessao(sessaoBuscada.getId(), salaRecebida.getId()));
	}
	
	@Test
	public void desvinculaSalaDaSessao() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Sala salaRecebida = salaService.salvarSala(sala);		
		
		Sessao sessao;
		LocalDate inicio = LocalDate.of(2018, 11, 10);
		LocalDate fim = LocalDate.of(2018, 11, 20);
		LocalTime horario = LocalTime.of(20,30);
		
		sessao = new Sessao(horario, inicio, fim);
		Sessao sessaoBuscada = sessaoService.salvarSessao(sessao);
		
		assertTrue(sessaoService.vinculaSalaASessao(sessaoBuscada.getId(), salaRecebida.getId()));
		sessaoService.desvinculaSalaDaSessao(sessaoBuscada.getId(), salaRecebida.getId());
	}
	
}
