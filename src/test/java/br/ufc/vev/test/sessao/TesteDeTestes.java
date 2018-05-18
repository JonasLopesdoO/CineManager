package br.ufc.vev.test.sessao;

import static org.junit.Assert.assertNotNull;

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
import br.ufc.vev.repositorio.SessaoRepositorio;
import br.ufc.vev.service.SessaoService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TesteDeTestes {

	@Autowired
	SessaoRepositorio repositorio;
	
	@Autowired
	SessaoService service;
	
	@Autowired
	SessaoController controller;
	
	@Test
	public void salvar() {
		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = LocalTime.of(20,30);
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = LocalDate.of(2018, 05, 30);
		
		Sessao s = new Sessao(filme, sala, horario, inicio, fim);
		
		Sessao sessaoRecebida = repositorio.save(s);
		
		System.out.println(sessaoRecebida);
		
		assertNotNull(sessaoRecebida);
	}
	
	@Test
	public void salvarService() {
		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = LocalTime.of(20,30);
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = LocalDate.of(2018, 05, 30);
		
		Sessao s = new Sessao(filme, sala, horario, inicio, fim);
		
		Sessao sessaoRecebida = service.salvarSessao(s);
		
		System.out.println(sessaoRecebida);
		
		assertNotNull(sessaoRecebida);
	}
	
	@Test
	public void salvarController() {
		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = LocalTime.of(21, 30);
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = LocalDate.of(2018, 05, 30);
		
		Sessao s = new Sessao(filme, sala, horario, inicio, fim);
		
		Sessao sessaoRecebida = (Sessao) controller.addSessao(filme, sala, horario, inicio, fim).getModel().get("sessao");
		
		System.out.println(sessaoRecebida);
		
		assertNotNull(sessaoRecebida);
	}
	
}
