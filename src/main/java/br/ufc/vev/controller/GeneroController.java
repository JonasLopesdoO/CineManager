package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import br.ufc.vev.bean.Genero;
import br.ufc.vev.service.GeneroService;

@Controller
@RequestMapping(path= "/genero")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	@RequestMapping(path="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("genero");
		try {
			List<Genero> generos = generoService.getAllGenero();
			
			model.addObject("generos", generos);
			return model;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioGenero() {
		ModelAndView model = new ModelAndView("formulario");
		model.addObject("genero", new Genero());
		
		return model;
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public ModelAndView salvaGenero(@RequestParam String nomeGenero) {
		ModelAndView model = new ModelAndView("genero");
		
		try {
			if (this.validaGenero(nomeGenero)) { // adiciona um genero novo
				Genero genero = new Genero();
				genero.setNome(nomeGenero);
				
				generoService.salvarGenero(genero);
				
		 	}
		} catch (Exception e) { 	// caso de erro 
			e.printStackTrace();
		} finally { // sempre será execultado
			List<Genero> generos = generoService.getAllGenero();
			model.addObject("generos", generos);
			return model;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaGenero(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("genero");
		try {
			if(this.validaIdGenero(id)) {
				if(existsByIdGenero(id)) {
					Genero genero = new Genero();
					
					genero = generoService.buscarGenero(id);
					
					model.addObject("generoRetorno", genero);
				}else {
					//mensagem de erro "id nao existente no banco"
				}
		 	}else {
		 		//msg de id invalido
		 	}
		} catch (Exception e) { 	// caso de erro 
			e.printStackTrace();
		} finally { // sempre será execultado
			List<Genero> generos = generoService.getAllGenero();
			model.addObject("generos", generos);
			return model;
		}
	}

	@SuppressWarnings("finally")
	@GetMapping("/excluir/{id}")
	public ModelAndView excluiGenero(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("genero");
		try {
			Genero genero = new Genero();
			if (validaIdGenero(id) && existsByIdGenero(id)) { 
				
				genero = generoService.buscarGenero(id);
				generoService.excluirGenero(genero);
		 	}
			
		} catch (Exception e) { 	// caso de erro 
			e.printStackTrace();
		} finally { // sempre será execultado
			List<Genero> generos = generoService.getAllGenero();
			model.addObject("generos", generos);
			return model;
		}
	}

	public List<Genero> getAllGenero() {		
		return generoService.getAllGenero();
	}

	public boolean atualizaGenero(Genero genero) {
		try {
			if (existsByIdGenero(genero.getId()) && 
					validaGenero(genero.getNome()) &&
					validaIdGenero(genero.getId())) {
				generoService.atualizaGenero(genero);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	@RequestMapping("/atualizar/{id}")
//	public ModelAndView atualizarPessoa(@PathVariable Long id) {
//		ModelAndView model = new ModelAndView("formulario");
//		
//		Pessoa pessoa = pessoaService.buscarPorId(id);
//		
//		model.addObject("pessoa",pessoa);
//		
//		return model;
//	}
	
	public boolean validaGenero(String nome) throws Exception {
		
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} 
		for (Genero genero : getAllGenero()) {
			// Verifica se existe um genero de mesmo nome no banco de dados
			if (genero.getNome().equals(nome)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean validaIdGenero(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro, ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro, ID não pode ser negativo");
		} 
		return true;
	}
	
	public boolean existsByIdGenero(int id) {
		return generoService.buscaGenero(id);
	}
	
//	@RequestMapping("/excluir/{id}")
//	public ModelAndView excluirPessoa(@PathVariable Long id) {
//		Pessoa pessoa = pessoaService.buscarPorId(id);
//		ModelAndView mv = new ModelAndView("pagina-listagem");
//		mv.addObject("pessoa", pessoa);
//		return mv;
//		
//	}
//	
//<tr th:object="${pessoa}">
//      <td th:text="${pessoa.id}">    </td>
//      <td th:text="${pessoa.nome}">  </td>
//      <td th:text="${pessoa.time}">  </td>
//      <td> <a th:href="@{/pessoa/atualizar/{id}(id = ${pessoa.id})}"> <button type="button" class="btn btn-primary">Atualizar</button> </a> </td>
//      <td> <a class="btn btn-danger" th:href="@{/pessoa/excluir/{id}(id = ${pessoa.id})}">  Excluir </a>
//    </tr>
	
}
