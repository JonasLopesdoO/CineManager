package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.service.SalaService;

@Controller
@RequestMapping(path = "/sala")
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	
	private static final String SALA = "sala";

	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(SALA);
		List<Sala> salas = getAllSala();
		if (salas != null) {
			model.addObject("salas", salas);
		}
		return model;
	}

	@RequestMapping("/formulario")
	public ModelAndView formularioGenero() {
		ModelAndView model = new ModelAndView("formulario-sala");
		model.addObject(SALA, new Sala());
		return model;
	}

	@PostMapping(path = "/salvar")
	public ModelAndView salvaSala(Sala sala) {
		ModelAndView model = new ModelAndView(SALA);
		salaService.salvarSala(sala);
		model.addObject("salaRetorno", sala);
		return index();
	}
	
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaSala(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView(SALA);
		Sala sala;	
		sala = salaService.buscarSala(id);
		if (sala != null) {
			model.addObject("salaRetorno", sala);
		}			
		return index();
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiSala(@PathVariable("id") Integer id) {
		Sala sala;
		sala = salaService.buscarSala(id);
		if (sala != null) {
			salaService.excluirSala(sala);
		}
		return index();
	}

	public List<Sala> getAllSala() {
		return salaService.getAllSala();
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaSala(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-sala");
		Sala sala = salaService.buscarSala(id);
		if (sala != null) {
			model.addObject(SALA, sala);
		}
		return model;	
	}
	
}
