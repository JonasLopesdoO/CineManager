package br.ufc.vev.controller;

import java.util.List;

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

@Controller
@Transactional
@Rollback(false)
@RequestMapping(path = "/cinema")
public class CinemaController {
	
	@Autowired
	private CinemaService cinemaService;
	@Autowired
	private SalaController salaController;
	
	@SuppressWarnings("finally")
	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("cinema");
		try {
			List<Cinema> cinemas = getAllCinema();

			model.addObject("cinemas", cinemas);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return model;
		}
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesCinema(@PathVariable("id") Integer id){
			
	  ModelAndView model = new ModelAndView("detalhes-cinema");
	  Cinema cinema = cinemaService.buscaCinema(id);

	  model.addObject("salas", salaController.getAllSala());
	  model.addObject("cinema", cinema);
			
	  return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioCinema() {
		ModelAndView model = new ModelAndView("formulario-cinema");
		model.addObject("cinema", new Cinema());

		return model;
	}

	@SuppressWarnings("finally")
	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvaCinema(Cinema cinema) {
		ModelAndView model = new ModelAndView("cinema");

		try {
			if (this.validaCinema(cinema.getNome(), cinema.getEndereco(), cinema.getEndereco())) {
				cinemaService.salvarCinema(cinema);

				model.addObject("cinemaRetorno", cinema);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaCinema(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("cinema");
		try {
			if (this.validaId(id)) {
				if (existsByIdCinema(id)) {
					Cinema cinema = new Cinema();

					cinema = cinemaService.buscaCinema(id);

					model.addObject("cinemaRetorno", cinema);
				} else {
					// mensagem de erro "id nao existente no banco"
				}
			} else {
				// msg de id invalido
			}
		} catch (Exception e) { // caso de erro
			e.printStackTrace();
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
			Cinema cinema = new Cinema();
			if (validaId(id) && existsByIdCinema(id)) {
				cinema = cinemaService.buscaCinema(id);
				cinemaService.excluiCinema(cinema);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		} finally {
			return model;
		}
	}
	
	@RequestMapping(path="/{idCinema}/adicionarSala", method=RequestMethod.POST)
	public ModelAndView vincularSalaAoCinema(@PathVariable("idCinema") Integer idCinema, 
											@RequestParam Integer idSala){

	  ModelAndView model = new ModelAndView("redirect:/cinema/"+idCinema);
	  
	  if (existsByIdCinema(idCinema) && salaController.existsByIdSala(idSala) && salaPertenceAoCinema(idCinema, idSala) == false) {
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
		try {
			if (validaId(idCine) && validaId(idSala)) {
				return cinemaService.vinculaSalaAoCinema(idCine, idSala);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void desvinculaSalaAoCinema(int idCine, int idSala) {
		try {
			if (validaId(idCine) && validaId(idSala)) {
				cinemaService.desvinculaSalaDoCinema(idCine, idSala);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean validaCinema(String nome, String cidade, String endereco) throws Exception {
		
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} else if (endereco.equals("")) {
			throw new Exception("Endereco não pode ser vazio");
		} else if (endereco.equals(null)) {
			throw new Exception("Endereco não pode ser nulo");
		} else if (cidade.equals("")) {
			throw new Exception("Cidade não pode ser vazio");
		} else if (cidade.equals(null)) {
			throw new Exception("Cidade não pode ser nulo");
		}
		return true;
	}
	
	public boolean validaId(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro ID não pode ser negativo");
		}
		return true;
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

