package br.ufc.vev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.repositorio.FilmeRepositorio;

@Service
public class FilmeService {
	@Autowired
	FilmeRepositorio filmeRepositorio;
	
	public Filme salvarFilme(Filme filme) {
		return filmeRepositorio.save(filme);
	}

	public Filme buscarFilme(Integer id) {
		return filmeRepositorio.getOne(id);
	}

	public void excluirFilme(Filme filme) {
		filmeRepositorio.delete(filme);
	}

	public Filme atualizaFilme(Filme filme) {
		return filmeRepositorio.save(filme);
		
	}

	public List<Filme> getAllFilme() {
		return filmeRepositorio.findAll();
	}
}
