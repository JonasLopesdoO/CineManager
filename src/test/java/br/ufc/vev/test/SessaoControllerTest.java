package br.ufc.vev.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.ufc.vev.repositorio.FilmeRepositorio;
import br.ufc.vev.repositorio.SalaRepositorio;
import br.ufc.vev.repositorio.SessaoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SessaoControllerTest {
	@MockBean
	private FilmeRepositorio filmeRepositorio;
	@MockBean
	private SalaRepositorio salaRepositorio;
	@MockBean
	private SessaoRepositorio sessaoRepositorio;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void listarSessaoPorData() {
		
	}
	
	@Test
	public void listarSessaoPorFilme() {}

	@Test
	public void listarSessaoPorCinema() {}
	
	@Test
	public void listarSessaoPorGenero() {}
}
