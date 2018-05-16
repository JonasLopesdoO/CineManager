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
	public void atualizaSessaoExistente() {
		filmeControlMock = Mockito.mock(FilmeController.class);
		Filme filme1 = new Filme(1); //interestelar
		Filme filme2 = new Filme(2);
		
		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme1);
		Mockito.when(filmeControlMock.buscarFilmeId(2)).thenReturn(filme2);
		Mockito.when(filmeControlMock.buscarFilmeNome("interestelar")).thenReturn(filme1);
		
		salaControlMock= Mockito.mock(SalaController.class);
		Sala sala1 = new Sala(1);
		Sala sala2 = new Sala(2); 
			
		Mockito.when(salaControlMock.buscarSalaId(1)).thenReturn(sala1);
		Mockito.when(salaControlMock.buscarSalaId(2)).thenReturn(sala2);
		
		LocalTime horario = LocalTime.parse("20:30");
		LocalDate dataInicio = LocalDate.parse("2018-05-22");
		LocalDate dataFim = LocalDate.parse("2018-05-30");
		
		LocalTime horario2 = LocalTime.parse("20:30");
		LocalDate dataInicio2 = LocalDate.parse("2018-06-22");
		LocalDate dataFim2 = LocalDate.parse("2018-06-30");
		
		sessaoController.addSessao(filme1, sala1, horario, dataInicio, dataFim);
		
		//atualizando a sessão criada
		Sessao sessaoAtualizada = (Sessao) sessaoController.atualizarSessao(1, filme2, sala2, horario2, dataInicio2, dataFim2).getModel().get("sessao");
		
		assertEquals(sessaoRepositorio.getOne(1), sessaoAtualizada);
	}
	
	
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
		assertEquals(salaControlMock.buscarSalaId(1), sala1);	
	}
	
	@Test
	public void excluirSessao() {
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
		
		sessaoController.addSessao(filme1, sala1, horario, dataInicio, dataFim);
		sessaoController.excluirSessao(1);
		
		assertEquals(sessaoRepositorio.getOne(1), null);
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