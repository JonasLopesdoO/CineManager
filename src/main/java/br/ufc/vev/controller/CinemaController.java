package br.ufc.vev.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.service.CinemaService;

@Controller
@Transactional
@Rollback(false)
@RequestMapping(path = "/cinema")
public class CinemaController {
	
	@Autowired
	private CinemaService cinemaService;
	@Autowired
	private SalaController salaController;

	private static final String CINEMA = "cinema";
	
	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(CINEMA);
		List<Cinema> cinemas = getAllCinema();
		if (cinemas != null) {
			model.addObject("cinemas", cinemas);
		}
		return model;
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesCinema(@PathVariable("id") Integer id){		
	  ModelAndView model = new ModelAndView("detalhes-cinema");
	  Cinema cinema = cinemaService.buscaCinema(id);
	  model.addObject("salas", salaController.getAllSala());
	  if (cinema != null) {
		  model.addObject(CINEMA, cinema);
	  }	
	  return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioCinema() {
		ModelAndView model = new ModelAndView("formulario-cinema");
		model.addObject(CINEMA, new Cinema());
		return model;
	}

	@PostMapping(path = "/salvar")
	public ModelAndView salvaCinema(@RequestParam String nome, @RequestParam String endereco, 
										@RequestParam String cidade) {
		ModelAndView model = new ModelAndView(CINEMA);
		Cinema cinema = new Cinema(nome, endereco, cidade);
		cinemaService.salvarCinema(cinema);
		model.addObject("cinemaRetorno", cinema);
		return index();
	}
	
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaCinema(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView(CINEMA);
		Cinema cinema;
		cinema = cinemaService.buscaCinema(id);
		if (cinema != null) {
			model.addObject("cinemaRetorno", cinema);
		}
		return index();
	}
	
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiCinema(@PathVariable("id") Integer id) {		
		Cinema cinema;
		cinema = cinemaService.buscaCinema(id);
		if (cinema != null) {
			cinemaService.excluiCinema(cinema);
		}
		return index();
	}

	public List<Cinema> getAllCinema() {		
		return cinemaService.getAllCinema();
	}

	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaCinema(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-cinema");
		Cinema cinema = cinemaService.buscaCinema(id);
		if (cinema != null) {
			model.addObject(CINEMA, cinema);
		}
		return model;
	}
	
	@PostMapping(path="/{idCinema}/adicionarSala")
	public ModelAndView vincularSalaAoCinema(@PathVariable("idCinema") Integer idCinema, 
											@RequestParam Integer idSala){

	  ModelAndView model = new ModelAndView("redirect:/cinema/"+idCinema);
	  cinemaService.vinculaSalaAoCinema(idCinema, idSala);
	  return model;
	}
	
	@RequestMapping(path="/{idCinema}/removerSala/{idSala}")
	public ModelAndView desvinculaSalaDoCinema(@PathVariable("idCinema") Integer idCinema, @PathVariable("idSala") Integer idSala) {
		
		ModelAndView model = new ModelAndView("redirect:/cinema/"+idCinema);
		cinemaService.desvinculaSalaDoCinema(idCinema, idSala);
		return model;
	}
		
}

