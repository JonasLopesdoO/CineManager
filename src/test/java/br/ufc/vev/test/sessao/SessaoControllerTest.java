package br.ufc.vev.test.sessao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.controller.SessaoController;
import br.ufc.vev.mock.Mock;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SessaoControllerTest {
	
	@Autowired
	private SessaoController sessaoController;
	
	@Test
	public void atualizaSessaoExistente() {
		
		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = LocalTime.of(21, 30);
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = LocalDate.of(2018, 05, 30);
		
		Sessao sessao = (Sessao) sessaoController.addSessao(filme, sala, horario, inicio, fim).getModel().get("sessao");
		
		Filme filmeUp       = Mock.mockFilme().buscarFilmeId(1);
		Sala salaUp         = Mock.mockSala().buscarSalaId(1);
		LocalTime horarioUp = LocalTime.of(20, 30);
		LocalDate inicioUp  = LocalDate.of(2018, 05, 02);
		LocalDate fimUp     = LocalDate.of(2018, 05, 29);
		
		Sessao sessaoAtualizada = (Sessao) sessaoController.atualizarSessao(sessao.getId(), filmeUp, salaUp, 
								horarioUp, inicioUp, fimUp).getModel().get("sessao");
		assertEquals(sessaoController.buscaSessao(sessaoAtualizada.getId()), sessaoAtualizada);
	}
	
	@Test
	public void salvarController() {
		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = LocalTime.of(21, 30);
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = LocalDate.of(2018, 05, 30);
		
		Sessao sessaoRecebida = (Sessao) sessaoController.addSessao(filme, sala, horario, inicio, fim).getModel().get("sessao");

		assertNotNull(sessaoRecebida);
	}
	
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
		
		System.out.println(id);
		assertNotNull(sessaoController.excluirSessao(id));
	}
	
}
