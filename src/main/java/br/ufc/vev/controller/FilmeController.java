package br.ufc.vev.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.service.FilmeService;

@Controller
@Transactional
@Rollback(false)
@RequestMapping(path = "/filme")
public class FilmeController {
	
	@Autowired
	FilmeService filmeService;
	@Autowired
	AtorController atorController;
	@Autowired
	DiretorController diretorController;
	@Autowired
	GeneroController generoController;
	
	@SuppressWarnings("finally")
	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("filme");
		try {
			List<Filme> filmes = getAllFilme();

			model.addObject("filmes", filmes);
		

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return model;
		}
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesFilme(@PathVariable("id") Integer id){
			
	  ModelAndView model = new ModelAndView("detalhes-filme");
	  Filme filme = filmeService.buscarFilme(id);

	  model.addObject("atores", atorController.getAllAtor());
	  model.addObject("diretores", diretorController.getAllDiretores());
	  model.addObject("generos", generoController.getAllGenero());
	  model.addObject("filme", filme);
			
	  return model;
	}

	@RequestMapping("/formulario")
	public ModelAndView formularioFilme() {
		ModelAndView model = new ModelAndView("formulario-filme");
		model.addObject("filme", new Filme());

		return model;
	}

	@SuppressWarnings("finally")
	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvaFilme(Filme filme) {
		ModelAndView model = new ModelAndView("filme");

		try {
			if (this.validaFilme(filme.getNome(), filme.getSinopse(), filme.getDuracao())) {
				filmeService.salvarFilme(filme);

				model.addObject("filmeRetorno", filme);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaFilme(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("filme");
		try {
			if (this.validaId(id)) {
				if (existsByIdFilme(id)) {
					Filme filme = new Filme();

					filme = filmeService.buscarFilme(id);

					model.addObject("filmeRetorno", filme);
				} else {
					// mensagem de erro "id nao existente no banco"
				}
			} else {
				// msg de id invalido
			}
		} catch (Exception e) { // caso de erro
			e.printStackTrace();
		} finally { // sempre será execultado
			return index();
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiFilme(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("filme");
		
		try {
			Filme filme = new Filme();
			if (validaId(id) && existsByIdFilme(id)) {
				filme = filmeService.buscarFilme(id);
				filmeService.excluirFilme(filme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
	}

	public List<Filme> getAllFilme() {		
		return filmeService.getAllFilme();
	}

	// o metodo utilizado para atualizar será o salvar, visto que o spring boot ja
			// atualiza automaticamente o objeto passado.
			// este método só redireciona para a digitação dos novos campos do model
	@SuppressWarnings("finally")
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaFilme(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-filme");

		try {
			if (existsByIdFilme(id)) {
				Filme filme = filmeService.buscarFilme(id);

				model.addObject("filme", filme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return model;
		}
	}
	
	@RequestMapping(path="/{idFilme}/adicionarAtor", method=RequestMethod.POST)
	public ModelAndView vincularAtorAoFilme(@PathVariable("idFilme") Integer idFilme, 
											@RequestParam Integer idAtor ){

	  ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
	  
	  if (existsByIdFilme(idFilme) && atorController.existsByIdAtor(idAtor) && atorPertenceAoFilme(idFilme, idAtor) != true) {
		  filmeService.vinculaAtorAoFilme(idFilme, idAtor);
	  }
	  
	  return model;
	}
	
	@RequestMapping(path="/{idFilme}/removerAtor/{idAtor}")
	public ModelAndView desvinculaAtorDoFilme(@PathVariable("idFilme") Integer idFilme, @PathVariable("idAtor") Integer idAtor) {
		
		ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
		
		if (atorPertenceAoFilme(idFilme, idAtor)) {
			filmeService.desvinculaAtorDoFilme(idFilme, idAtor);
		}
		
		return model;
	}
	
	@RequestMapping(path="/{idFilme}/adicionarDiretor", method=RequestMethod.POST)
	public ModelAndView vincularDiretorAoFilme(@PathVariable("idFilme") Integer idFilme, 
											@RequestParam Integer idDiretor ){

	  ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
	  
	  if (existsByIdFilme(idFilme) && diretorController.existsByIdDiretor(idDiretor) && diretorPertenceAoFilme(idFilme, idDiretor) != true){
		  filmeService.vinculaDiretorAoFilme(idFilme, idDiretor);
	  }
	  
	  return model;
	}
	
	@RequestMapping(path="/{idFilme}/removerDiretor/{idDiretor}")
	public ModelAndView desvinculaDiretorDoFilme(@PathVariable("idFilme") Integer idFilme, @PathVariable("idDiretor") Integer idDiretor) {
		
		ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
		
		if(diretorPertenceAoFilme(idFilme, idDiretor)) {
			filmeService.desvinculaDiretorDoFilme(idFilme, idDiretor);
		}
		
		return model;
	}
	
	@RequestMapping(path="/{idFilme}/adicionarGenero", method=RequestMethod.POST)
	public ModelAndView vincularGeneroAoFilme(@PathVariable("idFilme") Integer idFilme, 
											@RequestParam Integer idGenero){

	  ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
	  
	  if (existsByIdFilme(idFilme) && generoController.existsByIdGenero(idGenero) && generoPertenceAoFilme(idFilme, idGenero) != true){
		  filmeService.vinculaGeneroAoFilme(idFilme, idGenero);
	  }
	  
	  return model;
	}
	
	@RequestMapping(path="/{idFilme}/removerGenero/{idGenero}")
	public ModelAndView desvinculaGeneroDoFilme(@PathVariable("idFilme") Integer idFilme, @PathVariable("idGenero") Integer idGenero) {
		
		ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
		
		if(generoPertenceAoFilme(idFilme, idGenero)) {
			filmeService.desvinculaGeneroDoFilme(idFilme, idGenero);;
		}
		
		return model;
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
	
	public boolean existsByIdFilme(int id) {
		return filmeService.existsById(id);
	}
	
	public boolean atorPertenceAoFilme(int idFilme, int idAtor) {
		if (existsByIdFilme(idFilme) && atorController.existsByIdAtor(idAtor)) {
			Filme filme = filmeService.buscarFilme(idFilme);
			for (Ator ator : filme.getAtores()) {
				if (ator.getId() == idAtor) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean diretorPertenceAoFilme(int idFilme, int idDiretor) {
		if (existsByIdFilme(idFilme) && diretorController.existsByIdDiretor(idDiretor)) {
			Filme filme = filmeService.buscarFilme(idFilme);
			for (Diretor diretor : filme.getDiretores()) {
				if (diretor.getId() == idDiretor) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean generoPertenceAoFilme(int idFilme, int idGenero) {
		if (existsByIdFilme(idFilme) && generoController.existsByIdGenero(idGenero)) {
			Filme filme = filmeService.buscarFilme(idFilme);
			for (Genero genero : filme.getGeneros()) {
				if (genero.getId() == idGenero) {
					return true;
				}
			}
		}
		return false;
	}
}
