package br.ufc.vev.controller;

import br.ufc.vev.bean.Filme;

public interface FilmeController {

	public Filme buscarFilmeId(int filme);
	
	public Filme buscarFilmeNome(String nomeFilme);
	
	public Filme atualizarFilme(Filme filme);
	
	public Filme addFilme(Filme filme);
	
	public Filme removerFilme(int id);
	
	
}
