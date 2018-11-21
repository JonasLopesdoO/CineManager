package br.ufc.vev.controller;

import java.util.List;

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
	
<<<<<<< HEAD
	private static final String SALA = "sala";

=======
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(SALA);
		List<Sala> salas = getAllSala();
		if (salas != null) {
			model.addObject("salas", salas);
<<<<<<< HEAD
=======
			return model;

		} catch (Exception e) {
			e.printStackTrace();
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		}
		return model;
	}

	@RequestMapping("/formulario")
	public ModelAndView formularioGenero() {
		ModelAndView model = new ModelAndView("formulario-sala");
		model.addObject(SALA, new Sala());
		return model;
	}

	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvaSala(Sala sala) {
<<<<<<< HEAD
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
=======
		ModelAndView model = new ModelAndView("sala");

		try {
			if (this.validaSala(sala.getNome(), sala.getCapacidade())) {
				salaService.salvarSala(sala);
				
				model.addObject("salaRetorno", sala);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
	}
	
	public boolean validaSala(String nome, int capacidade) throws Exception {
		
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} else if (capacidade <= 0) {
			throw new Exception("Quantidades de lugares não pode ser menor ou igual a zero");
		}	 
		return true;
	}
	
	public boolean validaIdSala(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro ID não pode ser negativo");
		}
		return true;
	}

	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaSala(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("sala");
		try {
			if (this.validaIdSala(id)) {
				if (existsByIdSala(id)) {
					Sala sala = new Sala();

					sala = salaService.buscarSala(id);

					model.addObject("salaRetorno", sala);
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
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiSala(@PathVariable("id") Integer id) {
<<<<<<< HEAD
		Sala sala;
		sala = salaService.buscarSala(id);
		if (sala != null) {
			salaService.excluirSala(sala);
=======
		try {
			Sala sala = new Sala();
			if (validaIdSala(id) && existsByIdSala(id)) {
				sala = salaService.buscarSala(id);
				salaService.excluirSala(sala);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		}
		return index();
	}

<<<<<<< HEAD
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
=======
	public List<Sala> getAllSala() {		
		return salaService.getAllSala();
	}
	
//	public List<Sala> getAllSalasVazias() {		
//		return salaService.getAllSalasVazias();
//	}

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
				e.printStackTrace();
			} finally {
				return model;
			}
		}

	public boolean existsByIdSala(int id) {
		return salaService.buscaSala(id);
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	}
	
}
