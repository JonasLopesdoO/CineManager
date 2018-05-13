package br.ufc.vev.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Sessao;
import br.ufc.vev.repositorio.SessaoRepositorio;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SessaoRepositoryTest {

	@Autowired
	private SessaoRepositorio sessaoRepo;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createShouldPersistData() {
		Sessao sessao = new Sessao();
	}
}
