package br.ufc.vev.controller;

import br.ufc.vev.bean.Cinema;

public interface CinemaController {
	public Cinema getCinemaPorId(int cinema);
	public Cinema getCinemaPorNome(String nomeCinema);

}

