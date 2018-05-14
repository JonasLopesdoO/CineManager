package br.ufc.vev.service;

import java.util.Date;

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
	
	public void adicionaSessao(Date inicio, Date fim, int filme, int sala) {
		Filme filmeCompleto = filmeController.leFilmePorId(filme);
		Sala salaCompleta = salaController.leSalaPorId(sala);
		
		Sessao sessao = new Sessao(inicio, fim, filmeCompleto, salaCompleta);
		
	}
}
