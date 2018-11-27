package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Diretor;
import br.ufc.vev.service.DiretorService;


@Controller
@RequestMapping(path = "/diretor")
public class DiretorController {
	@Autowired
	private DiretorService diretorService;
	
	private static final String DIRETOR = "diretor";

	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(DIRETOR);
		List<Diretor> diretores = getAllDiretores();
		if (diretores != null) {
			model.addObject("diretores", diretores);
		}
		return model;
	}

	@RequestMapping("/formulario")
	public ModelAndView formularioDiretor() {
		ModelAndView model = new ModelAndView("formulario-diretor");
		model.addObject(DIRETOR, new Diretor());
		return model;
	}

	@PostMapping(path = "/salvar")
	public ModelAndView salvaDiretor(@RequestParam String nome, @RequestParam String sobre) {
		ModelAndView model = new ModelAndView(DIRETOR);
		Diretor diretor = new Diretor(nome, sobre);
		diretorService.salvarDiretor(diretor);
		model.addObject("diretorRetorno", diretor);
		return index();
		
	}

	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaDiretor(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView(DIRETOR);
		Diretor diretor;
		diretor = diretorService.buscarDiretor(id);
		if (diretor != null) {
			model.addObject("diretorRetorno", diretor);
		}
		return index();
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiDiretor(@PathVariable("id") Integer id) {
		Diretor diretor;
		diretor = diretorService.buscarDiretor(id);
		if (diretor != null) {
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
		if (diretor != null) {
			model.addObject(DIRETOR, diretor);
		}
		return model;
	}

}
