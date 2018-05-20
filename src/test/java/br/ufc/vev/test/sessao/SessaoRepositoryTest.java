//package br.ufc.vev.test.sessao;
//
//import static org.junit.Assert.assertEquals;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import br.ufc.vev.bean.Filme;
//import br.ufc.vev.bean.Sala;
//import br.ufc.vev.bean.Sessao;
//import br.ufc.vev.controller.FilmeController;
//import br.ufc.vev.controller.SalaController;
//import br.ufc.vev.repositorio.SessaoRepositorio;
//
//public class SessaoRepositoryTest {
//
//	@Autowired
//	private SessaoRepositorio sessaoRepositorio;
//	private FilmeController filmeControlMock;
//	private SalaController salaControlMock;
//	
//	
//	@Test
//	public void salvarSessao() {
//		
//		filmeControlMock = Mockito.mock(FilmeController.class);
//		salaControlMock= Mockito.mock(SalaController.class);
//		
//		LocalTime horario = LocalTime.parse("20:30");
//		LocalDate dataInicio = LocalDate.parse("2018-05-22");
//		LocalDate dataFim = LocalDate.parse("2018-05-30");
//		
//		Filme filme = new Filme(1); //interestelar
//		Sala sala = new Sala(1);
//		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme);
//		Mockito.when(filmeControlMock.buscarFilmeNome("interestelar")).thenReturn(filme);
//		Mockito.when(salaControlMock.buscarSalaId(1)).thenReturn(sala);
//		
//		Sessao sessao = new Sessao(filmeControlMock.buscarFilmeId(1),
//						salaControlMock.buscarSalaId(1)	, horario, dataInicio, dataFim);
//		
//		sessaoRepositorio.save(sessao);
//		
//		assertEquals(sessaoRepositorio.getOne(1), sessao);		
//		
//	}
//}
