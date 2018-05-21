package br.ufc.vev.test.cinema;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.repositorio.CinemaRepositorio;
import br.ufc.vev.repositorio.SalaRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemaRepositoryTest {
	@Autowired
	CinemaRepositorio cRepositorio;
	@Autowired
	SalaRepositorio sRepositorio;
	
	@Test
	public void salvaCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Quixada");
		cinema.setEndereco("Rua A, Planalto Universitario");
		cinema.setNome("Cine o bom vizinho");		
		
		assertNotNull(cRepositorio.save(cinema));
	}
	
	@Test 
	public void buscaCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Quixada");
		cinema.setEndereco("Rua A, Planalto Universitario");
		cinema.setNome("Cine o bom vizinho");
		
		Cinema cinemaBuscado = new Cinema();
		
		cinemaBuscado = cRepositorio.save(cinema);
		
		assertTrue(cRepositorio.existsById(cinemaBuscado.getId()));
	}
	
	@Test
	public void atualizaCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Quixada");
		cinema.setEndereco("Rua A, Planalto Universitario");
		cinema.setNome("Cine o bom vizinho");
		
		Cinema cinemaUp = new Cinema();
		cinemaUp = cRepositorio.save(cinema);
		
		cinemaUp.setCidade("Quixadá");
		cinemaUp.setEndereco("Rua José de Queiroz Pessoa, 2500 - Planalto Universitário");
		cinemaUp.setNome("Cine Bom Vizinho");
		
		assertThat(cRepositorio.save(cinemaUp));
	}
	
	@Test
	public void excluiCinemaRepositoryTest() {
		Cinema cinema = new Cinema();
		cinema.setCidade("Jucás");
		cinema.setEndereco("Travessa João Cavalcante, 2400 - Centro");
		cinema.setNome("Cine in Jucás");
		
		Cinema cinemaDel = new Cinema();
		cinemaDel = cRepositorio.save(cinema);
		cRepositorio.delete(cinemaDel);
		
		assertFalse(cRepositorio.existsById(cinemaDel.getId()));
	}
	
}
