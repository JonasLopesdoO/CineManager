package br.ufc.vev.controller;

import org.springframework.stereotype.Controller;

import br.ufc.vev.bean.Sala;

@Controller
public interface SalaController {
	
	public Sala buscarSalaId(int sala);
	
	public Sala buscarSalaNome(String nomeSala);
	
//	public Sala atualizarFilme(Sala sala);
//	
//	public Sala addFilme(Sala filme);
//	
//	public Sala removerSala(int id);
}
