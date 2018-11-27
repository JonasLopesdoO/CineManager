package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Genero;
import br.ufc.vev.service.GeneroService;

@Controller
@RequestMapping(path= "/genero")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	private static final String GENERO = "genero";
	private static final String GENEROS = "generos";
	
	@RequestMapping(path="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(GENERO);
		List<Genero> generos = getAllGenero();
		if (generos != null) {
			model.addObject(GENEROS, generos);
		}	
		return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioGenero() {
		ModelAndView model = new ModelAndView("formulario-genero");
		model.addObject(GENERO, new Genero());
		return model;
	}
	
	@PostMapping(path="/salvar")
	public ModelAndView salvaGenero(@RequestParam String nome) {
		ModelAndView model = new ModelAndView(GENERO);
		Genero genero = new Genero(nome);
		generoService.salvarGenero(genero);	
		model.addObject("generoRetorno", genero);
		List<Genero> generos = getAllGenero();
		model.addObject(GENEROS, generos);
		return model;
		
	}

	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaGenero(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView(GENERO);
		Genero genero;
		genero = generoService.buscarGenero(id);
		if(genero != null)
			model.addObject("generoRetorno", genero);
		
		List<Genero> generos = generoService.getAllGenero();
		model.addObject(GENEROS, generos);
		return model;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluiGenero(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView(GENERO);
		Genero genero;
		genero = generoService.buscarGenero(id);
		if(genero != null)
			generoService.excluirGenero(genero);
		 		
		List<Genero> generos = generoService.getAllGenero();
		model.addObject(GENEROS, generos);
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
		if(genero != null)		
			model.addObject(GENERO, genero);
		
		return model;		
	}
	
	protected Genero buscaPorNome(String nome) {
		return generoService.buscaPorNome(nome);
	}
	
}
