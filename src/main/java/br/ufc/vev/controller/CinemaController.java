package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.service.CinemaService;

@Controller
public class CinemaController {
	
	@Autowired
	private CinemaService service;
	

	public Cinema salvaCinema(String nome, String cidade, String endereco) {
		try {
			if (this.validaCinema(nome, cidade, endereco)) {
				Cinema cinema = new Cinema();
				cinema.setNome(nome);
				cinema.setCidade(cidade);
				cinema.setEndereco(endereco);
				return service.adicionaCinema(cinema);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean validaCinema(String nome, String cidade, String endereco) throws Exception {
		
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} else if (endereco.equals("")) {
			throw new Exception("Endereco não pode ser vazio");
		} else if (endereco.equals(null)) {
			throw new Exception("Endereco não pode ser nulo");
		} else if (cidade.equals("")) {
			throw new Exception("Cidade não pode ser vazio");
		} else if (cidade.equals(null)) {
			throw new Exception("Cidade não pode ser nulo");
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

	public Cinema buscaCinema(int id) {
		try {
			if (validaId(id)) {
				return service.buscaCinema(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean excluiCinema(int id) {
		try {
			if (validaId(id)) {
				service.excluiCinema(service.buscaCinema(id));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Cinema> getAllCinema() {		
		return service.getAllCinema();
	}

	public boolean atualizaCinema(Cinema cinema) {
		try {
			if (buscaCinema(cinema.getId()) != null && 
					validaCinema(cinema.getNome(), cinema.getCidade(), cinema.getEndereco()) &&
					validaId(cinema.getId())) {
				service.atualizaCinema(cinema);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean vinculaSalaAoCinema(int idCine, int idSala) {
		try {
			if (validaId(idCine) && validaId(idSala)) {
				return service.vinculaSalaAoCinema(idCine, idSala);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void desvinculaSalaAoCinema(int idCine, int idSala) {
		try {
			if (validaId(idCine) && validaId(idSala)) {
				service.desvinculaSalaDoCinema(idCine, idSala);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

