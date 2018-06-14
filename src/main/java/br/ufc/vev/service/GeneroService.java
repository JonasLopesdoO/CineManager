package br.ufc.vev.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import br.ufc.vev.bean.Genero;
import br.ufc.vev.repositorio.GeneroRepositorio;

@Service
@Transactional
@Rollback(false)
public class GeneroService {
	
	@Autowired
	GeneroRepositorio generoRepositorio;
	
	public Genero salvarGenero(Genero genero) {
		return generoRepositorio.save(genero);
	}

	public Genero buscarGenero(Integer id) {
		return generoRepositorio.getOne(id);
	}
	
	public boolean buscaGenero(Integer id) {
		return generoRepositorio.existsById(id);
	}

	public void excluirGenero(Genero genero) {
		generoRepositorio.delete(genero);
	}

	public Genero atualizaGenero(Genero genero) {
		return generoRepositorio.save(genero);
	}
	
	public Genero buscaPorNome(String nome) {
		return generoRepositorio.findByNome(nome);
	}

	public List<Genero> getAllGenero() {
		return generoRepositorio.findAll();
	}

}
