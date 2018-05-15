package br.ufc.vev.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.controller.FilmeController;
import br.ufc.vev.controller.SalaController;
import br.ufc.vev.repositorio.SessaoRepositorio;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SessaoRepositoryTest {

	private static final LocalTime horario = LocalTime.parse("12:00");
	private static final LocalDate dataInicio = LocalDate.parse("2018/05/22");
	private static final LocalDate dataFim = LocalDate.parse("2018/05/30");
	private static final Filme filme = new Filme();
	private static final Sala sala = new Sala();
	
	
	@MockBean
	private FilmeController filmeControl;
	@MockBean
	private SalaController salaControl;
	@Autowired
	private SessaoRepositorio sessaoRepo;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void adicionarSessaoTest() {
		
		BDDMockito.when(filmeControl.getFilmePorId(1)).thenReturn(filme);
		BDDMockito.when(salaControl.getSalaPorId(1)).thenReturn(sala);
		
		Sessao sessao = new Sessao(filmeControl.getFilmePorId(1), 
				salaControl.getSalaPorId(1), horario, dataInicio, dataFim);
		
		System.out.println(sessao.getId());
		
		this.sessaoRepo.save(sessao);
		Assertions.assertThat(sessao.getId()).isNotNull();
		Assertions.assertThat(sessao.getHorario()).isNotNull();
		Assertions.assertThat(sessao.getDataInicio()).isNotNull();
		Assertions.assertThat(sessao.getDataFim()).isNotNull();
	}
	
	@Test 
	public void criarSessaoComDataInicioNula() {
		thrown.expect(ConstraintViolationException.class);
		
	}
	
	@Test
	public void deleteSessaoTest() {

		BDDMockito.when(filmeControl.getFilmePorId(1)).thenReturn(filme);
		BDDMockito.when(salaControl.getSalaPorId(1)).thenReturn(sala);
		
		Sessao sessao = new Sessao(filmeControl.getFilmePorId(1), 
				salaControl.getSalaPorId(1), horario, dataInicio, dataFim);
		
		sessaoRepo.save(sessao);
		sessaoRepo.delete(sessao);
		assertThat(sessaoRepo.findOne(sessao.getId())).isNull();
	}
	
	@Test
	public void updateShouldChangeAndPersistData() {

		BDDMockito.when(filmeControl.getFilmePorId(1)).thenReturn(filme);
		BDDMockito.when(salaControl.getSalaPorId(1)).thenReturn(sala);
		
		Sessao sessao = new Sessao(filmeControl.getFilmePorId(1), 
				salaControl.getSalaPorId(1), horario, dataInicio, dataFim);
		
		this.sessaoRepo.save(sessao);
		//mudar as caracteristicas do pelos metodos set e tentar editar.
		
		Sessao sessaoUp = sessaoRepo.save(sessao);
		assertThat(sessao.getDataInicio()).isEqualTo(sessaoUp.getDataInicio());
		
		//atualiza alguns dos dados da sessao e ver se o update foi feito
	}
	
	//restando o teste de read...
	
}
