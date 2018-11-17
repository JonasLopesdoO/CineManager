package br.ufc.vev.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.service.SalaService;

@Controller
@RequestMapping(path = "/sala")
public class SalaController {
	
	@Autowired
	private SalaService salaService;

	private static final Logger logger = Logger.getLogger(String.valueOf(SalaController.class));


	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("sala");
		try {
			List<Sala> salas = getAllSala();
			model.addObject("salas", salas);
		} catch (Exception e) {
			logger.warning("Ocorreu um erro: " + e.getMessage());
		}
		return model;
	}

	@RequestMapping("/formulario")
	public ModelAndView formularioGenero() {
		ModelAndView model = new ModelAndView("formulario-sala");
		model.addObject("sala", new Sala());
		return model;
	}

	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvaSala(Sala sala) {
		ModelAndView model = new ModelAndView("sala");
<<<<<<< HEAD

		try {
			if (this.validaSala(sala.getNome(), sala.getCapacidade())) {
				salaService.salvarSala(sala);
				
				model.addObject("salaRetorno", sala);
		 	}
		} catch (Exception e) {
			logger.warning("Ocorreu um erro ao salvar sala: " + e.getMessage());
		}finally {
			return index();
		}
	}
	
	private boolean validaSala(String nome, int capacidade) throws Exception {
=======
		salaService.salvarSala(sala);
		model.addObject("salaRetorno", sala);
		return index();
>>>>>>> origin/master
		
	}
	
<<<<<<< HEAD
	private boolean validaIdSala(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro ID não pode ser negativo");
		}
		return true;
	}

=======
>>>>>>> origin/master
	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaSala(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("sala");
		try {
<<<<<<< HEAD
			if (this.validaIdSala(id)) {
				if (existsByIdSala(id)) {
					Sala sala;

					sala = salaService.buscarSala(id);

					model.addObject("salaRetorno", sala);
				} else {
					logger.info("Sala não existente no banco.");
				}
			} else {
				logger.info("Id de sala inválido");
			}
=======
			if (existsByIdSala(id)) {
				Sala sala = new Sala();
				sala = salaService.buscarSala(id);
				model.addObject("salaRetorno", sala);
			} 
>>>>>>> origin/master
		} catch (Exception e) { // caso de erro
			logger.warning("Ocorreu um erro ao buscar sala: " + e.getMessage());
		} finally { // sempre será execultado
			return index();
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiSala(@PathVariable("id") Integer id) {
		try {
<<<<<<< HEAD
			Sala sala;
			if (validaIdSala(id) && existsByIdSala(id)) {
=======
			Sala sala = new Sala();
			if (existsByIdSala(id)) {
>>>>>>> origin/master
				sala = salaService.buscarSala(id);
				salaService.excluirSala(sala);
			}
		} catch (Exception e) {
			logger.warning("Ocorreu um erro ao excluir sala: " + e.getMessage());
		}finally {
			return index();
		}
	}

	protected List<Sala> getAllSala() {
		return salaService.getAllSala();
	}
	
	// o metodo utilizado para atualizar será o salvar, visto que o spring boot ja
		// atualiza automaticamente o objeto passado.
		// este método só redireciona para a digitação dos novos campos do model
		@SuppressWarnings("finally")
		@RequestMapping("/atualizar/{id}")
		public ModelAndView atualizaSala(@PathVariable("id") Integer id) {
			ModelAndView model = new ModelAndView("formulario-sala");

			try {
				if (existsByIdSala(id)) {
					Sala sala = salaService.buscarSala(id);

					model.addObject("sala", sala);
				}
			} catch (Exception e) {
				logger.warning("Ocorreu um erro ao atualizar sala: " + e.getMessage());
			} finally {
				return model;
			}
		}

	protected boolean existsByIdSala(int id) {
		return salaService.buscaSala(id);
	}

	
	
}
