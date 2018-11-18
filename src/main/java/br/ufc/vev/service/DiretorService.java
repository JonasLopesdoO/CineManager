package br.ufc.vev.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import br.ufc.vev.bean.Diretor;
import br.ufc.vev.repositorio.DiretorRepositorio;

@Service
@Transactional
@Rollback(false)
public class DiretorService {

	@Autowired
	DiretorRepositorio diretorRepositorio;
	private static final Logger logger = Logger.getLogger(String.valueOf(AtorService.class));

	public Diretor salvarDiretor(Diretor diretor) {
		return diretorRepositorio.save(diretor);
	}

	public Diretor buscarDiretor(Integer id) {
		try {
			return diretorRepositorio.getOne(id);
		} catch (EntityNotFoundException  e) {
			logger.warning("Diretor não encontrado");
		}
		return null;
	}

	public void excluirDiretor(Diretor diretor) {
		try {
			diretorRepositorio.delete(diretor);
		} catch (IllegalArgumentException e) {
			logger.warning("Parametros incorretos");
		}
	}

	public Diretor atualizaDiretor(Diretor diretor) {
		return diretorRepositorio.save(diretor);
		
	}

	public List<Diretor> getAllDiretor() {
		return diretorRepositorio.findAll();
	}

}
