package br.ufc.vev.controller;

import br.ufc.vev.bean.Filme;

public interface FilmeController {

	public Filme getFilmePorId(int filme);
	public Filme getFilmePorNome(String nomeFilme);

}
