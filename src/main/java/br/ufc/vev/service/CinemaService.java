package br.ufc.vev.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	
	public Cinema salvarCinema(Cinema cinema) {
		return cinemaRepositorio.save(cinema);
	}

	public Cinema buscaCinema(int id) {
		return cinemaRepositorio.getOne(id);
	}

	public void excluiCinema(Cinema cinema) {
		cinemaRepositorio.delete(cinema);
	}

	public Cinema atualizaCinema(Cinema cinema) {
		return cinemaRepositorio.save(cinema);
	}

	public List<Cinema> getAllCinema() {
		return cinemaRepositorio.findAll();
	}
	
	public boolean existsById(int id) {
		return cinemaRepositorio.existsById(id);
	}

	public boolean vinculaSalaAoCinema(int idCinema, int idSala) {
		
		if (existsById(idCinema) && salaService.buscaSala(idSala)) {
			
			Sala sala = salaService.buscarSala(idSala);
			Cinema cinema = cinemaRepositorio.getOne(idCinema);
			
			try {
				cinema.addSala(sala);
				
				cinemaRepositorio.save(cinema);
				salaService.salvarSala(sala);
				return true;
			} catch (DataIntegrityViolationException e) {
				e.printStackTrace();
			}
			
		}
		
		return false;
		
	}
	
	public boolean desvinculaSalaDoCinema(int idCinema, int idSala) {
		
		
		if (existsById(idCinema) && salaService.buscaSala(idSala)) {
			Sala sala = salaService.buscarSala(idSala);
			Cinema cinema = cinemaRepositorio.getOne(idCinema);
			
			cinema.removeSala(sala);
					
			cinemaRepositorio.save(cinema);
			salaService.salvarSala(sala);
			return true;
			
		}
		return false;
	}	
}
