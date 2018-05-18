package br.ufc.vev.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.controller.FilmeController;
import br.ufc.vev.controller.SalaController;
import br.ufc.vev.controller.SessaoController;
import br.ufc.vev.mock.Mock;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SessaoControllerTest {
	
	private FilmeController filmeControlMock;
	private SalaController salaControlMock;

	@Autowired
	private SessaoController sessaoController;
	
	@Test
	public void atualizaSessaoExistente() {
		filmeControlMock = Mockito.mock(FilmeController.class);
		Filme filme1 = new Filme(1); //interestelar
		Filme filme2 = new Filme(2); //deadpool
		
		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme1);
		Mockito.when(filmeControlMock.buscarFilmeId(2)).thenReturn(filme2);
		Mockito.when(filmeControlMock.buscarFilmeNome("interestelar")).thenReturn(filme1);
		Mockito.when(filmeControlMock.buscarFilmeNome("deadpool")).thenReturn(filme2);
		
		salaControlMock= Mockito.mock(SalaController.class);
		Sala sala1 = new Sala(1);
		Sala sala2 = new Sala(2); 
			
		Mockito.when(salaControlMock.buscarSalaId(1)).thenReturn(sala1);
		Mockito.when(salaControlMock.buscarSalaId(2)).thenReturn(sala2);
		
		LocalTime horario1 = LocalTime.parse("20:30");
		LocalDate dataInicio1 = LocalDate.parse("2018-05-22");
		LocalDate dataFim1 = LocalDate.parse("2018-05-30");
		
		LocalTime horario2 = LocalTime.parse("20:30");
		LocalDate dataInicio2 = LocalDate.parse("2018-06-22");
		LocalDate dataFim2 = LocalDate.parse("2018-06-30");
		
		Sessao sessao = (Sessao) sessaoController.addSessao(filme1, sala1, horario1, dataInicio1, dataFim1).getModel().get("sessao");
		
		//atualizando a sessão criada
		Sessao sessaoAtualizada = (Sessao) sessaoController.atualizarSessao(sessao.getId(), filme2, sala2, 
								horario2, dataInicio2, dataFim2).getModel().get("sessao");
		assertEquals(sessaoController.buscaSessao(sessaoAtualizada.getId()), sessaoAtualizada);
	}
	
//	@Test
//	public void adicionarUmaSessaoCorretamete() {
//
//		filmeControlMock = Mockito.mock(FilmeController.class);
//		Filme filme1 = new Filme(1); //interestelar
//		
//		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme1);
//		Mockito.when(filmeControlMock.buscarFilmeNome("interestelar")).thenReturn(filme1);
//		
//		salaControlMock= Mockito.mock(SalaController.class);
//		
//		Sala sala1 = new Sala(1); 
//			
//		Mockito.when(salaControlMock.buscarSalaId(1)).thenReturn(sala1);
//		
//		LocalTime horario = LocalTime.of(20, 00);
//		LocalDate dataInicio = LocalDate.of(2018, 05, 15);
//		LocalDate dataFim = LocalDate.of(2018, 05, 30);
//		
//		Sessao sessao = (Sessao) sessaoController.addSessao(filme1, sala1, horario, dataInicio, dataFim).getModel().get("sessao");
//
//		assertNotNull(sessao);
//		
//	}
	
	@Test
	public void adicionarUmaSessaoComHorarioNulo() {

		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = null;
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = LocalDate.of(2018, 05, 30);

		assertNull(sessaoController.addSessao(filme, sala, horario, inicio, fim));
		
	}
	
	@Test
	public void adicionarUmaSessaoComDataInicioNula() {

		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = LocalTime.of(21, 30);
		LocalDate inicio  = null;
		LocalDate fim     = LocalDate.of(2018, 05, 30);

		assertNull(sessaoController.addSessao(filme, sala, horario, inicio, fim));
		
	}
	
	@Test
	public void adicionarUmaSessaoComDataFimNula() {

		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = LocalTime.of(21, 30);
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = null;

		assertNull(sessaoController.addSessao(filme, sala, horario, inicio, fim));	
	}
	
	@Test
	public void adicionarUmaSessaoComFilmeNulo() {

		Filme filme       = null;
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = LocalTime.of(20,30);
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = LocalDate.of(2018, 05, 30);
		
		assertNull(sessaoController.addSessao(filme, sala, horario, inicio, fim));	
		
	}
	
	@Test
	public void adicionarUmaSessaoComSalaNula() {

		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = null;
		LocalTime horario = LocalTime.of(20,30);
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = LocalDate.of(2018, 05, 30);
		
		assertNull(sessaoController.addSessao(filme, sala, horario, inicio, fim));	
	}
	
	@Test
	public void excluirSessao() {
		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = LocalTime.of(20,30);
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = LocalDate.of(2018, 05, 30);
		
		Sessao sessao = (Sessao) sessaoController.addSessao(filme, sala, horario, inicio, fim).getModel().get("sessao");
		int id = sessao.getId();
		
		assertNotNull(sessaoController.excluirSessao(id));
	}
	
}
