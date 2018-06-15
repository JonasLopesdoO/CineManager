package br.ufc.vev.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.service.SessaoService;

@Controller
@Transactional
//@Rollback(false)
@RequestMapping(path= "/sessao")
public class SessaoController {
		
	@Autowired
	SessaoService sessaoService;
	@Autowired
	FilmeController filmeController;
	@Autowired
	SalaController salaController;
	@Autowired
	GeneroController generoController;
	@Autowired
	CinemaController cinemaController;
	
	@SuppressWarnings("finally")
	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("sessao");
		try {
			List<Sessao> sessoes = getAllSessao();

			model.addObject("sessoes", sessoes);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return model;
		}
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesSessao(@PathVariable("id") Integer id){
	  ModelAndView model = new ModelAndView("detalhes-sessao");
	  Sessao sessao = sessaoService.getSessaoPorId(id);
			
	  model.addObject("filmes", filmeController.getAllFilme());
	  model.addObject("salas", salaController.getAllSala());
	  model.addObject("sessao", sessao);
			
	  return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioSessao() {
		ModelAndView model = new ModelAndView("formulario-sessao");
		model.addObject("sessao", new Sessao());
	
		return model;
	}

	@SuppressWarnings("finally")
	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvaSessao(@RequestParam String horario, @RequestParam String dataInicio, @RequestParam String dataFim) {
		ModelAndView model = new ModelAndView("sessao");
		try {
			Sessao sessao = new Sessao();
			if (this.validaSessao(horario, dataInicio, dataFim)) {
	
			LocalTime horarioConvert;
			horarioConvert = LocalTime.parse(horario);
			
			LocalDate dataInicioConvert, dataFimConvert;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			dataInicioConvert = LocalDate.parse(dataInicio, formatter);
			dataFimConvert = LocalDate.parse(dataFim, formatter);
			
			sessao.setHorario(horarioConvert);
			sessao.setDataInicio(dataInicioConvert);
			sessao.setDataFim(dataFimConvert);
		
				sessaoService.salvarSessao(sessao);

				model.addObject("sessao", sessao);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
	}
	
	// o metodo utilizado para atualizar será o salvar, visto que o spring boot ja
	// atualiza automaticamente o objeto passado.
	// este método só redireciona para a digitação dos novos campos do model
	@SuppressWarnings("finally")
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaSessao(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-sessao");

		try {
			if (existsByIdSessao(id)) {
				Sessao sessao = sessaoService.buscarSessao(id);

				model.addObject("sessao", sessao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return model;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiSessao(@PathVariable("id") Integer id) {		
		try {
			Sessao sessao = new Sessao();
			if (validaId(id) && existsByIdSessao(id)) {
				sessao = sessaoService.buscarSessao(id);
				sessaoService.excluirSessao(sessao);;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaSessao(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("sessao");
		try {
			if (this.validaId(id)) {
				if (existsByIdSessao(id)) {
					Sessao sessao = new Sessao();

					sessao = sessaoService.buscarSessao(id);

					model.addObject("sessaoRetorno", sessao);
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
	
	@RequestMapping(path="/{idSessao}/adicionarFilme", method=RequestMethod.POST)
	public ModelAndView vincularFilmeASessao(@PathVariable("idSessao") Integer idSessao, 
											@RequestParam Integer idFilme){

	  ModelAndView model = new ModelAndView("redirect:/sessao/"+idSessao);
	  
	  if (sessaoPossuiFilme(idSessao, idFilme) == false) {
		  sessaoService.vinculaFilmeASessao(idSessao, idFilme);
	  }
	  
	  return model;
	}
	
	@RequestMapping(path="/{idSessao}/removerFilme/{idFilme}")
	public ModelAndView desvinculaFilmeDaSessao(@PathVariable("idSessao") Integer idSessao,
												@PathVariable("idFilme") Integer idFilme) {
		
		ModelAndView model = new ModelAndView("redirect:/sessao/"+idSessao);
		
		if (sessaoPossuiFilme(idSessao, idFilme)) {
			sessaoService.desvinculaFilmeDaSessao(idSessao, idFilme);
		}
		
		return model;
	}
	
	@RequestMapping(path="/{idSessao}/adicionarSala", method=RequestMethod.POST)
	public ModelAndView vincularSalaASessao(@PathVariable("idSessao") Integer idSessao, 
											@RequestParam Integer idSala){

	  ModelAndView model = new ModelAndView("redirect:/sessao/"+idSessao);
	  
	  if (sessaoPossuiSala(idSessao, idSala) == false) {
		  sessaoService.vinculaSalaASessao(idSessao, idSala);
	  }
	  
	  return model;
	}
	
	@RequestMapping(path="/{idSessao}/removerSala/{idSala}")
	public ModelAndView desvinculaSalaDaSessao(@PathVariable("idSessao") Integer idSessao,
												@PathVariable("idSala") Integer idSala) {
		
		ModelAndView model = new ModelAndView("redirect:/sessao/"+idSessao);
		
		if (sessaoPossuiSala(idSessao, idSala)) {
			sessaoService.desvinculaSalaDaSessao(idSessao, idSala);
		}
		
		return model;
	}
	
	@RequestMapping(path = "/porData", method = RequestMethod.POST)
	public ModelAndView verTodasPorData(@RequestParam String dataInicio, @RequestParam String dataFim) {
// 	todasAsSessoesPorData
		ModelAndView model = new ModelAndView("sessao-busca");
		
		LocalDate dataInicioConvert, dataFimConvert;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		dataInicioConvert = LocalDate.parse(dataInicio, formatter);
		dataFimConvert = LocalDate.parse(dataFim, formatter);
		
		List<Sessao> sessoes = sessaoService.getSessaoPorData(dataInicioConvert, dataFimConvert);
		
		model.addObject("sessoes", sessoes);
		
		return model;
	}
	
	@RequestMapping(path = "/porCidade", method = RequestMethod.POST)
	public ModelAndView verTodasPorCidade(@RequestParam String cidade) {
		ModelAndView model = new ModelAndView("sessao-busca");
		if (cidade != null) {
			List<Sessao> sessoes = sessaoService.getSessaoPorCidade(cidade);
			model.addObject("sessoes", sessoes);
		}
		
		return model;
	}
	
	@RequestMapping(path = "/porFilme", method = RequestMethod.POST)
	public ModelAndView verTodasPorFilme(@RequestParam String filme) {
		ModelAndView model = new ModelAndView("sessao-busca");
		Filme filmeB = filmeController.buscarPorNome(filme);
		if (filmeB != null) {
			List<Sessao> sessoes = sessaoService.getSessaoPorFilme(filmeB);
			model.addObject("sessoes", sessoes);
		}
		
		return model;
	}
	
	@RequestMapping(path = "/porGenero", method = RequestMethod.POST)
	public ModelAndView verTodasPorGenero(@RequestParam String genero) {
		ModelAndView model = new ModelAndView("sessao-busca");
		Genero generoB = generoController.buscaPorNome(genero);
		if (generoB != null) {
			List<Sessao> sessoes = sessaoService.getSessaoPorGenero(generoB);
			model.addObject("sessoes", sessoes);
		}
		return model;
	}
	
	
	public List<Sessao> getAllSessao() {		
		return sessaoService.getAllSessao();
	}
	
	private boolean validaSessao(LocalTime horario, LocalDate dataInicio, LocalDate dataFim) throws Exception {
		if (horario.equals(null)) {
			throw new Exception("horario não pode ser nulo");
		} else if (dataInicio.equals(null)) {
			throw new Exception("Data Inicial não pode ser nula");
		} else if (dataFim.equals(null)) {
			throw new Exception("Data Inicial não pode ser nula");
		}
		return true;
	}
	
	private boolean validaSessao(String horario, String dataInicio, String dataFim) throws Exception {
		if (horario.equals(null)) {
			throw new Exception("horario não pode ser nulo");
		}else if(horario.equals("")) {
			throw new Exception("horario não pode ser vazia");	
		} else if (dataInicio.equals(null)) {
			throw new Exception("Data Inicial não pode ser nula");
		}else if(dataInicio.equals("")) {
			throw new Exception("Data Inicial não pode ser vazia");
		} else if (dataFim.equals(null)) {
			throw new Exception("Data FInal não pode ser nula");
		}else if(dataFim.equals("")) {
			throw new Exception("Data FInal não pode ser vazia");
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
	
	public boolean sessaoPossuiFilme(int idSessao, int idFilme) {
		
		if (existsByIdSessao(idSessao) && filmeController.existsByIdFilme(idFilme)) {
			Sessao sessao = sessaoService.buscarSessao(idSessao);
			
			if(sessao.getFilme() == null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean sessaoPossuiSala(int idSessao, int idSala) {
		if (existsByIdSessao(idSessao) && salaController.existsByIdSala(idSala)) {
			Sessao sessao = sessaoService.buscarSessao(idSessao);
			
			if(sessao.getSala() == null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean existsByIdSessao(int id) {
		return sessaoService.existsById(id);
	}

}
