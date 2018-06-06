package br.ufc.vev.test.cinema;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.controller.CinemaController;
import br.ufc.vev.controller.SalaController;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemaControllerTest {
	
	@Autowired
	CinemaController controller;
	@Autowired
	SalaController controllerSala;
	
	@Test
	public void salvaCinemaControllerTest() {
		
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";		
		
		assertNotNull(controller.salvaCinema(nome, cidade, endereco));
	}
	
	@Test
	public void salvaCinemaComNomeNuloControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = null;		
		
		assertNull("Nome não pode ser nulo", controller.salvaCinema(nome, cidade, endereco));
	}
	
	@Test
	public void salvaCinemaComNomeVazioControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "";		
		
		assertNull("Nome não pode ser vazio", controller.salvaCinema(nome, cidade, endereco));
	}
	
	@Test
	public void salvaCinemaComCidadeVazioControllerTest() {
		String cidade = "";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";		
		
		assertNull("Cidade não pode ser vazio", controller.salvaCinema(nome, cidade, endereco));
	}
	
	@Test
	public void salvaCinemaComCidadeNuloControllerTest() {
		String cidade = null;
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";		
		
		assertNull("Cidade não pode ser nulo", controller.salvaCinema(nome, cidade, endereco));
	}
	
	@Test
	public void salvaCinemaComEnderecoNuloControllerTest() {
		String cidade = "Quixada";
		String endereco = null;
		String nome = "Cine o bom vizinho";		
		
		assertNull("Endereco não pode ser nulo", controller.salvaCinema(nome, cidade, endereco));
	}
	
	@Test
	public void salvaCinemaComEnderecoVazioControllerTest() {
		String cidade = "Quixada";
		String endereco = "";
		String nome = "Cine o bom vizinho";		
		
		assertNull("Endereco não pode ser vazio", controller.salvaCinema(nome, cidade, endereco));
	}
	
	
	@Test 
	public void buscaCinemaControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";	
		
		Cinema cinemaBuscado = new Cinema();
		
		cinemaBuscado = controller.salvaCinema(nome, cidade, endereco);
		
		assertNotNull(controller.buscaCinema(cinemaBuscado.getId()));
	}
	
	@Test 
	public void buscaCinemaComIdZeroControllerTest() {
		assertNull("Erro ID deve ser maior que zero", controller.buscaCinema(0));
	}
	
	@Test 
	public void buscaCinemaComIdNegativoControllerTest() {
		assertNull("Erro ID não pode ser negativo", controller.buscaCinema(-1));
	}
		
	@Test
	public void atualizaCinemaControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";	
		
		Cinema cinemaUp = new Cinema();
		
		cinemaUp = controller.salvaCinema(nome, cidade, endereco);
		
		cinemaUp.setCidade("Quixadá");
		cinemaUp.setEndereco("Rua José de Queiroz Pessoa, 2500 - Planalto Universitário");
		cinemaUp.setNome("Cine Bom Vizinho");
		
		assertThat(controller.atualizaCinema(cinemaUp));
	}
	
	@Test
	public void atualizaCinemaComNomeVazioControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";	
		
		Cinema cinemaUp = new Cinema();
		
		cinemaUp = controller.salvaCinema(nome, cidade, endereco);
		
		cinemaUp.setCidade("Quixada");
		cinemaUp.setEndereco("Rua José de Queiroz Pessoa, 2500 - Planalto Universitário");
		cinemaUp.setNome("");
		
		assertFalse("Nome não pode ser vazio", controller.atualizaCinema(cinemaUp));
	}
	
	@Test
	public void atualizaCinemaComNomeNullControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";	
		
		Cinema cinemaUp = new Cinema();
		
		cinemaUp = controller.salvaCinema(nome, cidade, endereco);
		
		cinemaUp.setCidade("Quixada");
		cinemaUp.setEndereco("Rua José de Queiroz Pessoa, 2500 - Planalto Universitário");
		cinemaUp.setNome(null);
		
		assertFalse("Nome não pode ser nulo" ,controller.atualizaCinema(cinemaUp));
	}
	
	@Test
	public void atualizaCinemaComCidadeVaziaControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";	
		
		Cinema cinemaUp = new Cinema();
		
		cinemaUp = controller.salvaCinema(nome, cidade, endereco);
		
		cinemaUp.setCidade("");
		cinemaUp.setEndereco("Rua José de Queiroz Pessoa, 2500 - Planalto Universitário");
		cinemaUp.setNome("Cine Bom Vizinho");
		
		assertFalse("Cidade não pode ser vazio" ,controller.atualizaCinema(cinemaUp));
	}
	
	@Test
	public void atualizaCinemaComCidadeNulaControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";	
		
		Cinema cinemaUp = new Cinema();
		
		cinemaUp = controller.salvaCinema(nome, cidade, endereco);
		
		cinemaUp.setCidade(null);
		cinemaUp.setEndereco("Rua José de Queiroz Pessoa, 2500 - Planalto Universitário");
		cinemaUp.setNome("Cine Bom vizinho");
		
		assertFalse("Cidade não pode ser nulo" ,controller.atualizaCinema(cinemaUp));
	}
	
	@Test
	public void atualizaCinemaComEncderecoVazioControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";	
		
		Cinema cinemaUp = new Cinema();
		
		cinemaUp = controller.salvaCinema(nome, cidade, endereco);
		
		cinemaUp.setCidade("Quixadá");
		cinemaUp.setEndereco("");
		cinemaUp.setNome("Cine Bom Vizinho");
		
		assertFalse("Endereco não pode ser vazio" ,controller.atualizaCinema(cinemaUp));
	}
	
	@Test
	public void atualizaCinemaComEnderecoNuloControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";	
		
		Cinema cinemaUp = new Cinema();
		
		cinemaUp = controller.salvaCinema(nome, cidade, endereco);
		
		cinemaUp.setCidade("Quixadá");
		cinemaUp.setEndereco(null);
		cinemaUp.setNome("Cine Bom Vizinho");
		
		assertFalse("Endereco não pode ser nulo" , controller.atualizaCinema(cinemaUp));
	}
	
	
	@Test
	public void excluiCinemaControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";	
		
		Cinema cinemaBuscado = new Cinema();
		
		cinemaBuscado = controller.salvaCinema(nome, cidade, endereco);
		
		controller.excluiCinema(cinemaBuscado.getId());
		
		assertNotNull(controller.buscaCinema(cinemaBuscado.getId()));
	}
	
	@Test
	public void excluiCinemaComIdNegativoControllerTest() {		
		assertFalse("Erro ID não pode ser negativo", controller.excluiCinema(-1));
	}
	
	@Test
	public void excluiCinemaComIdZeroControllerTest() {		
		assertFalse("Erro ID deve ser maior que zero", controller.excluiCinema(0));
	}
	
	@Test 
	public void getAllCinemaControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";	
				
		controller.salvaCinema(nome, cidade, endereco);
		
		assertTrue(controller.getAllCinema().size() >= 1);
	}
	
//	@Test
//	public void vinculaSalaAoCinemaControllerTest() {
//		String cidade = "Quixada";
//		String endereco = "Rua A, Planalto Universitario";
//		String nome = "Cine o bom vizinho";	
//			
//		int idCine = controller.salvaCinema(nome, cidade, endereco).getId();
//		
//		String nomeSala = "Sala A1";
//		int capacidade = 150;
//		
//		Integer idSala = controllerSala.salvaSala(nomeSala, capacidade).getId();
//
//		assertTrue(controller.vinculaSalaAoCinema(idCine, idSala));
//		
//	}
//	
//	@Test
//	public void vinculaSalaAoCinemaComIdSalaNegativoControllerTest() {
//		String cidade = "Quixada";
//		String endereco = "Rua A, Planalto Universitario";
//		String nome = "Cine o bom vizinho";	
//			
//		int idCine = controller.salvaCinema(nome, cidade, endereco).getId();
//		
//		assertFalse("Erro ID não pode ser negativo", controller.vinculaSalaAoCinema(idCine, -1));
//		
//	}
//	
//	@Test
//	public void vinculaSalaAoCinemaComIdSalaZeroControllerTest() {
//		String cidade = "Quixada";
//		String endereco = "Rua A, Planalto Universitario";
//		String nome = "Cine o bom vizinho";	
//			
//		int idCine = controller.salvaCinema(nome, cidade, endereco).getId();
//		
//		assertFalse("Erro ID deve ser maior que zero", controller.vinculaSalaAoCinema(idCine, 0));
//		
//	}
//	
//	@Test
//	public void vinculaSalaAoCinemaComIdCinemaNegativoControllerTest() {
//		
//		String nomeSala = "Sala A1";
//		int capacidade = 150;
//		
//		Integer idSala = controllerSala.salvaSala(nomeSala, capacidade).getId();
//
//		assertFalse("Erro ID não pode ser negativo", controller.vinculaSalaAoCinema( -1, idSala));
//		
//	}
//	
//	@Test
//	public void vinculaSalaAoCinemaComIdCinemaZeroControllerTest() {
//		
//		String nomeSala = "Sala A1";
//		int capacidade = 150;
//		
//		Integer idSala = controllerSala.salvaSala(nomeSala, capacidade).getId();
//
//		assertFalse("Erro ID deve ser maior que zero" , controller.vinculaSalaAoCinema( 0 , idSala));
//	}
//	
//	@Test
//	public void desvinculaSalaDoCinemaControllerTest() {
//		String cidade = "Quixada";
//		String endereco = "Rua A, Planalto Universitario";
//		String nome = "Cine o bom vizinho";	
//			
//		int idCine = controller.salvaCinema(nome, cidade, endereco).getId();
//		
//		String nomeSala = "Sala A1";
//		int capacidade = 150;
//		
//		Integer idSala = controllerSala.salvaSala(nomeSala, capacidade).getId();
//
//		assertTrue(controller.vinculaSalaAoCinema(idCine, idSala));
//		controller.desvinculaSalaAoCinema(idCine, idSala);
//	}
	
}
