package br.ufc.vev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.vev.repositorio.FilmeRepositorio;

@Service
public class FilmeService {
	@Autowired
	FilmeRepositorio filmeRepositorio;
}
