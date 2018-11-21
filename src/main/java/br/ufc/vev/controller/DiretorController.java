package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Diretor;
import br.ufc.vev.service.DiretorService;


@Controller
@RequestMapping(path = "/diretor")
public class DiretorController {
	@Autowired
	private DiretorService diretorService;
<<<<<<< HEAD
	
	private static final String DIRETOR = "diretor";
=======
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!

	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(DIRETOR);
		List<Diretor> diretores = getAllDiretores();
		if (diretores != null) {
			model.addObject("diretores", diretores);
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
	public ModelAndView formularioDiretor() {
		ModelAndView model = new ModelAndView("formulario-diretor");
		model.addObject(DIRETOR, new Diretor());
		return model;
	}

	@PostMapping(path = "/salvar")
	public ModelAndView salvaDiretor(Diretor diretor) {
		ModelAndView model = new ModelAndView(DIRETOR);
		diretorService.salvarDiretor(diretor);
		model.addObject("diretorRetorno", diretor);
		return index();
		
	}

	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaDiretor(@PathVariable Integer id) {
<<<<<<< HEAD
		ModelAndView model = new ModelAndView(DIRETOR);
		Diretor diretor;
		diretor = diretorService.buscarDiretor(id);
		if (diretor != null) {
			model.addObject("diretorRetorno", diretor);
=======
		ModelAndView model = new ModelAndView("diretor");
		try {
			if (existsByIdDiretor(id)) {
				Diretor diretor = new Diretor();
				diretor = diretorService.buscarDiretor(id);
				model.addObject("diretorRetorno", diretor);
			} 
		} catch (Exception e) { // caso de erro
			e.printStackTrace();
		} finally { // sempre será execultado
			return index();
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		}
		return index();
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiDiretor(@PathVariable("id") Integer id) {
<<<<<<< HEAD
		Diretor diretor;
		diretor = diretorService.buscarDiretor(id);
		if (diretor != null) {
			diretorService.excluirDiretor(diretor);
=======
		try {
			Diretor diretor = new Diretor();
			if (existsByIdDiretor(id)) {
				diretor = diretorService.buscarDiretor(id);
				diretorService.excluirDiretor(diretor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return index();
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
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
<<<<<<< HEAD
		Diretor diretor;
		diretor = diretorService.buscarDiretor(id);
		if (diretor != null) {
			model.addObject(DIRETOR, diretor);
=======

		try {
			if (existsByIdDiretor(id)) {
				Diretor diretor = diretorService.buscarDiretor(id);
				model.addObject("diretor", diretor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return model;
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		}
		return model;
	}

}
