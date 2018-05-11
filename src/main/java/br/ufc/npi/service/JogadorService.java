package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.repositorio.JogadorRepositorio;

@Service
public class JogadorService {
	
	@Autowired
	JogadorRepositorio jogadorRepositorio;
	
	public Jogador salvarJogador(String nome, int idade) {
		Jogador jogador = new Jogador();
		jogador.setNome(nome);
		jogador.setIdade(idade);
		jogadorRepositorio.save(jogador);
		
		return jogador;
	}
	
	public Jogador getJogador(Integer id) {
		return jogadorRepositorio.getOne(id);
	}
	
	public List<Jogador> getTodosJogadores(){
		return jogadorRepositorio.findAll();
	}
	
	public List<Jogador> getJogadoresSemTime(){
		return jogadorRepositorio.buscarJogadorSemTime();
	}
}
