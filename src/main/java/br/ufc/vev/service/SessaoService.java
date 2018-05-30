package br.ufc.vev.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import br.ufc.vev.bean.Genero;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.repositorio.SessaoRepositorio;

@Service
@Transactional
@Rollback(false)
public class SessaoService {
	
	@Autowired
	SessaoRepositorio sessaoRepositorio;
	/*
	@Autowired
	FilmeController filmeController;
	*/
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
	
	public List<Sessao> getTodasSessoes() {
		return sessaoRepositorio.findAll();
	}

	public Sessao deletarSessao(Integer idSessao) {
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		sessaoRepositorio.delete(sessao);
		return sessao; 
	}

	public List<Sessao> getSessaoPorData(LocalDate dataInicial, LocalDate dataFinal) {
		List<Sessao> s = new ArrayList<Sessao>();
		for (Sessao sessao : getTodasSessoes()) {
			if (sessao.getDataInicio().isBefore(dataInicial) && sessao.getDataFim().isAfter(dataFinal)) {
				s.add(sessao);
			}
		}
		return s;
	}

	public List<Sessao> getSessaoPorCidade(String cidade) {
		List<Sessao> s = new ArrayList<Sessao>();
		for (Sessao sessao : getTodasSessoes()) {
			if (sessao.getSala().getCinema().getCidade().equals(cidade)) {
				s.add(sessao);
			}
		}
		return s;
	}

	public List<Sessao> getSessaoPorFilme(String filme) {
		List<Sessao> s = new ArrayList<Sessao>();
		for (Sessao sessao : getTodasSessoes()) {
			if (sessao.getFilme().getNome().equals(filme)) {
				s.add(sessao);
			}
		}
		return s;
	}

	public List<Sessao> getSessaoPorGenero(String genero) {
		List<Sessao> s = new ArrayList<Sessao>();
		for (Sessao sessao : getTodasSessoes()) {
			for (Genero g : sessao.getFilme().getGeneros()) {
				if (g.getNome().equals(genero)) {
					s.add(sessao);
				}
			}
		}
		return s;
	}
	
}
