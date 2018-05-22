package br.ufc.vev.service;

import java.util.List;

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
	
	public Diretor salvarDiretor(Diretor diretor) {
		return diretorRepositorio.save(diretor);
	}

	public Diretor buscarDiretor(Integer id) {
		return diretorRepositorio.getOne(id);
	}

	public void excluirDiretor(Diretor diretor) {
		diretorRepositorio.delete(diretor);
	}

	public Diretor atualizaDiretor(Diretor diretor) {
		return diretorRepositorio.save(diretor);
		
	}

	public List<Diretor> getAllDiretor() {
		return diretorRepositorio.findAll();
	}
}
