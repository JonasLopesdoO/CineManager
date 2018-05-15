package br.ufc.vev.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.controller.FilmeController;
import br.ufc.vev.controller.SalaController;
import br.ufc.vev.repositorio.SessaoRepositorio;

@Service
public class SessaoService {
	
	@Autowired
	SessaoRepositorio sessaoRepositorio;
	
	@Autowired
	FilmeController filmeController;
	
	@Autowired
	SalaController salaController;
	
	public Sessao salvarSessao(Sessao sessao) {
		sessaoRepositorio.save(sessao);
		
		return sessao;
	}
	
	public Sessao atualizarSessao(Integer idSessao, Filme filme, Sala sala, LocalTime horario, LocalDate dataInicio,
			LocalDate dataFim) {

		Sessao sessao = sessaoRepositorio.findOne(idSessao);
		sessao.setFilme(filme);
		sessao.setSala(sala);
		sessao.setHorario(horario);
		sessao.setDataInicio(dataInicio);
		sessao.setDataFim(dataFim);
		
		sessaoRepositorio.save(sessao);
		
		return sessao;
	}

	public Sessao getSessaoPorId(Integer id) {
		return sessaoRepositorio.findOne(id);
	}
	
	public List<Sessao> getTodasSessoes() {
		return sessaoRepositorio.findAll();
	}

	public Sessao deletarSessao(Integer idSessao) {
		
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		sessaoRepositorio.delete(sessao);
		
		return sessao; 
	}

	
}
