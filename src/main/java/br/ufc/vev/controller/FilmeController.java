package br.ufc.vev.controller;

import org.springframework.stereotype.Controller;

import br.ufc.vev.bean.Filme;

@Controller
public interface FilmeController {
	public Filme leFilmePorId(int filme);
}
