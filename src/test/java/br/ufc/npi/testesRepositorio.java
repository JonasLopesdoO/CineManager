package br.ufc.npi;


import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.controller.FilmeController;
import br.ufc.vev.controller.SalaController;
import br.ufc.vev.repositorio.CinemaRepositorio;
import br.ufc.vev.repositorio.FilmeRepositorio;
import br.ufc.vev.repositorio.SalaRepositorio;
import br.ufc.vev.repositorio.SessaoRepositorio;
import br.ufc.vev.service.SessaoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class  testesRepositorio{
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private SessaoRepositorio sessaoRepositorio;
    @MockBean
    private FilmeRepositorio filmeRepositorio;
    @MockBean
    private SalaRepositorio salaRepositorio;
    
    @Autowired
	SessaoService sessaoService;
    
    @MockBean
    private CinemaRepositorio cinemaRepositorio;
    
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void adicionarSessao() {
    	MockitoAnnotations.initMocks(this);
		//configurando o stub usando mockito
		//stub da dependencia
    	SalaController salaController = Mockito.mock(SalaController.class);
    	FilmeController filmeController = Mockito.mock(FilmeController.class);
    	
    	Filme filme = new Filme();
    	Sala sala = new Sala();
    	
//    	Mockito.when(filmeController.getFilmePorId(1)).thenReturn(filme);
    	Mockito.when(salaController.getSalaPorId(1)).thenReturn(sala);
    	
    	//exercitar a classe a ser testada
		LocalTime horario = LocalTime.parse("20:00");
		LocalDate dataInicio = LocalDate.parse("2018-05-12");
		LocalDate dataFim = LocalDate.parse("2018-06-12");
		
//		Sessao sessao = new Sessao(filme, sala, horario, dataInicio, dataFim);
		
		
		//verificacao
		Sessao sessaoEsperado = new Sessao(filme, sala, horario, dataInicio, dataFim);
		Sessao sessaoRecebido = sessaoService.getSessaoPorId(sessaoEsperado.getId());
		Assert.assertEquals(sessaoEsperado.getId(), sessaoRecebido.getId());
    }

}
