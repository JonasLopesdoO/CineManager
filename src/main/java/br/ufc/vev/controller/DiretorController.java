package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufc.vev.bean.Diretor;
//import br.ufc.vev.bean.Filme;
import br.ufc.vev.service.DiretorService;
//import br.ufc.vev.service.FilmeService;

@Controller
public class DiretorController {
	@Autowired
	private DiretorService diretorService;
	

	public Diretor salvaDiretor(String nome, String sobre) {
		try {
			if (this.validaDiretor(nome, sobre)) {
				Diretor diretor = new Diretor();
				diretor.setNome(nome);
				diretor.setSobre(sobre);
				return diretorService.salvarDiretor(diretor);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean validaDiretor(String nome, String sobre) throws Exception {
		
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

	public Diretor buscaDiretor(int id) {
		try {
			if (validaId(id)) {
				return diretorService.buscarDiretor(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean excluiDiretor(int id) {
		try {
			if (validaId(id)) {
				diretorService.excluirDiretor(diretorService.buscarDiretor(id));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Diretor> getAllAtor() {		
		return diretorService.getAllDiretor();
	}

	public boolean atualizaDiretor(Diretor diretor) {
		try {
			if (buscaDiretor(diretor.getId()) != null && 
					validaDiretor(diretor.getNome(), diretor.getSobre()) &&
					validaId(diretor.getId())) {
				diretorService.atualizaDiretor(diretor);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	public boolean addFilme(Diretor ator, Filme filme) {
//		@Autowired
//		private FilmeController filmeController;
//		
//		try {
//			if (filmeController.validaFilme(filme) && validaDiretor(ator.getNome() diretor.getSobre()){
//				return diretor.addFilme(filme);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
//	public void removerFilme(Diretor ator, Filme filme) {
//		@Autowired
//		private FilmeController filmeController;
//		
//		try {
//			if (filmeController.validaFilme(filme) && validaId(filme.getId())) {
//				diretor.removerFilme(filme);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
