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
		if (!atores.equals(null)) {
			model.addObject("atores", atores);
		}	
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
		Ator ator;
		ator = atorService.buscarAtor(id);	
		if(!ator.equals(null)) {			
			model.addObject("atorRetorno", ator);
		}
		return index();
		
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiAtor(@PathVariable("id") Integer id) {		
		Ator ator;
		ator = atorService.buscarAtor(id);
		if (!ator.equals(null)) {
			atorService.excluirAtor(ator);
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
		ModelAndView model = new ModelAndView("formulario-ator");		
		Ator ator;
		ator = atorService.buscarAtor(id);
		if (ator.equals(null)) {
			model.addObject("ator", ator);
		}								
		return model;
	}
	
}
