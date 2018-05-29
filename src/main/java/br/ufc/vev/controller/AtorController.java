package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.service.AtorService;


@Controller
public class AtorController {
	@Autowired
	private AtorService service;
	

	public Ator salvaAtor(String nome, String sobre) {
		try {
			if (this.validaAtor(nome, sobre)) {
				Ator ator = new Ator();
				ator.setNome(nome);
				ator.setSobre(sobre);
				return service.salvarAtor(ator);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean validaAtor(String nome, String sobre) throws Exception {
		
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} else if (sobre.equals(null)) {
			throw new Exception("Sobre não pode ser nulo");
		} else if (sobre.equals("")) {
			throw new Exception("Sobre não pode ser vazio");
		}
		return true;
	}
	
	public boolean validaId(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro ID não pode ser negativo");
		}
		return true;
	}

	public Ator buscaAtor(int id) {
		try {
			if (validaId(id) && existsByIdAtor(id)) {
				return service.buscarAtor(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean excluiAtor(int id) {
		try {
			if (validaId(id) && existsByIdAtor(id)) {
				service.excluirAtor(service.buscarAtor(id));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Ator> getAllAtor() {		
		return service.getAllAtor();
	}

	public boolean atualizaAtor(Ator ator) {
		try {
			if (existsByIdAtor(ator.getId()) && 
					validaAtor(ator.getNome(), ator.getSobre()) &&
					validaId(ator.getId())) {
				service.atualizaAtor(ator);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean existsByIdAtor(int id) {
		return service.buscaAtor(id);
	}
}
