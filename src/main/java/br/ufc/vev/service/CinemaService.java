package br.ufc.vev.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.repositorio.CinemaRepositorio;

@Service
@Transactional
@Rollback(false)
public class CinemaService {
	@Autowired
	CinemaRepositorio cinemaRepositorio;

	@Autowired
	SalaService salaService;
	private static final Logger logger = Logger.getLogger(String.valueOf(CinemaService.class));

	
	public Cinema salvarCinema(Cinema cinema) {
		return cinemaRepositorio.save(cinema);
	}

	public Cinema buscaCinema(int id) {
		try {
			return cinemaRepositorio.getOne(id);
		} catch (EntityNotFoundException  e) {
			logger.warning("Ator não encontrado");
		}
		return null;
	}

	public void excluiCinema(Cinema cinema) {
		try {
			cinemaRepositorio.delete(cinema);
		} catch (IllegalArgumentException e) {
			logger.warning("Parametros incorretos");
		}
	}

	public Cinema atualizaCinema(Cinema cinema) {
		return cinemaRepositorio.save(cinema);
	}

	public List<Cinema> getAllCinema() {
		return cinemaRepositorio.findAll();
	}
	
	public void vinculaSalaAoCinema(int idCinema, int idSala) {
		Cinema cinema = cinemaRepositorio.getOne(idCinema);
		if (cinema != null) {
			Sala sala = salaService.buscarSala(idSala);
			if (sala != null && !cinema.getSalas().contains(sala)) {
				cinema.getSalas().add(sala);
				sala.setCinema(cinema);
				cinemaRepositorio.save(cinema);
				salaService.salvarSala(sala);
				
			}
		}
	}
	
	public void desvinculaSalaDoCinema(int idCinema, int idSala) {
		Cinema cinema = cinemaRepositorio.getOne(idCinema);
		if (cinema != null) {
			Sala sala = salaService.buscarSala(idSala);
			if (sala != null && cinema.getSalas().contains(sala)) {
				cinema.getSalas().remove(sala);
				sala.setCinema(null);
				cinemaRepositorio.save(cinema);
				salaService.salvarSala(sala);
				
			}
		}
	}	
}
