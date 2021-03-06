package br.ufc.vev.test.cinema;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.controller.CinemaController;
import br.ufc.vev.service.CinemaService;
import br.ufc.vev.service.SalaService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemaControllerTest {
	
	@Autowired
	CinemaController controller;
	@Autowired
	CinemaService cinemaService;
	@Autowired
	SalaService salaService;
	
	
	@Test
	public void indexControllerTest() {
		assertNotNull(controller.index());
	}
	
	@Test
	public void formularioControllerTest() {
		assertNotNull(controller.formularioCinema());
	}
	
	@Test
	public void salvaCinemaControllerTest() {
		
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";		
		assertNotNull(controller.salvaCinema(nome, endereco, cidade));
	}
			
	@Test
	public void atualizaCinemaControllerTest() {
		String cidade = "Quixada";
		String endereco = "Rua A, Planalto Universitario";
		String nome = "Cine o bom vizinho";	
		Cinema cinema = new Cinema(nome, endereco, cidade);
		
		Cinema cinemaUp = new Cinema();
		
		cinemaUp = cinemaService.salvarCinema(cinema);
		
		int id = cinemaUp.getId();
		assertNotNull(controller.atualizaCinema(id));
	}
	
	@Test
	public void excluiCinemaControllerTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Cinema cinemaDel = new Cinema();
		cinemaDel = cinemaService.salvarCinema(cinema);
		assertNotNull(controller.excluiCinema(cinemaDel.getId()));
	}
		
	@Test
	public void vinculaSalaAoCinemaControllerTest() {
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

		assertNotNull(controller.vincularSalaAoCinema(idCine, idSala));
		
	}
	
	@Test
	public void desvinculaSalaDoCinemaControllerTest() {
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

		controller.vincularSalaAoCinema(idCine, idSala);
		assertNotNull(controller.desvinculaSalaDoCinema(idCine, idSala));
	}
	
}