package br.ufc.vev.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Sessao;
import br.ufc.vev.repositorio.SessaoRepositorio;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SessaoRepositoryTest {

	@Autowired
	private SessaoRepositorio sessaoRepo;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@SuppressWarnings("deprecation")
	@Test
	public void createShouldPersistData() {
		Sessao sessao = new Sessao();
		sessao.setId(1);
		
		Date dataInicio = new Date();
		dataInicio.setYear(2018);
		dataInicio.setMonth(5);
		dataInicio.setDate(22);
		sessao.setDataInicio(dataInicio);
		Date dataFim = new Date();
		dataFim.setYear(2018);
		dataFim.setMonth(5);
		dataFim.setDate(30);
		sessao.setDataFim(dataFim);
		
		this.sessaoRepo.save(sessao);
		Assertions.assertThat(sessao.getId()).isNotNull();
		Assertions.assertThat(sessao.getDataInicio()).isNotNull();
		Assertions.assertThat(sessao.getDataFim()).isNotNull();
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
