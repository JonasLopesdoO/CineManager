package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.service.FilmeService;

@Controller
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
		} else if (duracao < 0) {
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

	public Filme atualizaFilme(Filme filme) {
		try {
			if (validaFilme(filme.getNome(), filme.getSinopse(), filme.getDuracao()) &&
					validaId(filme.getId()) && buscaFilme(filme.getId()) != null) {
				return service.atualizaFilme(filme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	
	public void desvinculaAtorDoFilme(int idFilme, int idAtor) {
		try {
			if (validaId(idFilme) && validaId(idAtor)) {
				service.desvinculaAtorDoFilme(idFilme, idAtor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public void desvinculaDiretorDoFilme(int idFilme, int idDir) {
		try {
			if (validaId(idFilme) && validaId(idDir)) {
				service.desvinculaDiretorDoFilme(idFilme, idDir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public void desvinculaGeneroDoFilme(int idFilme, int idGen) {
		try {
			if (validaId(idFilme) && validaId(idGen)) {
				service.desvinculaGeneroDoFilme(idFilme, idGen);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean atorPertenceAoFilme(int idFilme, int idAtor) {
		try {
			if (validaId(idAtor) && validaId(idFilme)) {
				Filme filme = new Filme();
				filme = buscaFilme(idFilme);
				for (int i = 0; i < filme.getAtores().size(); i++) {
					if (filme.getAtores().get(i).getId() == idAtor) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	public boolean diretorPertenceAoFilme(int idFilme, int idDir) {
		try {
			if (validaId(idDir) && validaId(idFilme)) {
				Filme filme = new Filme();
				filme = buscaFilme(idFilme);
				for (int i = 0; i < filme.getDiretores().size(); i++) {
					if (filme.getDiretores().get(i).getId() == idDir) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	public boolean generoPertenceAoFilme(int idFilme, int idGen) {
		try {
			if (validaId(idGen) && validaId(idFilme)) {
				Filme filme = new Filme();
				filme = buscaFilme(idFilme);
				for (int i = 0; i < filme.getGeneros().size(); i++) {
					if (filme.getGeneros().get(i).getId() == idGen) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}
}
