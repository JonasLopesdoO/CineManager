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
	@Autowired
	CinemaService cinemaService;
	
	public Sessao salvarSessao(Sessao sessao) {
			return sessaoRepositorio.save(sessao);
	}
	
	public Sessao atualizarSessao(Sessao sessao) {
		sessaoRepositorio.save(sessao);
		return sessao;
	}
	
	public boolean existsById(int id) {
		return sessaoRepositorio.existsById(id);
	}

	public Sessao buscarSessao(Integer id) {
		return sessaoRepositorio.getOne(id);
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
			if (sessao.getSala() != null && sessao.getSala().getCinema() != null
					&& sessao.getSala().getCinema().getCidade().equals(cidade)) {
				sessoes.add(sessao);
			}
		}
		return sessoes;
	}

	public List<Sessao> getSessaoPorFilme(Filme filme) {
		return filme.getSessoes();
	}

	public List<Sessao> getSessaoPorGenero(Genero genero) {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		
		for (Sessao sessao : this.getAllSessao()) {
			if (sessao.getFilme() != null && sessao.getFilme().getGeneros() != null) {
				for (Genero gen : sessao.getFilme().getGeneros()) {
					if (gen == genero) {
						sessoes.add(sessao);
						break;
					}
				}
			}
			
		}
		return sessoes;
	}

	

	public boolean vinculaFilmeASessao(int idSessao, int idFilme) {
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

	public void desvinculaFilmeDaSessao(int idSessao, int idFilme) {
		Filme filme = filmeService.buscarFilme(idFilme);
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		
		if (!filme.equals(null) && !sessao.equals(null)) {
			sessao.setFilme(null);
			filme.removeSessao(sessao);
			
			sessaoRepositorio.save(sessao);
			filmeService.salvarFilme(filme);
		} 
	}
	
	public boolean vinculaSalaASessao(int idSessao, int idSala) {
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

	public void desvinculaSalaDaSessao(int idSessao, int idSala) {
		Sessao sessao = sessaoRepositorio.getOne(idSessao);
		Sala sala = salaService.buscarSala(idSala);
			
		if (!sala.equals(null) && !sessao.equals(null)) {
			sala.removeSessao(sessao);
			
			sessaoRepositorio.save(sessao);
			salaService.salvarSala(sala);
		} 
		
	}
	
}
