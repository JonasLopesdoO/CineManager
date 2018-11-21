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
<<<<<<< HEAD
	
	private static final String GENERO = "genero";
	private static final String GENEROS = "generos";
	
	@RequestMapping(path="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(GENERO);
		List<Genero> generos = getAllGenero();
		if (generos != null) {
			model.addObject(GENEROS, generos);
		}	
=======
	
	@RequestMapping(path="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("genero");
		try {
			List<Genero> generos = getAllGenero();
			
			model.addObject("generos", generos);
			return model;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioGenero() {
		ModelAndView model = new ModelAndView("formulario-genero");
		model.addObject(GENERO, new Genero());
		return model;
	}
	
	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public ModelAndView salvaGenero(Genero genero) {
		ModelAndView model = new ModelAndView(GENERO);
		generoService.salvarGenero(genero);	
		model.addObject("generoRetorno", genero);
		List<Genero> generos = getAllGenero();
		model.addObject(GENEROS, generos);
		return model;
		
	}

	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaGenero(@PathVariable Integer id) {
<<<<<<< HEAD
		ModelAndView model = new ModelAndView(GENERO);
		Genero genero;
		genero = generoService.buscarGenero(id);
		if(genero != null)
			model.addObject("generoRetorno", genero);
		
		List<Genero> generos = generoService.getAllGenero();
		model.addObject(GENEROS, generos);
		return model;
=======
		ModelAndView model = new ModelAndView("genero");
		try {
			if(existsByIdGenero(id)) {
				Genero genero = new Genero();	
				genero = generoService.buscarGenero(id);
				model.addObject("generoRetorno", genero);
			}	
		} catch (Exception e) { 	// caso de erro 
			e.printStackTrace();
		} finally { // sempre será execultado
			List<Genero> generos = generoService.getAllGenero();
			model.addObject("generos", generos);
			return model;
		}
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluiGenero(@PathVariable("id") Integer id) {
<<<<<<< HEAD
		ModelAndView model = new ModelAndView(GENERO);
		Genero genero;
		genero = generoService.buscarGenero(id);
		if(genero != null)
			generoService.excluirGenero(genero);
		 		
		List<Genero> generos = generoService.getAllGenero();
		model.addObject(GENEROS, generos);
		return model;
=======
		ModelAndView model = new ModelAndView("genero");
		try {
			Genero genero = new Genero();
			if (existsByIdGenero(id)) { 
				genero = generoService.buscarGenero(id);
				generoService.excluirGenero(genero);
		 	}
			
		} catch (Exception e) { 	// caso de erro 
			e.printStackTrace();
		} finally { // sempre será execultado
			List<Genero> generos = generoService.getAllGenero();
			model.addObject("generos", generos);
			return model;
		}
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	}

	public List<Genero> getAllGenero() {		
		return generoService.getAllGenero();
	}

	//o metodo utilizado para atualizar será o salvar, visto que o spring boot ja atualiza automaticamente o objeto passado.
	//este método só redireciona para a digitação dos novos campos do model
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaGenero(@PathVariable int id) {
		ModelAndView model = new ModelAndView("formulario-genero");
<<<<<<< HEAD
		Genero genero;
		genero = generoService.buscarGenero(id);
		if(genero != null)		
			model.addObject(GENERO, genero);
=======
		try {
			if (existsByIdGenero(id)) {
				Genero genero = generoService.buscarGenero(id);
				
				model.addObject("genero", genero);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return model;
		}
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		
		return model;		
	}
	
	public Genero buscaPorNome(String nome) {
		return generoService.buscaPorNome(nome);
	}
	
}
