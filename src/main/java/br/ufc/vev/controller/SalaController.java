package br.ufc.vev.controller;

import org.springframework.stereotype.Controller;

import br.ufc.vev.bean.Sala;

@Controller
public interface SalaController {
	public Sala getSalaPorId(int sala);
	
}
