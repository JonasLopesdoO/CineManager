package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.service.FilmeService;

public class FilmeController {
	@Autowired
	FilmeService service;
	@Autowired
	AtorController atorController;
	
	public Filme salvaFilme(String nome, String sinopse, int duracao) {
		try {
			if (this.validaFilme(nome, sinopse, duracao)) {
				Filme filme = new Filme();
				filme.setNome(nome);
				filme.setSinopse(sinopse);
				filme.setDuracao(duracao);
				return service.salvarFilme(filme);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean validaFilme(String nome, String sinopse, int duracao) throws Exception {
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} else if (duracao == -1) {
			throw new Exception("Duração não pode ser negativa");
		} else if (duracao == 0) {
			throw new Exception("duração não pode ser zero");
		} else if (sinopse.equals("")) {
			throw new Exception("Cidade não pode ser vazio");
		} else if (sinopse.equals(null)) {
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

	public Filme buscaFilme(int id) {
		try {
			if (validaId(id)) {
				return service.buscarFilme(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean excluiFilme(int id) {
		try {
			if (validaId(id)) {
				service.excluirFilme(service.buscarFilme(id));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Filme> getAllFilme() {		
		return service.getAllFilme();
	}

	public boolean atualizaFilme(Filme filme) {
		try {
			if (validaFilme(filme.getNome(), filme.getSinopse(), filme.getDuracao()) &&
					validaId(filme.getId()) && buscaFilme(filme.getId()) != null) {
				service.atualizaFilme(filme);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean vinculaAtorAoFilme(int idFilme, int idAtor) {
		try {
			if (validaId(idFilme) && validaId(idAtor)) {
				return service.vinculaAtorAoFilme(idFilme, idAtor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean vinculaDiretorAoFilme(int idFilme, int idDir) {
		try {
			if (validaId(idFilme) && validaId(idDir)) {
				return service.vinculaDiretorAoFilme(idFilme, idDir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean vinculaGeneroAoFilme(int idFilme, int idGen) {
		try {
			if (validaId(idFilme) && validaId(idGen)) {
				return service.vinculaAtorAoFilme(idFilme, idGen);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
//	
//	public void desvinculaSalaAoCinema(int idCine, int idSala) {
//		try {
//			if (validaId(idCine) && validaId(idSala)) {
//				service.desvinculaSalaDoCinema(idCine, idSala);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
}
