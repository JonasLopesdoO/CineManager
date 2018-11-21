package br.ufc.vev.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
<<<<<<< HEAD

	private static final Logger logger = Logger.getLogger(String.valueOf(SessaoController.class));
	
	private static final String SESSAO = "sessao";
	private static final String SESSOES = "sessoes";
	private static final String REDIRECT = "redirect:/sessao/";
	private static final String BUSCA = "sessao-busca";
	
	
	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(SESSAO);
		List<Sessao> sessoes = getAllSessao();
		if (sessoes != null) {
			model.addObject(SESSOES, sessoes);
=======
	
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
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		}
		return model;
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesSessao(@PathVariable("id") Integer id){
	  ModelAndView model = new ModelAndView("detalhes-sessao");
	  Sessao sessao = sessaoService.buscarSessao(id);
	  if (sessao != null) {
		  model.addObject(SESSAO, sessao);
	  }
	  model.addObject("filmes", filmeController.getAllFilme());
	  model.addObject("salas", salaController.getAllSala());
	  return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioSessao() {
		ModelAndView model = new ModelAndView("formulario-sessao");
		model.addObject(SESSAO, new Sessao());
		return model;
	}

	@PostMapping(path = "/salvar")
	public ModelAndView salvaSessao(@RequestParam String horario, @RequestParam String dataInicio, @RequestParam String dataFim) {
		ModelAndView model = new ModelAndView(SESSAO);

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
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataInicioConvert = LocalDate.parse(dataInicio, formatter);
			dataFimConvert = LocalDate.parse(dataFim, formatter);
			sessao.setDataInicio(dataInicioConvert);
			sessao.setDataFim(dataFimConvert);
<<<<<<< HEAD
		} catch (IllegalArgumentException | DateTimeParseException e) {
			logger.warning("Data no formato invalido");
=======
		
				sessaoService.salvarSessao(sessao);

				model.addObject("sessao", sessao);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		}
		
		sessaoService.salvarSessao(sessao);
		model.addObject(SESSAO, sessao);		
		return index();
		
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaSessao(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-sessao");
<<<<<<< HEAD
		Sessao sessao = sessaoService.buscarSessao(id);
		if (sessao != null) {
			model.addObject(SESSAO, sessao);
		}		
		return model;
=======

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
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiSessao(@PathVariable("id") Integer id) {		
<<<<<<< HEAD
		Sessao sessao;
		sessao = sessaoService.buscarSessao(id);
		if (sessao != null) {
			sessaoService.excluirSessao(sessao);
		}		
		return index();
=======
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
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	}
	
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaSessao(@PathVariable Integer id) {
<<<<<<< HEAD
		ModelAndView model = new ModelAndView(SESSAO);
		Sessao sessao;
		sessao = sessaoService.buscarSessao(id);
		if (sessao != null) {
			model.addObject("sessaoRetorno", sessao);
=======
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
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
		}
		return index();
	}
	
	@RequestMapping(path="/{idSessao}/adicionarFilme", method=RequestMethod.POST)
	public ModelAndView vincularFilmeASessao(@PathVariable("idSessao") Integer idSessao, 
											@RequestParam Integer idFilme){

<<<<<<< HEAD
	  ModelAndView model = new ModelAndView(REDIRECT + idSessao);
	  sessaoService.vinculaFilmeASessao(idSessao, idFilme);
=======
	  ModelAndView model = new ModelAndView("redirect:/sessao/"+idSessao);
	  
	  if (sessaoPossuiFilme(idSessao, idFilme) == false) {
		  sessaoService.vinculaFilmeASessao(idSessao, idFilme);
	  }
	  
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	  return model;
	}
	
	@RequestMapping(path="/{idSessao}/removerFilme/{idFilme}")
	public ModelAndView desvinculaFilmeDaSessao(@PathVariable("idSessao") Integer idSessao,
												@PathVariable("idFilme") Integer idFilme) {
		
		ModelAndView model = new ModelAndView(REDIRECT + idSessao);
		sessaoService.desvinculaFilmeDaSessao(idSessao, idFilme);
		return model;
	}
	
	@RequestMapping(path="/{idSessao}/adicionarSala", method=RequestMethod.POST)
	public ModelAndView vincularSalaASessao(@PathVariable("idSessao") Integer idSessao, 
											@RequestParam Integer idSala){

<<<<<<< HEAD
	  ModelAndView model = new ModelAndView(REDIRECT + idSessao);
	  sessaoService.vinculaSalaASessao(idSessao, idSala);
=======
	  ModelAndView model = new ModelAndView("redirect:/sessao/"+idSessao);
	  
	  if (sessaoPossuiSala(idSessao, idSala) == false) {
		  sessaoService.vinculaSalaASessao(idSessao, idSala);
	  }
	  
>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
	  return model;
	}
	
	@RequestMapping(path="/{idSessao}/removerSala/{idSala}")
	public ModelAndView desvinculaSalaDaSessao(@PathVariable("idSessao") Integer idSessao,
												@PathVariable("idSala") Integer idSala) {
		
		ModelAndView model = new ModelAndView(REDIRECT + idSessao);
		sessaoService.desvinculaSalaDaSessao(idSessao, idSala);
		return model;
	}
	
	@RequestMapping(path = "/porData", method = RequestMethod.POST)
	public ModelAndView verTodasPorData(@RequestParam String dataInicio, @RequestParam String dataFim) {
// 	todasAsSessoesPorData
		ModelAndView model = new ModelAndView(BUSCA);
		
		LocalDate dataInicioConvert, dataFimConvert;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			dataInicioConvert = LocalDate.parse(dataInicio, formatter);
			dataFimConvert = LocalDate.parse(dataFim, formatter);
			List<Sessao> sessoes = 
					sessaoService.getSessaoPorData(dataInicioConvert, dataFimConvert);
			if (sessoes != null) {
				model.addObject(SESSOES, sessoes);
			}
		} catch (IllegalArgumentException | DateTimeParseException e) {
			logger.warning("Data no formato invalido");
		}
		return model;
	}
	
	@RequestMapping(path = "/porCidade", method = RequestMethod.POST)
	public ModelAndView verTodasPorCidade(@RequestParam String cidade) {
		ModelAndView model = new ModelAndView(BUSCA);
		if (cidade != null && !cidade.equals("")) {
			List<Sessao> sessoes = sessaoService.getSessaoPorCidade(cidade);
			model.addObject(SESSOES, sessoes);
		}
		return model;
	}
	
	@RequestMapping(path = "/porFilme", method = RequestMethod.POST)
	public ModelAndView verTodasPorFilme(@RequestParam String nome) {
		ModelAndView model = new ModelAndView(BUSCA);
		Filme filme = filmeController.buscarPorNome(nome);
		if (filme != null) {
			List<Sessao> sessoes = sessaoService.getSessaoPorFilme(filme);
			model.addObject(SESSOES, sessoes);
		}
		
		return model;
	}
	
	@RequestMapping(path = "/porGenero", method = RequestMethod.POST)
	public ModelAndView verTodasPorGenero(@RequestParam String nome) {
		ModelAndView model = new ModelAndView(BUSCA);
		Genero genero = generoController.buscaPorNome(nome);
		if (genero != null) {
			List<Sessao> sessoes = sessaoService.getSessaoPorGenero(genero);
			model.addObject(SESSOES, sessoes);
		}
		return model;
	}
	
	
	public List<Sessao> getAllSessao() {		
		return sessaoService.getAllSessao();
	}
	
<<<<<<< HEAD
=======
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

>>>>>>> parent of 9573420... vulnerabilidades na situação BOM!
}
