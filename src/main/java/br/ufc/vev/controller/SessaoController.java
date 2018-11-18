package br.ufc.vev.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	private static final Logger logger = Logger.getLogger(String.valueOf(SessaoController.class));

	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("sessao");
		List<Sessao> sessoes = getAllSessao();
		if (sessoes != null) {
			model.addObject("sessoes", sessoes);
		}
		return model;
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesSessao(@PathVariable("id") Integer id){
	  ModelAndView model = new ModelAndView("detalhes-sessao");
	  Sessao sessao = sessaoService.buscarSessao(id);
	  if (sessao != null) {
		  model.addObject("sessao", sessao);
	  }
	  model.addObject("filmes", filmeController.getAllFilme());
	  model.addObject("salas", salaController.getAllSala());
	  return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioSessao() {
		ModelAndView model = new ModelAndView("formulario-sessao");
		model.addObject("sessao", new Sessao());
		return model;
	}

	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvaSessao(@RequestParam String horario, @RequestParam String dataInicio, @RequestParam String dataFim) {
		ModelAndView model = new ModelAndView("sessao");

		Sessao sessao = new Sessao();
		LocalTime horarioConvert;
		LocalDate dataInicioConvert, dataFimConvert;
		try {
			horarioConvert = LocalTime.parse(horario);
			sessao.setHorario(horarioConvert);
		} catch (DateTimeParseException e) {
			logger.warning("Hora no formato invalido");
		}
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			dataInicioConvert = LocalDate.parse(dataInicio, formatter);
			dataFimConvert = LocalDate.parse(dataFim, formatter);
			sessao.setDataInicio(dataInicioConvert);
			sessao.setDataFim(dataFimConvert);
		} catch (IllegalArgumentException | DateTimeParseException e) {
			logger.warning("Data no formato invalido");
		}
		
		sessaoService.salvarSessao(sessao);
		model.addObject("sessao", sessao);		
		return index();
		
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaSessao(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-sessao");
		Sessao sessao = sessaoService.buscarSessao(id);
		if (sessao != null) {
			model.addObject("sessao", sessao);
		}		
		return model;
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiSessao(@PathVariable("id") Integer id) {		
		Sessao sessao;
		sessao = sessaoService.buscarSessao(id);
		if (sessao != null) {
			sessaoService.excluirSessao(sessao);
		}		
		return index();
	}
	
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaSessao(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("sessao");
		Sessao sessao;
		sessao = sessaoService.buscarSessao(id);
		if (sessao != null) {
			model.addObject("sessaoRetorno", sessao);
		}
		return index();
	}
	
	@RequestMapping(path="/{idSessao}/adicionarFilme", method=RequestMethod.POST)
	public ModelAndView vincularFilmeASessao(@PathVariable("idSessao") Integer idSessao, 
											@RequestParam Integer idFilme){

	  ModelAndView model = new ModelAndView("redirect:/sessao/"+idSessao);
	  sessaoService.vinculaFilmeASessao(idSessao, idFilme);
	  return model;
	}
	
	@RequestMapping(path="/{idSessao}/removerFilme/{idFilme}")
	public ModelAndView desvinculaFilmeDaSessao(@PathVariable("idSessao") Integer idSessao,
												@PathVariable("idFilme") Integer idFilme) {
		
		ModelAndView model = new ModelAndView("redirect:/sessao/"+idSessao);
		sessaoService.desvinculaFilmeDaSessao(idSessao, idFilme);
		return model;
	}
	
	@RequestMapping(path="/{idSessao}/adicionarSala", method=RequestMethod.POST)
	public ModelAndView vincularSalaASessao(@PathVariable("idSessao") Integer idSessao, 
											@RequestParam Integer idSala){

	  ModelAndView model = new ModelAndView("redirect:/sessao/"+idSessao);
	  sessaoService.vinculaSalaASessao(idSessao, idSala);
	  return model;
	}
	
	@RequestMapping(path="/{idSessao}/removerSala/{idSala}")
	public ModelAndView desvinculaSalaDaSessao(@PathVariable("idSessao") Integer idSessao,
												@PathVariable("idSala") Integer idSala) {
		
		ModelAndView model = new ModelAndView("redirect:/sessao/"+idSessao);
		sessaoService.desvinculaSalaDaSessao(idSessao, idSala);
		return model;
	}
	
	@RequestMapping(path = "/porData", method = RequestMethod.POST)
	public ModelAndView verTodasPorData(@RequestParam String dataInicio, @RequestParam String dataFim) {
// 	todasAsSessoesPorData
		ModelAndView model = new ModelAndView("sessao-busca");
		
		LocalDate dataInicioConvert, dataFimConvert;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			dataInicioConvert = LocalDate.parse(dataInicio, formatter);
			dataFimConvert = LocalDate.parse(dataFim, formatter);
			List<Sessao> sessoes = 
					sessaoService.getSessaoPorData(dataInicioConvert, dataFimConvert);
			if (sessoes != null) {
				model.addObject("sessoes", sessoes);
			}
		} catch (IllegalArgumentException | DateTimeParseException e) {
			logger.warning("Data no formato invalido");
		}
		return model;
	}
	
	@RequestMapping(path = "/porCidade", method = RequestMethod.POST)
	public ModelAndView verTodasPorCidade(@RequestParam String cidade) {
		ModelAndView model = new ModelAndView("sessao-busca");
		if (cidade != null && !cidade.equals("")) {
			List<Sessao> sessoes = sessaoService.getSessaoPorCidade(cidade);
			model.addObject("sessoes", sessoes);
		}
		return model;
	}
	
	@RequestMapping(path = "/porFilme", method = RequestMethod.POST)
	public ModelAndView verTodasPorFilme(@RequestParam String nome) {
		ModelAndView model = new ModelAndView("sessao-busca");
		Filme filme = filmeController.buscarPorNome(nome);
		if (filme != null) {
			List<Sessao> sessoes = sessaoService.getSessaoPorFilme(filme);
			model.addObject("sessoes", sessoes);
		}
		
		return model;
	}
	
	@RequestMapping(path = "/porGenero", method = RequestMethod.POST)
	public ModelAndView verTodasPorGenero(@RequestParam String nome) {
		ModelAndView model = new ModelAndView("sessao-busca");
		Genero genero = generoController.buscaPorNome(nome);
		if (genero != null) {
			List<Sessao> sessoes = sessaoService.getSessaoPorGenero(genero);
			model.addObject("sessoes", sessoes);
		}
		return model;
	}
	
	
	public List<Sessao> getAllSessao() {		
		return sessaoService.getAllSessao();
	}
	
}
