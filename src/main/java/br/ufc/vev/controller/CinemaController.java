package br.ufc.vev.controller;

import br.ufc.vev.bean.Filme;

public interface CinemaController {

	public Filme getCinemaPorId(int cinema);
	public Filme getCinemaPorNome(String nomeCinema);

}
