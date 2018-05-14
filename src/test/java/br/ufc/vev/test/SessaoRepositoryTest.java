package br.ufc.vev.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import javax.validation.ConstraintViolationException;

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

	@MockBean
	private FilmeController filmeControl;
	@MockBean
	private SalaController salaControl;
	@Autowired
	private SessaoRepositorio sessaoRepo;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@SuppressWarnings({ "deprecation", "unused" })
	@Test
	public void adicionarSessaoTest() {
		
		Date dataInicio = new Date(2018, 05, 22);
		Date dataFim = new Date(2018, 05, 30);
		
		Filme filme = new Filme();
		Sala sala = new Sala();
		
		BDDMockito.when(filmeControl.leFilmePorId(1)).thenReturn(filme);
		BDDMockito.when(salaControl.leSalaPorId(1)).thenReturn(sala);
		
		//Sessao sessao = new Sessao(dataInicio, dataFim, 1, 1);
		
//		this.sessaoRepo.save(sessao);
//		Assertions.assertThat(sessao.getId()).isNotNull();
//		Assertions.assertThat(sessao.getDataInicio()).isNotNull();
//		Assertions.assertThat(sessao.getDataFim()).isNotNull();
	}
	
	@Test 
	public void criarSessaoComDataInicioNula() {
		thrown.expect(ConstraintViolationException.class);
		
	}
	
	@Test
	public void deleteShouldRemoveData() {
		Sessao sessao = new Sessao();
		this.sessaoRepo.delete(sessao);
		sessaoRepo.delete(sessao);
		assertThat(sessaoRepo.findOne(sessao.getId())).isNull();
	}
	
	@Test
	public void updateShouldChangeAndPersistData() {
		Sessao sessao = new Sessao();
		this.sessaoRepo.save(sessao);
		//mudar as caracteristicas do pelos metodos set e tentar editar.
		
		Sessao sessaoUp = sessaoRepo.save(sessao);
		assertThat(sessao.getDataInicio()).isEqualTo(sessaoUp.getDataInicio());
		
		//atualiza alguns dos dados da sessao e ver se o update foi feito
	}
	
	//restando o teste de read...
	
}
