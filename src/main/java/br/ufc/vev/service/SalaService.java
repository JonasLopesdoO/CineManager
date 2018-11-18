package br.ufc.vev.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.repositorio.SalaRepositorio;

@Service	
public class SalaService {
	@Autowired
	SalaRepositorio salaRepositorio;
	private static final Logger logger = Logger.getLogger(String.valueOf(SalaService.class));

	
	public Sala salvarSala(Sala sala) {
		try {
			return salaRepositorio.save(sala);
		} catch (ConstraintViolationException e) {
			return null;
		}
	}

	public Sala buscarSala(Integer id) {
		try {
			return salaRepositorio.getOne(id);
		} catch (EntityNotFoundException  e) {
			logger.warning("Sala não encontrada");
		}
		return null;
	}
	
	public void excluirSala(Sala sala) {
		try {
			salaRepositorio.delete(sala);
		} catch (IllegalArgumentException e) {
			logger.warning("Parametros incorretos");
		}
	}

	public Sala atualizaSala(Sala sala) {
		return salaRepositorio.save(sala);
		
	}

	public List<Sala> getAllSala() {
		return salaRepositorio.findAll();
	}
	
}
