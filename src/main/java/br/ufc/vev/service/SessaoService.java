package br.ufc.vev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.vev.repositorio.FilmeRepositorio;
import br.ufc.vev.repositorio.SalaRepositorio;

@Service
public class SessaoService {
	@Autowired
	SalaRepositorio salaRepositorio;
	
	@Autowired
	FilmeRepositorio filmeRepositorio;
}
