package br.ufc.vev.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.controller.SessaoController;
import br.ufc.vev.mock.Mock;



public class SessaoControllerTest {
	
	@Autowired
	private SessaoController sessaoController;
	
	@Test
	public void atualizarUmaSessaoExistente() {

		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = LocalTime.of(20,30);
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = LocalDate.of(2018, 05, 30);
		
		Filme filme2       = Mock.mockFilme().buscarFilmeId(2);
		Sala sala2         = Mock.mockSala().buscarSalaId(2);
		LocalTime horario2 = LocalTime.of(20,35);
		LocalDate inicio2  = LocalDate.of(2018, 05, 02);
		LocalDate fim2     = LocalDate.of(2018, 05, 31);
		
		Sessao sessao = new Sessao(filme, sala, horario, inicio, fim);
		
		Sessao sessaoRecebida = (Sessao) sessaoController.addSessao(filme, sala, horario, inicio, fim).
								getModel().get("sessao");
		
		
		Sessao sessaoAtualizada = (Sessao) sessaoController.atualizarSessao(sessao.getId(),
								  filme2, sala2, horario2, inicio2, fim2).getModel().get("sessao");
		
		System.out.println(sessaoAtualizada.toString());
		assertNotNull(sessaoAtualizada);
		
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
