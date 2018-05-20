package br.ufc.vev.controller;

import org.springframework.stereotype.Controller;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.service.SalaService;

@Controller
public class SalaController {
	
	private SalaService service;
	
	//public Sala buscarSalaId(int sala);
	
	//public Sala buscarSalaNome(String nomeSala);

	public Sala salvaSala(String nome, int capacidade) {
		try {
			if (validaSala(nome, capacidade)) {
				Sala sala = new Sala(nome, capacidade);
				return service.salvarSala(sala);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean validaSala(String nome, int capacidade) throws Exception {
		
		if (nome.equals(" ")) {
			throw new Exception("Nome não pode ser vazio");
		} if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} if (capacidade <= 0) {
			throw new Exception("Quantidades de lugares não pode ser menor ou igual a zero");
		}	 
		return true;
	}
	
//	public Sala atualizarFilme(Sala sala);
//	
//	public Sala addFilme(Sala filme);
//	
//	public Sala removerSala(int id);
}
