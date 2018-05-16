package br.ufc.vev.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.service.FilmeService;
import br.ufc.vev.service.SalaService;
import br.ufc.vev.service.SessaoService;

@Controller
@RequestMapping(path= "/sessao/")
public class SessaoController {
		
	@Autowired
	SessaoService sessaoService;
	@Autowired
	FilmeService filmeService;
	@Autowired
	SalaService salaService;
	
	@RequestMapping(path = "/")
	public ModelAndView index() {
// 	todasAsSessoes
		ModelAndView model = new ModelAndView("sessao");
		List<Sessao> sessoes = sessaoService.getTodasSessoes();
		
		model.addObject("sessao", sessoes);
		
		return model;
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesSessao(@PathVariable("id") Integer id){
	//+ getSessaoPorIdI(id : int) : Sessao		
	  ModelAndView model = new ModelAndView("detalhes-sessao");
	  Sessao sessao = sessaoService.getSessaoPorId(id);
			
	  model.addObject("sessao", sessao);
			
	  return model;
	}
	
	@RequestMapping(path = "/porData", method = RequestMethod.GET)
	public ModelAndView verTodasPorData(@RequestParam String dataInicio, @RequestParam String dataFim) {
// 	todasAsSessoesPorData
		ModelAndView model = new ModelAndView("sessao");
		LocalDate dataInicial = LocalDate.parse(dataInicio);
		LocalDate dataFinal = LocalDate.parse(dataFim);
		
		List<Sessao> sessoes = sessaoService.getSessaoPorData(dataInicial, dataFinal);
		
		model.addObject("sessao", sessoes);
		
		return model;
	}
	
	@RequestMapping(path = "/porCidade", method = RequestMethod.GET)
	public ModelAndView verTodasPorCidade(@RequestParam String cidade) {
// 	todasAsSessoesPorData
		ModelAndView model = new ModelAndView("sessao");
		
		List<Sessao> sessoes = sessaoService.getSessaoPorCidade(cidade);
		
		model.addObject("sessao", sessoes);
		
		return model;
	}
	
	@RequestMapping(path = "/porFilme", method = RequestMethod.GET)
	public ModelAndView verTodasPorFilme(@RequestParam String filme) {
// 	todasAsSessoesPorData
		ModelAndView model = new ModelAndView("sessao");
		
		List<Sessao> sessoes = sessaoService.getSessaoPorFilme(filme);
		
		model.addObject("sessao", sessoes);
		
		return model;
	}
	
	@RequestMapping(path = "/porGenero", method = RequestMethod.GET)
	public ModelAndView verTodasPorGenero(@RequestParam String genero) {
// 	todasAsSessoesPorData
		ModelAndView model = new ModelAndView("sessao");
		
		List<Sessao> sessoes = sessaoService.getSessaoPorGenero(genero);
		
		model.addObject("sessao", sessoes);
		
		return model;
	}
	
//	@RequestMapping(path="/adicionar", method = RequestMethod.POST)
	public ModelAndView addSessao(Filme filme,Sala sala,LocalTime horario,
								LocalDate dataInicio,LocalDate dataFim) {
//	+ addSessao(sessao : Sessao) : Sessao
		Sessao sessao = new Sessao(filme, sala, horario, dataInicio, dataFim);
		
		Sessao sessaoRetorno = sessaoService.salvarSessao(sessao);
		
		ModelAndView model = new ModelAndView("sessao");
		model.addObject("sessao", sessaoRetorno);
		
		return model;
	}
	
	@RequestMapping(path="/atualizar", method = RequestMethod.POST)
	public ModelAndView atualizarSessao(@RequestParam Integer idSessao, @RequestParam Filme filme,@RequestParam Sala sala, 
			@RequestParam LocalTime horario, @RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim){
//	+ atualizarSessao(sessao : Sessao) : Sessao
		
		Sessao sessaoRetorno = sessaoService.atualizarSessao(idSessao, filme, sala, horario, dataInicio, dataFim);
		
		ModelAndView model = new ModelAndView("sessao");
		model.addObject("sessao", sessaoRetorno);
		return model;
	}
	
	@RequestMapping(path="/excluir", method = RequestMethod.POST)
	public ModelAndView excluirSessao(@RequestParam Integer idSessao) {
//	+ removerSessao(id : int) : Sessao
		
		sessaoService.deletarSessao(idSessao);
		
		ModelAndView model = new ModelAndView("sessao");
		
		return model;
	}
}
