package br.ufc.vev.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Filme;
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
	
<<<<<<< HEAD
	private static final String FILME = "filme";
	private static final String REDIRECT = "redirect:/filme/";

=======
	@SuppressWarnings("finally")
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(FILME);
		List<Filme> filmes = getAllFilme();
		if (filmes != null) {
			model.addObject("filmes", filmes);
<<<<<<< HEAD
=======
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return model;
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		}
		return model;
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesFilme(@PathVariable("id") Integer id){		
	  ModelAndView model = new ModelAndView("detalhes-filme");
	  Filme filme = filmeService.buscarFilme(id);
	  model.addObject("atores", atorService.getAllAtor());
	  model.addObject("diretores", diretorService.getAllDiretor());
	  model.addObject("generos", generoService.getAllGenero());
	  model.addObject(FILME, filme);
	  return model;
	}

	@RequestMapping("/formulario")
	public ModelAndView formularioFilme() {
		ModelAndView model = new ModelAndView("formulario-filme");
		model.addObject(FILME, new Filme());
		return model;
	}

	@PostMapping(path = "/salvar")
	public ModelAndView salvaFilme(Filme filme) {
		ModelAndView model = new ModelAndView(FILME);
		filmeService.salvarFilme(filme);
		model.addObject("filmeRetorno", filme);
		return index();
	}
	
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaFilme(@PathVariable Integer id) {
<<<<<<< HEAD
		ModelAndView model = new ModelAndView(FILME);
		Filme filme;
		filme = filmeService.buscarFilme(id);
		if (filme != null) {
			model.addObject("filmeRetorno", filme);
=======
		ModelAndView model = new ModelAndView("filme");
		try {
			if (existsByIdFilme(id)) {
				Filme filme = new Filme();
				filme = filmeService.buscarFilme(id);
				model.addObject("filmeRetorno", filme);
			} 
		} catch (Exception e) { // caso de erro
			e.printStackTrace();
		} finally { // sempre será execultado
			return index();
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		}
		return index();
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiFilme(@PathVariable("id") Integer id) {		
<<<<<<< HEAD
		Filme filme;
		filme = filmeService.buscarFilme(id);
		if (filme != null) {
			filmeService.excluirFilme(filme);
		}			
		return index();
=======
		try {
			Filme filme = new Filme();
			if (existsByIdFilme(id)) {
				filme = filmeService.buscarFilme(id);
				filmeService.excluirFilme(filme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	}

	public List<Filme> getAllFilme() {		
		return filmeService.getAllFilme();
	}

	// o metodo utilizado para atualizar será o salvar, visto que o spring boot ja
			// atualiza automaticamente o objeto passado.
			// este método só redireciona para a digitação dos novos campos do model
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaFilme(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-filme");
<<<<<<< HEAD
		Filme filme = filmeService.buscarFilme(id);
		if (filme != null) {
			model.addObject(FILME, filme);
		}	
		return model;
=======

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
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	}
	
	@RequestMapping(path="/{idFilme}/adicionarAtor", method=RequestMethod.POST)
	public ModelAndView vincularAtorAoFilme(@PathVariable("idFilme") Integer idFilme, 
											@RequestParam Integer idAtor ){
<<<<<<< HEAD
		
	  ModelAndView model = new ModelAndView(REDIRECT + idFilme);
	  filmeService.vinculaAtorAoFilme(idFilme, idAtor);
=======

	  ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
	  
	  if (existsByIdFilme(idFilme) && atorService.buscaAtor(idAtor) 
			  			&& atorPertenceAoFilme(idFilme, idAtor) != true) {
		  filmeService.vinculaAtorAoFilme(idFilme, idAtor);
	  }
	  
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	  return model;
	}
	
	@RequestMapping(path="/{idFilme}/removerAtor/{idAtor}")
	public ModelAndView desvinculaAtorDoFilme(@PathVariable("idFilme") Integer idFilme, 
										@PathVariable("idAtor") Integer idAtor) {
		
		ModelAndView model = new ModelAndView(REDIRECT + idFilme);
		filmeService.desvinculaAtorDoFilme(idFilme, idAtor);
		return model;
	}
	
	@RequestMapping(path="/{idFilme}/adicionarDiretor", method=RequestMethod.POST)
	public ModelAndView vincularDiretorAoFilme(@PathVariable("idFilme") Integer idFilme, 
											@RequestParam Integer idDiretor ){

<<<<<<< HEAD
	  ModelAndView model = new ModelAndView(REDIRECT + idFilme);
	  filmeService.vinculaDiretorAoFilme(idFilme, idDiretor);
=======
	  ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
	  if (existsByIdFilme(idFilme) && diretorService.buscaDiretor(idDiretor) 
			  	&& diretorPertenceAoFilme(idFilme, idDiretor) != true){
		  filmeService.vinculaDiretorAoFilme(idFilme, idDiretor);
	  }
	  
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	  return model;
	}
	
	@RequestMapping(path="/{idFilme}/removerDiretor/{idDiretor}")
	public ModelAndView desvinculaDiretorDoFilme(@PathVariable("idFilme") Integer idFilme, 
											@PathVariable("idDiretor") Integer idDiretor) {
		
		ModelAndView model = new ModelAndView(REDIRECT + idFilme);
		filmeService.desvinculaDiretorDoFilme(idFilme, idDiretor);
		return model;
	}
	
	@RequestMapping(path="/{idFilme}/adicionarGenero", method=RequestMethod.POST)
	public ModelAndView vincularGeneroAoFilme(@PathVariable("idFilme") Integer idFilme, 
												@RequestParam Integer idGenero){

<<<<<<< HEAD
		ModelAndView model = new ModelAndView(REDIRECT + idFilme);
		filmeService.vinculaGeneroAoFilme(idFilme, idGenero);
=======
		ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
		if (existsByIdFilme(idFilme) && generoService.buscaGenero(idGenero) 
					&& generoPertenceAoFilme(idFilme, idGenero) != true){
		  filmeService.vinculaGeneroAoFilme(idFilme, idGenero);
		}
		  
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		return model;
	}
	
	@RequestMapping(path="/{idFilme}/removerGenero/{idGenero}")
	public ModelAndView desvinculaGeneroDoFilme(
					@PathVariable("idFilme") Integer idFilme, 
						@PathVariable("idGenero") Integer idGenero) {
<<<<<<< HEAD
		ModelAndView model = new ModelAndView(REDIRECT + idFilme);
		filmeService.desvinculaGeneroDoFilme(idFilme, idGenero);
=======
		ModelAndView model = new ModelAndView("redirect:/filme/"+idFilme);
		if(generoPertenceAoFilme(idFilme, idGenero)) {
			filmeService.desvinculaGeneroDoFilme(idFilme, idGenero);;
		}
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		return model;
	}

	public Filme buscarPorNome(String nome) {
		return filmeService.bucarPorNome(nome);
	}
}
