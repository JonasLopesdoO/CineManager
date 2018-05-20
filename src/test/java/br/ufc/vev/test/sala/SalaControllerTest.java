package br.ufc.vev.test.sala;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.controller.SalaController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaControllerTest {

	@Autowired
	SalaController controller;
	
	@Test
	public void adicionaSalaControllerTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala salaRecebida = new Sala();
		controller.salvaSala(nome, capacidade);
		
		//assertNotNull(salaRecebida);
		
	}
	
//	@Test
//	public void adicionaSalaComNomeNuloControllerTest() {
//		String nome = null;
//		int capacidade = 150;
//		
//		controller.salvaSala(nome, capacidade);
//		
//		assertThatException(controller.salvaSala(nome, capacidade));
//		
//	}
}
