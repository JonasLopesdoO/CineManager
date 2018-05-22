package br.ufc.vev.service;

import java.util.List;

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
	CinemaRepositorio repositorio;

	@Autowired
	SalaService salaService;
	
	public Cinema adicionaCinema(Cinema cinema) {
		return repositorio.save(cinema);
	}

	public Cinema buscaCinema(int id) {
		return repositorio.getOne(id);
	}

	public void excluiCinema(Cinema cinema) {
		repositorio.delete(cinema);
	}

	public Cinema atualizaCinema(Cinema cinema) {
		return repositorio.save(cinema);
	}

	public List<Cinema> getAllCinema() {
		return repositorio.findAll();
	}

	public boolean vinculaSalaAoCinema(int idCine, int idSala) {
		
		Sala sala = salaService.buscarSala(idSala);
		Cinema cinema = repositorio.getOne(idCine);
		
		if (sala.equals(null) || cinema.equals(null)) {
			return false;
		} else {
			cinema.getSalas().add(sala);
			sala.setCinema(cinema);
			repositorio.save(cinema);
			salaService.salvarSala(sala);
			return true;
		}
	}
	
	public void desvinculaSalaDoCinema(int idCine, int idSala) {
		Sala sala = salaService.buscarSala(idSala);
		Cinema cinema = repositorio.getOne(idCine);
		
		if (!sala.equals(null) || !cinema.equals(null)) {
			cinema.getSalas().remove(sala);
			sala.setCinema(null);
			
			repositorio.save(cinema);
			salaService.salvarSala(sala);
		} 
	}
}
