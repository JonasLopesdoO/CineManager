package br.ufc.vev.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.controller.FilmeController;
import br.ufc.vev.controller.SalaController;
import br.ufc.vev.controller.SessaoController;
import br.ufc.vev.repositorio.SessaoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SessaoControllerTest {
	
	@MockBean
	private SessaoRepositorio sessaoRepositorio;
	
	private FilmeController filmeControlMock;
	
	private SalaController salaControlMock;
	@Autowired
	private SessaoController sessaoController;
	
	@Test
	public void adicionarUmaSessaoCorretamete() {
		filmeControlMock = Mockito.mock(FilmeController.class);
		Filme filme1 = new Filme(1); //interestelar
		
		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme1);
		Mockito.when(filmeControlMock.buscarFilmeNome("interestelar")).thenReturn(filme1);
		
		salaControlMock= Mockito.mock(SalaController.class);
		
		Sala sala1 = new Sala(1); 
			
		Mockito.when(salaControlMock.buscarSalaId(1)).thenReturn(sala1);
		
		LocalTime horario = LocalTime.parse("20:30");
		LocalDate dataInicio = LocalDate.parse("2018-05-22");
		LocalDate dataFim = LocalDate.parse("2018-05-30");
		
		Sessao sessao = (Sessao) sessaoController.addSessao(filme1, sala1, horario, dataInicio, dataFim).getModel().get("sessao");
		
		assertEquals(sessaoRepositorio.getOne(1), sessao);
		assertEquals(filmeControlMock.buscarFilmeId(1), filme1);
		
	}
	
	@Test
	public void listarSessaoPorData() {
		
	}
	
	@Test
	public void listarSessaoPorFilme() {}

	@Test
	public void listarSessaoPorCinema() {}
	
	@Test
	public void listarSessaoPorGenero() {}
}
