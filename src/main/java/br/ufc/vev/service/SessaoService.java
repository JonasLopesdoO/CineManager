package br.ufc.vev.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.bean.Sala;
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
			if (sessao.getDataInicio().isAfter(dataInicial) && sessao.getDataFim().isBefore(dataFinal)) {
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

	public List<Sessao> getSessaoPorFilme(Filme filme) {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		for (Sessao sessao : this.getAllSessao()) {
				if (sessao.getFilme() == filme) {
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

	public boolean vinculaFilmeASessao(Integer idSessao, Integer idFilme) {
		Filme filme = filmeService.buscarFilme(idFilme);
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		
		if(filme.equals(null) || sessao.equals(null) || sessao.getFilme() != null) {
			return false;
		}else{
			sessao.setFilme(filme);
			filme.getSessoes().add(sessao);
			
			sessaoRepositorio.save(sessao);
			filmeService.salvarFilme(filme);
			
			return true;
		}
			
	}

	public void desvinculaFilmeDaSessao(Integer idSessao, Integer idFilme) {
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		Filme filme = filmeService.buscarFilme(idFilme);
		
		if (!filme.equals(null) && !sessao.equals(null)) {
			sessao.setFilme(null);
			filme.getSessoes().remove(sessao);
			
			sessaoRepositorio.save(sessao);
			filmeService.salvarFilme(filme);
		}
	}
	public void desvinculaFilmeDaSessao(int idSessao, int idFilme) {
		Filme filme = filmeService.buscarFilme(idFilme);
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		
		if (!filme.equals(null) && !sessao.equals(null)) {
			filme.removeSessao(sessao);
			
			sessaoRepositorio.save(sessao);
			filmeService.salvarFilme(filme);
		} 
	}
	
	public boolean vinculaSalaASessao(Integer idSessao, Integer idSala) {
		Sala sala = salaService.buscarSala(idSala);
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		
		if(sala.equals(null) || sessao.equals(null) || sessao.getSala() != null) {
			return false;
		}else{
			sessao.setSala(sala);
			sala.getSessoes().add(sessao);
				
			sessaoRepositorio.save(sessao);
			salaService.salvarSala(sala);
			
			return true;
		}
	}

	public void desvinculaSalaDaSessao(Integer idSessao, Integer idSala) {
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		Sala sala = salaService.buscarSala(idSala);
			
		if (!sala.equals(null) && !sessao.equals(null)) {
			sala.removeSessao(sessao);
			
			sessaoRepositorio.save(sessao);
			salaService.salvarSala(sala);
		} 
		
	}
	
}
