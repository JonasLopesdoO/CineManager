package br.ufc.vev.controller;

import java.util.List;
import java.util.logging.Logger;

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
import br.ufc.vev.service.AtorService;
import br.ufc.vev.service.DiretorService;
import br.ufc.vev.service.FilmeService;
import br.ufc.vev.service.GeneroService;

@Controller
@Transactional
@Rollback(false)
@RequestMapping(path = "/filme")
public class FilmeController {
	
	@Autowired
	FilmeService filmeService;
	@Autowired
	AtorService atorService;
	@Autowired
	DiretorService diretorService;
	@Autowired
	GeneroService generoService;

	private static final Logger logger = Logger.getLogger(String.valueOf(FilmeController.class));


	@SuppressWarnings("finally")
	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("filme");
		try {
			List<Filme> filmes = getAllFilme();
			model.addObject("filmes", filmes);
		} catch (Exception e) {
			logger.warning("Ocorreu um erro: " + e.getMessage());
		}finally {
			return model;
		}
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesFilme(@PathVariable("id") Integer id){
			
	  ModelAndView model = new ModelAndView("detalhes-filme");
	  Filme filme = filmeService.buscarFilme(id);

	  model.addObject("atores", atorService.getAllAtor());
	  model.addObject("diretores", diretorService.getAllDiretor());
	  model.addObject("generos", generoService.getAllGenero());
	  model.addObject("filme", filme);
			
	  return model;
	}

	@RequestMapping("/formulario")
	public ModelAndView formularioFilme() {
		ModelAndView model = new ModelAndView("formulario-filme");
		model.addObject("filme", new Filme());

		return model;
	}

	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvaFilme(Filme filme) {
		ModelAndView model = new ModelAndView("filme");
		filmeService.salvarFilme(filme);
		model.addObject("filmeRetorno", filme);
		return index();

	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaFilme(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("filme");
		try {
			if (existsByIdFilme(id)) {
				Filme filme;
				filme = filmeService.buscarFilme(id);
				model.addObject("filmeRetorno", filme);
			} 
		} catch (Exception e) { // caso de erro
			logger.warning("Ocorreu um erro ao buscar filme: " + e.getMessage());
		} finally { // sempre será execultado
			return index();
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiFilme(@PathVariable("id") Integer id) {		
		try {
			Filme filme;
			if (existsByIdFilme(id)) {
				filme = filmeService.buscarFilme(id);
				filmeService.excluirFilme(filme);
			}
		} catch (Exception e) {
			logger.warning("Ocorreu um erro ao excluir filme: " + e.getMessage());
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
			logger.warning("Ocorreu um erro ao atualizar filme: " + e.getMessage());
		} finally {
			return model;
		}
	}
	
	@RequestMapping(path="/{idFilme}/adicionarAtor", method=RequestMethod.POST)
	public ModelAndView vincularAtorAoFilme(@PathVariable("idFilme") Integer idFilme, 
											@RequestParam Integer idAtor ){

	  ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
	  
	  if (existsByIdFilme(idFilme) && atorService.buscaAtor(idAtor) && !atorPertenceAoFilme(idFilme, idAtor)) {
		  filmeService.vinculaAtorAoFilme(idFilme, idAtor);
	  }
	  
	  return model;
	}
	
	@RequestMapping(path="/{idFilme}/removerAtor/{idAtor}")
	public ModelAndView desvinculaAtorDoFilme(@PathVariable("idFilme") Integer idFilme, 
										@PathVariable("idAtor") Integer idAtor) {
		
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
	  if (existsByIdFilme(idFilme) && diretorService.buscaDiretor(idDiretor) && !diretorPertenceAoFilme(idFilme, idDiretor)){
		  filmeService.vinculaDiretorAoFilme(idFilme, idDiretor);
	  }
	  
	  return model;
	}
	
	@RequestMapping(path="/{idFilme}/removerDiretor/{idDiretor}")
	public ModelAndView desvinculaDiretorDoFilme(@PathVariable("idFilme") Integer idFilme, 
											@PathVariable("idDiretor") Integer idDiretor) {
		
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
		if (existsByIdFilme(idFilme) && generoService.buscaGenero(idGenero) && !generoPertenceAoFilme(idFilme, idGenero)){
		  filmeService.vinculaGeneroAoFilme(idFilme, idGenero);
		}
		  
		return model;
	}
	
	@RequestMapping(path="/{idFilme}/removerGenero/{idGenero}")
	public ModelAndView desvinculaGeneroDoFilme(
					@PathVariable("idFilme") Integer idFilme, 
						@PathVariable("idGenero") Integer idGenero) {
		ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
		if(generoPertenceAoFilme(idFilme, idGenero)) {
			filmeService.desvinculaGeneroDoFilme(idFilme, idGenero);
		}
		return model;
	}

	public boolean existsByIdFilme(int id) {
		return filmeService.existsById(id);
	}
	
	public boolean atorPertenceAoFilme(int idFilme, int idAtor) {
	
		Filme filme = filmeService.buscarFilme(idFilme);
		Ator ator = atorService.buscarAtor(idAtor);
		return filme.getAtores().contains(ator);
		
	}
	
	public boolean diretorPertenceAoFilme(int idFilme, int idDiretor) {
		Filme filme = filmeService.buscarFilme(idFilme);
		Diretor diretor = diretorService.buscarDiretor(idDiretor);
		return filme.getDiretores().contains(diretor);
		
	}
	
	public boolean generoPertenceAoFilme(int idFilme, int idGenero) {
		if (existsByIdFilme(idFilme) && generoService.buscaGenero(idGenero)) {
			Filme filme = filmeService.buscarFilme(idFilme);
			Genero genero = generoService.buscarGenero(idGenero);
			return filme.getGeneros().contains(genero);
		}
		return false;
	}
	
	public Filme buscarPorNome(String nome) {
		return filmeService.bucarPorNome(nome);
	}
}
