package br.ufc.vev.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.repositorio.AtorRepositorio;

@Service
@Transactional
@Rollback(false)
public class AtorService {

	@Autowired 
	AtorRepositorio atorRepositorio;
	private static final Logger logger = Logger.getLogger(String.valueOf(AtorService.class));


	public Ator salvarAtor(Ator ator) {
		return atorRepositorio.save(ator);
	}

	public Ator buscarAtor(Integer id) {
		try {
			return atorRepositorio.getOne(id);
		} catch (EntityNotFoundException  e) {
			logger.warning("Ator não encontrado");
		}
		return null;
	}
	
	public void excluirAtor(Ator ator) {
		try {
			atorRepositorio.delete(ator);
		} catch (IllegalArgumentException e) {
			logger.warning("Parametros incorretos");
		}
	}

	public Ator atualizaAtor(Ator ator) {
		return atorRepositorio.save(ator);
	}

	public List<Ator> getAllAtor() {
		return atorRepositorio.findAll();
	}
}
