package br.ufc.vev.test.sala;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.service.SalaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaServiceTest {
	
	@Autowired
	SalaService service;

	@Test
	public void adicionarSalaServiceTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala(nome, capacidade);
		Sala salaRecebida = service.salvarSala(sala);
		
		assertNotNull(salaRecebida);
	}
	
	@Test
	public void buscaSalaServiceTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala(nome, capacidade);
		Sala salaRecebida = new Sala(); 
		salaRecebida = service.salvarSala(sala);
	
		assertNotNull(service.buscarSala(salaRecebida.getId()));
	}
	
	@Test
	public void excluiSalaServiceTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		
		sala =  service.salvarSala(new Sala(nome, capacidade));
		
		service.excluirSala(sala);
		
		assertThat(service.buscarSala(sala.getId()));

	}
	
	@Test
	public void alteraSalaRepositoryTest(){
		//Sala atual
		String nome = "Sala A1";
		int capacidade = 150;
		Sala salaAtual = new Sala(nome, capacidade);
		
		//Sala atualizada
		String nomeUp = "Sala A2";
		int capacidadeUp = 120;
		Sala salaAtualizada = new Sala();
		
		salaAtualizada = service.salvarSala(salaAtual);
		salaAtualizada.setNome(nomeUp);
		salaAtualizada.setCapacidade(capacidadeUp);
		
		service.atualizaSala(salaAtualizada);
		
		assertThat(service.buscarSala(salaAtualizada.getId()));
	}
	
}
