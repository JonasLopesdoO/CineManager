package br.ufc.vev.service;

import java.util.List;

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
	

	public Ator salvarAtor(Ator ator) {
		return atorRepositorio.save(ator);
	}

	public Ator buscarAtor(Integer id) {
		return atorRepositorio.getOne(id);
	}

	public void excluirAtor(Ator ator) {
		atorRepositorio.delete(ator);
	}

	public Ator atualizaAtor(Ator ator) {
		return atorRepositorio.save(ator);
	}

	public List<Ator> getAllAtor() {
		return atorRepositorio.findAll();
	}
}
