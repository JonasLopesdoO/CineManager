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
	public ModelAndView detalhesSessao(@PathVariable("id") Integer id, @RequestParam(required=false) String erro){
	//+ getSessaoPorIdI(id : int) : Sessao		
	  ModelAndView model = new ModelAndView("detalhes-sessao");
	  Sessao sessao = sessaoService.getSessaoPorId(id);
			
	  model.addObject("sessao", sessao);
	  model.addObject("erro", erro);
			
	  return model;
	}
	
	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public String salvarSessao(@RequestParam Filme filme, @RequestParam Sala sala, @RequestParam LocalTime horario,
							@RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim) {
//	+ addSessao(sessao : Sessao) : Sessao
		
		sessaoService.salvarSessao(filme, sala, horario, dataInicio, dataFim);
		return "redirect:/sessao/";
	}
	
	@RequestMapping(path="/atualizar", method = RequestMethod.POST)
	public String atualizarSessao(@RequestParam Integer idSessao, @RequestParam Filme filme,@RequestParam Sala sala, 
					@RequestParam LocalTime horario, @RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim){
//	+ atualizarSessao(sessao : Sessao) : Sessao
		sessaoService.atualizarSessao(idSessao, filme, sala, horario, dataInicio, dataFim);
		
		return "redirect:/sessao/";
	}
	
	@RequestMapping(path="/excluir", method = RequestMethod.POST)
	public String excluirSessao(@RequestParam Integer idSessao) {
//	+ removerSessao(id : int) : Sessao
		
		sessaoService.deletarSessao(idSessao);
		return "redirect:/times/";
	}
}
