package br.ufc.vev.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Diretor;
//import br.ufc.vev.bean.Filme;
import br.ufc.vev.service.DiretorService;
//import br.ufc.vev.service.FilmeService;

@Controller
@RequestMapping(path = "/diretor")
public class DiretorController {
	@Autowired
	private DiretorService diretorService;

	private static final Logger logger = Logger.getLogger(String.valueOf(DiretorController.class));

	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("diretor");
		try {
			List<Diretor> diretores = getAllDiretores();

			model.addObject("diretores", diretores);
			return model;

		} catch (Exception e) {
			logger.warning("Ocorreu um erro: " + e.getMessage());
		}
		return model;
	}

	@RequestMapping("/formulario")
	public ModelAndView formularioDiretor() {
		ModelAndView model = new ModelAndView("formulario-diretor");
		model.addObject("diretor", new Diretor());

		return model;
	}

	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvaDiretor(Diretor diretor) {
		ModelAndView model = new ModelAndView("diretor");
		diretorService.salvarDiretor(diretor);
		model.addObject("diretorRetorno", diretor);
		return index();
		
	}

	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaDiretor(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("diretor");
		try {
			if (existsByIdDiretor(id)) {
				Diretor diretor;
				diretor = diretorService.buscarDiretor(id);
				model.addObject("diretorRetorno", diretor);
			} 
		} catch (Exception e) { // caso de erro
			logger.warning("Ocorreu um erro ao buscar diretor: " + e.getMessage());
		} finally { // sempre será execultado
			return index();
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiDiretor(@PathVariable("id") Integer id) {
		try {
			Diretor diretor;
			if (existsByIdDiretor(id)) {
				diretor = diretorService.buscarDiretor(id);
				diretorService.excluirDiretor(diretor);
			}
		} catch (Exception e) {
			logger.warning("Ocorreu um erro a excluir diretor: " + e.getMessage());
		} finally {
			return index();
		}
	}

	public List<Diretor> getAllDiretores() {
		return diretorService.getAllDiretor();
	}

	// o metodo utilizado para atualizar será o salvar, visto que o spring boot ja
	// atualiza automaticamente o objeto passado.
	// este método só redireciona para a digitação dos novos campos do model
	@SuppressWarnings("finally")
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaDiretor(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-diretor");

		try {
			if (existsByIdDiretor(id)) {
				Diretor diretor = diretorService.buscarDiretor(id);
				model.addObject("diretor", diretor);
			}
		} catch (Exception e) {
			logger.warning("Ocorreu um erro ao atualizar diretor: " + e.getMessage());
		} finally {
			return model;
		}
	}

	public boolean existsByIdDiretor(int id) {
		return diretorService.buscaDiretor(id);
	}

}
