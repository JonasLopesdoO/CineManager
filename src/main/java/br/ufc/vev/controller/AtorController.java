package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufc.vev.bean.Ator;
//import br.ufc.vev.bean.Filme;
import br.ufc.vev.service.AtorService;
//import br.ufc.vev.service.FilmeService;

@Controller
public class AtorController {
	@Autowired
	private AtorService atorService;
	

	public Ator salvaAtor(String nome, String sobre) {
		try {
			if (this.validaAtor(nome, sobre)) {
				Ator ator = new Ator();
				ator.setNome(nome);
				ator.setSobre(sobre);
				return atorService.salvarAtor(ator);
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
			if (validaId(id)) {
				return atorService.buscarAtor(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean excluiAtor(int id) {
		try {
			if (validaId(id)) {
				atorService.excluirAtor(atorService.buscarAtor(id));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Ator> getAllAtor() {		
		return atorService.getAllAtor();
	}

	public boolean atualizaAtor(Ator ator) {
		try {
			if (buscaAtor(ator.getId()) != null && 
					validaAtor(ator.getNome(), ator.getSobre()) &&
					validaId(ator.getId())) {
				atorService.atualizaAtor(ator);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	public boolean addFilme(Ator ator, Filme filme) {
//		@Autowired
//		private FilmeController filmeController;
//		
//		try {
//			if (filmeController.validaFilme(filme) && validaAtor(ator.getNome() ator.getSobre()){
//				return ator.addFilme(filme);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
//	public void removerFilme(Ator ator, Filme filme) {
//		@Autowired
//		private FilmeController filmeController;
//		
//		try {
//			if (filmeController.validaFilme(filme) && validaId(filme.getId())) {
//				ator.removerFilme(filme);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
