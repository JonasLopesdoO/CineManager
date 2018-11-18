package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Diretor;
//import br.ufc.vev.bean.Filme;
import br.ufc.vev.service.DiretorService;


@Controller
@RequestMapping(path = "/diretor")
public class DiretorController {
	@Autowired
	private DiretorService diretorService;

	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("diretor");
		List<Diretor> diretores = getAllDiretores();
		if (!diretores.equals(null)) {
			model.addObject("diretores", diretores);
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

	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaDiretor(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("diretor");
		Diretor diretor;
		diretor = diretorService.buscarDiretor(id);
		if (!diretor.equals(null)) {
			model.addObject("diretorRetorno", diretor);
		}
		return index();
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiDiretor(@PathVariable("id") Integer id) {
		Diretor diretor;
		diretor = diretorService.buscarDiretor(id);
		if (!diretor.equals(null)) {
			diretorService.excluirDiretor(diretor);
		}
		return index();
	}

	public List<Diretor> getAllDiretores() {
		return diretorService.getAllDiretor();
	}

	// o metodo utilizado para atualizar será o salvar, visto que o spring boot ja
	// atualiza automaticamente o objeto passado.
	// este método só redireciona para a digitação dos novos campos do model
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaDiretor(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-diretor");
		Diretor diretor;
		diretor = diretorService.buscarDiretor(id);
		if (!diretor.equals(null)) {
			model.addObject("diretor", diretor);
		}
		return model;
	}

}
