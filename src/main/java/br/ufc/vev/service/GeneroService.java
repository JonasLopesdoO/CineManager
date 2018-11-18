package br.ufc.vev.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;
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
	private static final Logger logger = Logger.getLogger(String.valueOf(GeneroService.class));

	
	public Genero salvarGenero(Genero genero) {
		return generoRepositorio.save(genero);
	}

	public Genero buscarGenero(Integer id) {
		try {
			return generoRepositorio.getOne(id);
		} catch (EntityNotFoundException  e) {
			logger.warning("Genero não encontrado");
		}
		return null;
	}
	
	public void excluirGenero(Genero genero) {
		try {
			generoRepositorio.delete(genero);
		} catch (IllegalArgumentException e) {
			logger.warning("Parametros incorretos");
		}
		
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
