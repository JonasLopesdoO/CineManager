package br.ufc.vev.test.filme;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.controller.FilmeController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmeControllerTest {
	
	@MockBean
	private FilmeController fController;
	
	@Test
	public void buscarFilmeIdTest() {
		
		Filme filme = new Filme(1);
		BDDMockito.when(fController.buscarFilmeId(1)).thenReturn(filme);
		
	}
	
	public void getFilmePorNomeTest() {
		
	}
}
