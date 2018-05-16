package br.ufc.vev.controller;

import br.ufc.vev.bean.Cinema;

public interface CinemaController {
	public Cinema buscarCinemaPorId(int cinema);
	public Cinema buscarCinemaPorNome(String nomeCinema);

}

