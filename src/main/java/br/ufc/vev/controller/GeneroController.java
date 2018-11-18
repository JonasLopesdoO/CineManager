package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Genero;
import br.ufc.vev.service.GeneroService;

@Controller
@RequestMapping(path= "/genero")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	@RequestMapping(path="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("genero");
		List<Genero> generos = getAllGenero();
		if (!generos.equals(null)) {
			model.addObject("generos", generos);
		}	
		return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioGenero() {
		ModelAndView model = new ModelAndView("formulario-genero");
		model.addObject("genero", new Genero());
		return model;
	}
	
	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public ModelAndView salvaGenero(Genero genero) {
		ModelAndView model = new ModelAndView("genero");
		generoService.salvarGenero(genero);	
		model.addObject("generoRetorno", genero);
		List<Genero> generos = getAllGenero();
		model.addObject("generos", generos);
		return model;
		
	}

	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaGenero(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("genero");
		Genero genero;
		genero = generoService.buscarGenero(id);
		if(!genero.equals(null))
			model.addObject("generoRetorno", genero);
		
		List<Genero> generos = generoService.getAllGenero();
		model.addObject("generos", generos);
		return model;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluiGenero(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("genero");
		Genero genero;
		genero = generoService.buscarGenero(id);
		if(!genero.equals(null))
			generoService.excluirGenero(genero);
		 		
		List<Genero> generos = generoService.getAllGenero();
		model.addObject("generos", generos);
		return model;
	}

	public List<Genero> getAllGenero() {		
		return generoService.getAllGenero();
	}

	//o metodo utilizado para atualizar será o salvar, visto que o spring boot ja atualiza automaticamente o objeto passado.
	//este método só redireciona para a digitação dos novos campos do model
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaGenero(@PathVariable int id) {
		ModelAndView model = new ModelAndView("formulario-genero");
		Genero genero;
		genero = generoService.buscarGenero(id);
		if(!genero.equals(null))		
			model.addObject("genero", genero);
		
		return model;		
	}
	
	protected Genero buscaPorNome(String nome) {
		return generoService.buscaPorNome(nome);
	}
	
}
