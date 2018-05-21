package br.ufc.vev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.repositorio.CinemaRepositorio;

@Service
public class CinemaService {
	@Autowired
	CinemaRepositorio repositorio;

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
}
