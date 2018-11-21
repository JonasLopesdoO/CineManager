package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.service.AtorService;


@Controller
@RequestMapping(path= "/ator")
public class AtorController {
	
	@Autowired
	private AtorService atorService;
	
	@RequestMapping(path="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("ator");
		List<Ator> atores = getAllAtor();
		if (atores != null) {
			model.addObject("atores", atores);
<<<<<<< HEAD
		}	
=======
			return model;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioAtor() {
		ModelAndView model = new ModelAndView("formulario-ator");
		model.addObject("ator", new Ator());	
		return model;
	}

	@PostMapping(path="/salvar")
	public ModelAndView salvaAtor(Ator ator) {
		ModelAndView model = new ModelAndView("ator");
		atorService.salvarAtor(ator);
		model.addObject("atorRetorno", ator);
		return index();
	}
	
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaAtor(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("ator");
<<<<<<< HEAD
		Ator ator;
		ator = atorService.buscarAtor(id);	
		if(ator != null) {			
			model.addObject("atorRetorno", ator);
=======
		try {	
			if(existsByIdAtor(id)) {
				Ator ator = new Ator();			
				ator = atorService.buscarAtor(id);			
				model.addObject("atorRetorno", ator);
			}
		} catch (Exception e) { 	// caso de erro 
			e.printStackTrace();
		} finally { // sempre será execultado
			return index();
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		}
		return index();
		
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiAtor(@PathVariable("id") Integer id) {		
<<<<<<< HEAD
		Ator ator;
		ator = atorService.buscarAtor(id);
		if (ator != null) {
			atorService.excluirAtor(ator);
=======
		try {
			Ator ator = new Ator();
			if (existsByIdAtor(id)) {
				ator = atorService.buscarAtor(id);
				atorService.excluirAtor(ator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		}
		return index();
	}

	public List<Ator> getAllAtor() {		
		return atorService.getAllAtor();
	}
	
	//o metodo utilizado para atualizar será o salvar, visto que o spring boot ja atualiza automaticamente o objeto passado.
	//este método só redireciona para a digitação dos novos campos do model
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaAtor(@PathVariable("id") Integer id) {
<<<<<<< HEAD
		ModelAndView model = new ModelAndView("formulario-ator");		
		Ator ator;
		ator = atorService.buscarAtor(id);
		if (ator != null) {
			model.addObject("ator", ator);
		}								
		return model;
=======
		ModelAndView model = new ModelAndView("formulario-ator");

		try {
			if (existsByIdAtor(id)) {
				Ator ator = atorService.buscarAtor(id);
				
				model.addObject("ator", ator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return model;
		}
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	}
	
}
