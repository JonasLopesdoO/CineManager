package br.ufc.vev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.service.SalaService;

@Controller
public class SalaController {
	
	@Autowired
	private SalaService service;
	
	//public Sala buscarSalaId(int sala);
	
	//public Sala buscarSalaNome(String nomeSala);

	public Sala salvaSala(String nome, int capacidade) {
		try {
			if (this.validaSala(nome, capacidade)) {
				Sala sala = new Sala(nome, capacidade);
				return service.salvarSala(sala);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean validaSala(String nome, int capacidade) throws Exception {
		
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} else if (capacidade <= 0) {
			throw new Exception("Quantidades de lugares não pode ser menor ou igual a zero");
		}	 
		return true;
	}
	
	public boolean validaIdSala(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro ID não pode ser negativo");
		}
		return true;
	}

	public Sala buscaSala(int id) {
		try {
			if (validaIdSala(id)) {
				return service.buscarSala(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean excluiSala(int id) {
		try {
			if (validaIdSala(id)) {
				service.excluirSala(service.buscarSala(id));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	public Sala atualizarFilme(Sala sala);
//	
//	public Sala addFilme(Sala filme);
//	
//	public Sala removerSala(int id);
}
