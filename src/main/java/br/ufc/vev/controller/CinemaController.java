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

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.service.CinemaService;
import br.ufc.vev.service.SalaService;

@Controller
@Transactional
@Rollback(false)
@RequestMapping(path = "/cinema")
public class CinemaController {
	
	@Autowired
	private CinemaService cinemaService;
	@Autowired
<<<<<<< HEAD
	private SalaController salaController;

	private static final Logger logger = Logger.getLogger(String.valueOf(CinemaController.class));


=======
	private SalaService salaService;
	
>>>>>>> origin/master
	@SuppressWarnings("finally")
	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("cinema");
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

	  model.addObject("salas", salaService.getAllSala());
	  model.addObject("cinema", cinema);
			
	  return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioCinema() {
		ModelAndView model = new ModelAndView("formulario-cinema");
		model.addObject("cinema", new Cinema());

		return model;
	}

	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvaCinema(Cinema cinema) {
		
		ModelAndView model = new ModelAndView("cinema");
		cinemaService.salvarCinema(cinema);
		model.addObject("cinemaRetorno", cinema);
		return index();
		
	}

	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaCinema(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("cinema");
<<<<<<< HEAD
		try {
			if (this.validaId(id)) {
				if (cinemaService.existsById(id)) {
					Cinema cinema;

					cinema = cinemaService.buscaCinema(id);

					model.addObject("cinemaRetorno", cinema);
				} else {
					// mensagem de erro "id nao existente no banco"
					logger.info("Id do cinema não existe no banco de dados");
				}
			} else {
				// msg de id invalido
				logger.info("Id inválido");
			}
		} catch (Exception e) { // caso de erro
			logger.warning("Ocorreu um erro ao buscar cinema: " + e.getMessage());
		} finally { // sempre será execultado
			return index();
		}
=======
			if (cinemaService.existsById(id)) { 
				Cinema cinema = new Cinema();
				cinema = cinemaService.buscaCinema(id);
				model.addObject("cinemaRetorno", cinema);
			}
		return index();
		
>>>>>>> origin/master
	}
	
	public boolean existsByIdCinema(int id) {
		return cinemaService.existsById(id);
	}

	@SuppressWarnings("finally")
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiCinema(@PathVariable("id") Integer id) {		
		try {
<<<<<<< HEAD
			Cinema cinema;
			if (validaId(id) && existsByIdCinema(id)) {
=======
			Cinema cinema = new Cinema();
			if (existsByIdCinema(id)) {
>>>>>>> origin/master
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
				model.addObject("cinema", cinema);
			}
		} catch (Exception e) {
			logger.warning("Ocorreu um erro ao atualizar cinema: " + e.getMessage());
		} finally {
			return model;
		}
	}
	
	@RequestMapping(path="/{idCinema}/adicionarSala", method=RequestMethod.POST)
	public ModelAndView vincularSalaAoCinema(@PathVariable("idCinema") Integer idCinema, 
											@RequestParam Integer idSala){

	  ModelAndView model = new ModelAndView("redirect:/cinema/"+idCinema);
	  cinemaService.vinculaSalaAoCinema(idCinema, idSala);
	  
<<<<<<< HEAD
	  if (existsByIdCinema(idCinema) && salaController.existsByIdSala(idSala) && !salaPertenceAoCinema(idCinema, idSala)) {
		  cinemaService.vinculaSalaAoCinema(idCinema, idSala);
	  }
=======
>>>>>>> origin/master
	  
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
	
<<<<<<< HEAD
	public boolean vinculaSalaAoCinema(int idCine, int idSala) {
		try {
			if (validaId(idCine) && validaId(idSala)) {
				return cinemaService.vinculaSalaAoCinema(idCine, idSala);
			}
		} catch (Exception e) {
			logger.warning("Ocorreu um erro ao vincular a sala no cinema: " + e.getMessage());
		}
		return false;
	}
	
	public void desvinculaSalaAoCinema(int idCine, int idSala) {
		try {
			if (validaId(idCine) && validaId(idSala)) {
				cinemaService.desvinculaSalaDoCinema(idCine, idSala);
			}
		} catch (Exception e) {
			logger.warning("Ocorreu um erro ao desvincular sala do cinema: " + e.getMessage());
		}
	}
	
	public boolean validaId(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro ID não pode ser negativo");
		}
		return true;
	}
	
=======
>>>>>>> origin/master
	public boolean salaPertenceAoCinema(int idCinema, int idSala) {
		if (existsByIdCinema(idCinema) && salaService.buscaSala(idSala)) {
			Cinema cinema = cinemaService.buscaCinema(idCinema);
			Sala sala = salaService.buscarSala(idSala);
			return cinema.getSalas().contains(sala);
		}
		return false;
	}
		
}

