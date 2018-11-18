package br.ufc.vev.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.bean.Sala;
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
	private static final Logger logger = Logger.getLogger(String.valueOf(CinemaController.class));


	@SuppressWarnings("finally")
	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(CINEMA);
		try {
			List<Cinema> cinemas = getAllCinema();

			model.addObject("cinemas", cinemas);

		} catch (Exception e) {
			logger.warning("Ocorreu um erro: " + e.getMessage());
		}finally {
			return model;
		}
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesCinema(@PathVariable("id") Integer id){
			
	  ModelAndView model = new ModelAndView("detalhes-cinema");
	  Cinema cinema = cinemaService.buscaCinema(id);

	  model.addObject("salas", salaController.getAllSala());
	  model.addObject(CINEMA, cinema);
			
	  return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioCinema() {
		ModelAndView model = new ModelAndView("formulario-cinema");
		model.addObject(CINEMA, new Cinema());

		return model;
	}

	@PostMapping(path = "/salvar")
	public ModelAndView salvaCinema(Cinema cinema) {
		
		ModelAndView model = new ModelAndView(CINEMA);
		cinemaService.salvarCinema(cinema);
		model.addObject("cinemaRetorno", cinema);
		return index();
		
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaCinema(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView(CINEMA);
		try {
			
			if (cinemaService.existsById(id)) {
				Cinema cinema;

				cinema = cinemaService.buscaCinema(id);

				model.addObject("cinemaRetorno", cinema);
			} else {
				// mensagem de erro "id nao existente no banco"
				logger.info("Id do cinema não existe no banco de dados");
			}
		
		} catch (Exception e) { // caso de erro
			logger.warning("Ocorreu um erro ao buscar cinema: " + e.getMessage());
		} finally { // sempre será execultado
			return index();
		}
	}
	
	public boolean existsByIdCinema(int id) {
		return cinemaService.existsById(id);
	}

	@SuppressWarnings("finally")
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiCinema(@PathVariable("id") Integer id) {		
		try {
			Cinema cinema;
			if (existsByIdCinema(id)) {
				cinema = cinemaService.buscaCinema(id);
				cinemaService.excluiCinema(cinema);
			}
		} catch (Exception e) {
			logger.warning("Ocorreu um erro ao excluir cinema: " + e.getMessage());
		}finally {
			return index();
		}
	}

	public List<Cinema> getAllCinema() {		
		return cinemaService.getAllCinema();
	}

	// o metodo utilizado para atualizar será o salvar, visto que o spring boot ja
				// atualiza automaticamente o objeto passado.
				// este método só redireciona para a digitação dos novos campos do model
	@SuppressWarnings("finally")
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaCinema(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-cinema");

		try {
			if (existsByIdCinema(id)) {
				Cinema cinema = cinemaService.buscaCinema(id);

				model.addObject(CINEMA, cinema);
			}
		} catch (Exception e) {
			logger.warning("Ocorreu um erro ao atualizar cinema: " + e.getMessage());
		} finally {
			return model;
		}
	}
	
	@PostMapping(path="/{idCinema}/adicionarSala")
	public ModelAndView vincularSalaAoCinema(@PathVariable("idCinema") Integer idCinema, 
											@RequestParam Integer idSala){

	  ModelAndView model = new ModelAndView("redirect:/cinema/"+idCinema);
	  
	  if (existsByIdCinema(idCinema) && salaController.existsByIdSala(idSala) && !salaPertenceAoCinema(idCinema, idSala)) {
		  cinemaService.vinculaSalaAoCinema(idCinema, idSala);
	  }
	  
	  return model;
	}
	
	@RequestMapping(path="/{idCinema}/removerSala/{idSala}")
	public ModelAndView desvinculaSalaDoCinema(@PathVariable("idCinema") Integer idCinema, @PathVariable("idSala") Integer idSala) {
		
		ModelAndView model = new ModelAndView("redirect:/cinema/"+idCinema);
		
		if (salaPertenceAoCinema(idCinema, idSala)) {
			cinemaService.desvinculaSalaDoCinema(idCinema, idSala);
		}
		
		return model;
	}
	
	public boolean vinculaSalaAoCinema(int idCine, int idSala) {
			return cinemaService.vinculaSalaAoCinema(idCine, idSala);	
		
	}
	
	public void desvinculaSalaAoCinema(int idCine, int idSala) {			
			cinemaService.desvinculaSalaDoCinema(idCine, idSala);
	}
	
	public boolean salaPertenceAoCinema(int idCinema, int idSala) {
		if (existsByIdCinema(idCinema) && salaController.existsByIdSala(idSala)) {
			Cinema cinema = cinemaService.buscaCinema(idCinema);
			for (Sala sala : cinema.getSalas()) {
				if (sala.getId() == idSala) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}

