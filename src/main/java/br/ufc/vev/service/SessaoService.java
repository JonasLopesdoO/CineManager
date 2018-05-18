package br.ufc.vev.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.vev.bean.Sessao;
import br.ufc.vev.repositorio.SessaoRepositorio;

@Service
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
	//	return sessaoRepositorio.findByStartDateBetween(dataInicial, dataFinal);
		return null;
	}

	public List<Sessao> getSessaoPorCidade(String cidade) {
	//	return sessaoRepositorio.findByFirstNameCidade(cidade);
		return null;
	}

	public List<Sessao> getSessaoPorFilme(String filme) {
		//return sessaoRepositorio.findByFirstNameFilme(filme);
		return null;
	}

	public List<Sessao> getSessaoPorGenero(String genero) {
		//return sessaoRepositorio.findByFirstNameGenero(genero);
		return null;
	}
	
}
