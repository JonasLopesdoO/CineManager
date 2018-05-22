package br.ufc.vev.test.cinema;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
	private CinemaService service;
	@Autowired
	private SalaService salaService;
	
	@Test
	public void salvaCinemaServiceTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Quixada");
		cinema.setEndereco("Rua A, Planalto Universitario");
		cinema.setNome("Cine o bom vizinho");		
		
		assertNotNull(service.adicionaCinema(cinema));
	}
	
	@Test 
	public void buscaCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Quixada");
		cinema.setEndereco("Rua A, Planalto Universitario");
		cinema.setNome("Cine o bom vizinho");
		
		Cinema cinemaBuscado = new Cinema();
		
		cinemaBuscado = service.adicionaCinema(cinema);
		
		assertNotNull(service.buscaCinema(cinemaBuscado.getId()));
	}
	
	@Test
	public void atualizaCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Quixada");
		cinema.setEndereco("Rua A, Planalto Universitario");
		cinema.setNome("Cine o bom vizinho");
		
		Cinema cinemaUp = new Cinema();
		cinemaUp = service.adicionaCinema(cinema);
		
		cinemaUp.setCidade("Quixadá");
		cinemaUp.setEndereco("Rua José de Queiroz Pessoa, 2500 - Planalto Universitário");
		cinemaUp.setNome("Cine Bom Vizinho");
		
		assertThat(service.atualizaCinema(cinemaUp));
	}
	
	@Test
	public void excluiCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Cinema cinemaDel = new Cinema();
		cinemaDel = service.adicionaCinema(cinema);
		service.excluiCinema(cinemaDel);
		
		assertNotNull(service.buscaCinema(cinemaDel.getId()));
	}
	
	@Test 
	public void getAllCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		service.adicionaCinema(cinema);
		
		assertTrue(service.getAllCinema().size() >= 1);
	}
	
	@Test
	public void vinculaSalaAoCinemaServiceTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Integer idCine = service.adicionaCinema(cinema).getId();
		
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Integer idSala = salaService.salvarSala(sala).getId();

		assertTrue(service.vinculaSalaAoCinema(idCine, idSala));
		
	}
	
	@Test
	public void desvinculaSalaDoCinemaServiceTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Integer idCine = service.adicionaCinema(cinema).getId();
		
		String nome = "Sala A1";
		int capacidade = 150;
		
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setCapacidade(capacidade);
		
		Integer idSala = salaService.salvarSala(sala).getId();

		service.vinculaSalaAoCinema(idCine, idSala);
		service.desvinculaSalaDoCinema(idCine, idSala);
	}
}
