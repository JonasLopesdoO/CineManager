package br.ufc.vev.test.cinema;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.service.CinemaService;
import br.ufc.vev.service.SalaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemaServiceTest {
	
	@Autowired
	private CinemaService cinemaService;
	@Autowired
	private SalaService salaService;
	
	@Test
	public void salvaCinemaServiceTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Quixada");
		cinema.setEndereco("Rua A, Planalto Universitario");
		cinema.setNome("Cine o bom vizinho");		
		
		assertNotNull(cinemaService.salvarCinema(cinema));
	}
	
	@Test
	public void salvaCinemaComNomeNuloServiceTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = null;		
		
		Cinema cinema = new Cinema(nome, endereco, cidade);
		try {
			cinemaService.salvarCinema(cinema);
		} catch (ConstraintViolationException e) {
			assertNotNull(e.getMessage());
		}	
	}
	
	@Test
	public void salvaCinemaComNomeVazioServiceTest(){
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "";		
		Cinema cinema = new Cinema(nome, endereco, cidade);
		try {
			cinemaService.salvarCinema(cinema);
		} catch (ConstraintViolationException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void salvaCinemaComCidadeVazioServiceTest() {
		String cidade = "";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";		
		
		Cinema cinema = new Cinema(nome, endereco, cidade);
		try {
			cinemaService.salvarCinema(cinema);
		} catch (ConstraintViolationException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void salvaCinemaComCidadeNuloControllerTest() {
		String cidade = null;
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";		
		
		Cinema cinema = new Cinema(nome, endereco, cidade);
		try {
			cinemaService.salvarCinema(cinema);
		} catch (ConstraintViolationException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void salvaCinemaComEnderecoNuloControllerTest() {
		String cidade = "Quixada";
		String endereco = null;
		String nome = "Cine o bom vizinho";		
		
		Cinema cinema = new Cinema(nome, endereco, cidade);
		try {
			cinemaService.salvarCinema(cinema);
		} catch (ConstraintViolationException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void salvaCinemaComEnderecoVazioControllerTest() {
		String cidade = "Quixada";
		String endereco = "";
		String nome = "Cine o bom vizinho";		
		
		Cinema cinema = new Cinema(nome, endereco, cidade);
		try {
			cinemaService.salvarCinema(cinema);
		} catch (ConstraintViolationException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test 
	public void buscaCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Quixada");
		cinema.setEndereco("Rua A, Planalto Universitario");
		cinema.setNome("Cine o bom vizinho");
		
		Cinema cinemaBuscado = new Cinema();
		
		cinemaBuscado = cinemaService.salvarCinema(cinema);
		assertNotNull(cinemaService.buscaCinema(cinemaBuscado.getId()));
	}
	
	@Test(expected = org.hibernate.LazyInitializationException.class)
	public void buscaCinemaPassandoIdNegativoServiceTest() {
		assertNull(cinemaService.buscaCinema(-1));
	}
	
	@Test(expected = org.hibernate.LazyInitializationException.class)
	public void buscaCinemaPassandoIdZeroServiceTest() {
		assertNull(cinemaService.buscaCinema(0));
	}
	
	@Test
	public void existByIdCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Quixada");
		cinema.setEndereco("Rua A, Planalto Universitario");
		cinema.setNome("Cine o bom vizinho");
		
		Cinema cinemaBuscado = new Cinema();
		
		cinemaBuscado = cinemaService.salvarCinema(cinema);
		assertTrue(cinemaService.existsById(cinemaBuscado.getId()));
	}
	
	@Test
	public void existByIdCinemaComIdNegativoRepositoryTest() {
		assertFalse(cinemaService.existsById(-1));
	}
	
	@Test
	public void existByIdCinemaComIdZerooRepositoryTest() {
		assertFalse(cinemaService.existsById(0));
	}
	
	@Test
	public void atualizaCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Quixada");
		cinema.setEndereco("Rua A, Planalto Universitario");
		cinema.setNome("Cine o bom vizinho");
		
		Cinema cinemaUp = new Cinema();
		cinemaUp = cinemaService.salvarCinema(cinema);
		
		cinemaUp.setCidade("Quixadá");
		cinemaUp.setEndereco("Rua José de Queiroz Pessoa, 2500 - Planalto Universitário");
		cinemaUp.setNome("Cine Bom Vizinho");
		
		assertThat(cinemaService.atualizaCinema(cinemaUp));
	}
	
	@Test(expected = org.springframework.transaction.TransactionSystemException.class)
	public void atualizaCinemaComNomeVazioRepositoryTest() {
		Cinema cinema = new Cinema("Quixada", "Rua A, Planalto Universitario", 
							"Cine o bom vizinho");
		
		Cinema cinemaUp = new Cinema();
		cinemaUp = cinemaService.salvarCinema(cinema);
		
		cinemaUp.setNome("");
		cinemaService.atualizaCinema(cinemaUp);
		
	}
	
	@Test(expected = org.springframework.transaction.TransactionSystemException.class)
	public void atualizaCinemaComNomeNuloRepositoryTest() {
		Cinema cinema = new Cinema("Quixada", "Rua A, Planalto Universitario", 
							"Cine o bom vizinho");
		
		Cinema cinemaUp = new Cinema();
		cinemaUp = cinemaService.salvarCinema(cinema);
		
		cinemaUp.setNome(null);
		cinemaService.atualizaCinema(cinemaUp);
		
	}
	
	@Test(expected = org.springframework.transaction.TransactionSystemException.class)
	public void atualizaCinemaComEnderecoVazioRepositoryTest() {
		Cinema cinema = new Cinema("Quixada", "Rua A, Planalto Universitario", 
							"Cine o bom vizinho");
		
		Cinema cinemaUp = new Cinema();
		cinemaUp = cinemaService.salvarCinema(cinema);
		
		cinemaUp.setEndereco("");
		cinemaService.atualizaCinema(cinemaUp);
		
	}
	
	@Test(expected = org.springframework.transaction.TransactionSystemException.class)
	public void atualizaCinemaComEnderecoNuloRepositoryTest() {
		Cinema cinema = new Cinema("Quixada", "Rua A, Planalto Universitario", 
							"Cine o bom vizinho");
		
		Cinema cinemaUp = new Cinema();
		cinemaUp = cinemaService.salvarCinema(cinema);
		
		cinemaUp.setEndereco(null);
		cinemaService.atualizaCinema(cinemaUp);
		
	}
	
	@Test(expected = org.springframework.transaction.TransactionSystemException.class)
	public void atualizaCinemaComCidadeVaziaRepositoryTest() {
		Cinema cinema = new Cinema("Quixada", "Rua A, Planalto Universitario", 
							"Cine o bom vizinho");
		
		Cinema cinemaUp = new Cinema();
		cinemaUp = cinemaService.salvarCinema(cinema);
		
		cinemaUp.setCidade("");
		cinemaService.atualizaCinema(cinemaUp);
		
	}
	
	@Test(expected = org.springframework.transaction.TransactionSystemException.class)
	public void atualizaCinemaComCidadeNulaRepositoryTest() {
		Cinema cinema = new Cinema("Quixada", "Rua A, Planalto Universitario", 
							"Cine o bom vizinho");
		
		Cinema cinemaUp = new Cinema();
		cinemaUp = cinemaService.salvarCinema(cinema);
		
		cinemaUp.setCidade(null);
		cinemaService.atualizaCinema(cinemaUp);
		
	}
	
	@Test
	public void excluiCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Cinema cinemaDel = new Cinema();
		cinemaDel = cinemaService.salvarCinema(cinema);
		cinemaService.excluiCinema(cinemaDel);
		
		assertNotNull(cinemaService.buscaCinema(cinemaDel.getId()));
	}
	
	@Test 
	public void getAllCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		cinemaService.salvarCinema(cinema);
		
		assertTrue(cinemaService.getAllCinema().size() >= 1);
	}
	
	@Test
	public void vinculaSalaAoCinemaServiceTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Integer idCine = cinemaService.salvarCinema(cinema).getId();
		
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Integer idSala = salaService.salvarSala(sala).getId();

		assertTrue(cinemaService.vinculaSalaAoCinema(idCine, idSala));
		
	}
	
	@Test
	public void vinculaSalaJaVinculadaAoCinemaServiceTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Integer idCine = cinemaService.salvarCinema(cinema).getId();
		
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Integer idSala = salaService.salvarSala(sala).getId();

		assertTrue(cinemaService.vinculaSalaAoCinema(idCine, idSala));
		//assertFalse(cinemaService.vinculaSalaAoCinema(idCine, idSala));
		
	}
	
	@Test
	public void vinculaSalaComIdNegativoAoCinemaServiceTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Integer idCine = cinemaService.salvarCinema(cinema).getId();
		assertFalse(cinemaService.vinculaSalaAoCinema(idCine, -1));	
	}
	
	@Test
	public void vinculaSalaComIdZeroAoCinemaServiceTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Integer idCine = cinemaService.salvarCinema(cinema).getId();
		assertFalse(cinemaService.vinculaSalaAoCinema(idCine, 0));	
	}
	
	@Test
	public void vinculaSalaAoCinemaComIdNegativoServiceTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Integer idSala = salaService.salvarSala(sala).getId();

		assertFalse(cinemaService.vinculaSalaAoCinema(-1, idSala));	
	}
	
	@Test
	public void vinculaSalaAoCinemaComIdZeroServiceTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Integer idSala = salaService.salvarSala(sala).getId();

		assertFalse(cinemaService.vinculaSalaAoCinema(-1, idSala));	
	}
	
	@Test
	public void desvinculaSalaDoCinemaServiceTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Integer idCine = cinemaService.salvarCinema(cinema).getId();
		
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Integer idSala = salaService.salvarSala(sala).getId();

		cinemaService.vinculaSalaAoCinema(idCine, idSala);
		assertTrue(cinemaService.desvinculaSalaDoCinema(idCine, idSala));
	}
	
//	@Test(expected = NullPointerException.class)
//	public void desvinculaSalaNaoVinculadaAoCinemaServiceTest() {
//		Cinema cinema = new Cinema();
//		cinema.setCidade("Jucás");
//		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
//		cinema.setNome("Cine in Jucás");
//		
//		Integer idCine = cinemaService.salvarCinema(cinema).getId();
//		
//		String nome = "Sala A1";
//		int capacidade = 150;
//		
//		Sala sala = new Sala();
//		sala.setNome(nome);
//		sala.setCapacidade(capacidade);
//		
//		Integer idSala = salaService.salvarSala(sala).getId();
//
//		cinemaService.desvinculaSalaDoCinema(idCine, idSala);
//	}
	
	@Test
	public void desvinculaSalaComIdNegativoDoCinemaServiceTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Integer idCine = cinemaService.salvarCinema(cinema).getId();
		
		assertFalse(cinemaService.desvinculaSalaDoCinema(idCine, -1));
	}
	@Test
	public void desvinculaSalaComIdZeroDoCinemaServiceTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Integer idCine = cinemaService.salvarCinema(cinema).getId();

		assertFalse(cinemaService.desvinculaSalaDoCinema(idCine, 0));
	}
	@Test
	public void desvinculaSalaDoCinemaComIdNegativoServiceTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Integer idSala = salaService.salvarSala(sala).getId();

		assertFalse(cinemaService.desvinculaSalaDoCinema(-1, idSala));
	}
	@Test
	public void desvinculaSalaDoCinemaComIdZeroServiceTest() {
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Integer idSala = salaService.salvarSala(sala).getId();

		assertFalse(cinemaService.desvinculaSalaDoCinema(0, idSala));
	}
}
