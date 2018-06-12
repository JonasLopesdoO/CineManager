package br.ufc.vev.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import br.ufc.vev.bean.Sessao;
import br.ufc.vev.bean.Ator;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.repositorio.SessaoRepositorio;

@Service
@Transactional
@Rollback(false)
public class SessaoService {
	
	@Autowired
	SessaoRepositorio sessaoRepositorio;
	@Autowired
	FilmeService filmeService;
	@Autowired
	SalaService salaService;
	
	public Sessao salvarSessao(Sessao sessao) {
			return sessaoRepositorio.save(sessao);
	}
	
	public Sessao atualizarSessao(Sessao sessao) {
		sessaoRepositorio.save(sessao);
		return sessao;
	}

	public Sessao getSessaoPorId(Integer idSessao) {
		return sessaoRepositorio.getOne(idSessao);
	}
	
	public List<Sessao> getAllSessao() {
		return sessaoRepositorio.findAll();
	}

	public void excluirSessao(Sessao sessao) {
		sessaoRepositorio.delete(sessao);
	}

	public List<Sessao> getSessaoPorData(LocalDate dataInicial, LocalDate dataFinal) {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		for (Sessao sessao : getAllSessao()) {
			if (sessao.getDataInicio().isBefore(dataInicial) && sessao.getDataFim().isAfter(dataFinal)) {
				sessoes.add(sessao);
			}
		}
		return sessoes;
	}

	public List<Sessao> getSessaoPorCidade(String cidade) {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		for (Sessao sessao : getAllSessao()) {
			if (sessao.getSala().getCinema().getCidade().equals(cidade)) {
				sessoes.add(sessao);
			}
		}
		return sessoes;
	}

	public List<Sessao> getSessaoPorFilme(String filme) {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		for (Sessao sessao : getAllSessao()) {
			if (sessao.getFilme().getNome().equals(filme)) {
				sessoes.add(sessao);
			}
		}
		return sessoes;
	}

	public List<Sessao> getSessaoPorGenero(String genero) {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		for (Sessao sessao : getAllSessao()) {
			for (Genero g : sessao.getFilme().getGeneros()) {
				if (g.getNome().equals(genero)) {
					sessoes.add(sessao);
				}
			}
		}
		return sessoes;
	}

	public boolean existsById(int id) {
		return sessaoRepositorio.existsById(id);
	}

	public Sessao buscarSessao(Integer id) {
		return sessaoRepositorio.getOne(id);
	}

	public void vinculaFilmeASessao(Integer idSessao, Integer idFilme) {
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		Filme filme = filmeService.buscarFilme(idFilme);
		
		sessao.setFilme(filme);
			
		sessaoRepositorio.save(sessao);
		filmeService.salvarFilme(filme);
	}

	public void desvinculaFilmeDaSessao(Integer idSessao, Integer idFilme) {
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		Filme filme = filmeService.buscarFilme(idFilme);
			
		sessao.setFilme(null);
		
		sessaoRepositorio.save(sessao);
		filmeService.salvarFilme(filme);		
	}
	
	public void vinculaSalaASessao(Integer idSessao, Integer idSala) {
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		Sala sala = salaService.buscarSala(idSala);
		
		sessao.setSala(sala);
			
		sessaoRepositorio.save(sessao);
		salaService.salvarSala(sala);
	}

	public void desvinculaSalaDaSessao(Integer idSessao, Integer idSala) {
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		Sala sala = salaService.buscarSala(idSala);
			
		sessao.setSala(null);
		
		sessaoRepositorio.save(sessao);
		salaService.salvarSala(sala);		
	}
	
}
