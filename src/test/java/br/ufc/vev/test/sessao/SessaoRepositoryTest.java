package br.ufc.vev.test.sessao;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.controller.FilmeController;
import br.ufc.vev.controller.SalaController;
import br.ufc.vev.repositorio.SessaoRepositorio;

public class SessaoRepositoryTest {

	@Autowired
	private SessaoRepositorio sessaoRepositorio;
	@Autowired
	private FilmeController filmeController;
	@Autowired
	private SalaController salaController;
	
	@Test
	public void salvarSessao() {
		
		LocalTime horario = LocalTime.parse("20:30");
		LocalDate dataInicio = LocalDate.parse("2018-05-22");
		LocalDate dataFim = LocalDate.parse("2018-05-30");
		
		String nomeFilme = "DeadPool";
		String sinopse = "Filme Top";
		int duracao = 90;
		
		String nomeSala = "Sala A1";
		int capacidade = 150;
		
		Filme filme = new Filme();
		filme = filmeController.salvaFilme(nomeFilme, sinopse, duracao);
		
		Sala sala = new Sala();
		sala = salaController.salvaSala(nomeSala, capacidade);
		
		Sessao sessao = new Sessao(filme, sala, horario, dataInicio, dataFim);
		
		assertNotNull(sessaoRepositorio.save(sessao));
		
	}
}
