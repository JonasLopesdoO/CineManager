package br.ufc.vev.test.sessao;

import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.repositorio.SessaoRepositorio;
import br.ufc.vev.service.SessaoService;


@RunWith(SpringRunner.class)
public class SessaoServiceTest {
	
	private static final LocalTime horario = LocalTime.parse("12:00");
	private static final LocalDate dataInicio = LocalDate.parse("2018/05/22");
	private static final LocalDate dataFim = LocalDate.parse("2018/05/30");
	
	@MockBean
	private SessaoRepositorio sRepository;
	
	private SessaoService sService;
	private Sessao sessao; 
	
	@MockBean 
	Filme filme;
	
	@MockBean
	Sala sala;
	
//	@Test
//	public void salvarUmaSessaoCorretamente()  throws Exception {
//		sessao.setHorario(horario);
//		sessao.setDataInicio(dataInicio);
//		sessao.setDataFim(dataFim);
//		filme = new Filme(1);
//		
//		sService.salvarSessao(sessao);
//		
//		verify(sRepository).save(sessao);
//	}
//	
	@Test
	public void naoSalvarDuasSessaoComMesmoHorarioESala() throws Exception {}
	
}
