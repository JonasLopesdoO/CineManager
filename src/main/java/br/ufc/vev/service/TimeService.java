package br.ufc.vev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.vev.bean.Jogador;
import br.ufc.vev.bean.Time;
import br.ufc.vev.repositorio.JogadorRepositorio;
import br.ufc.vev.repositorio.TimeRepositorio;

@Service
public class TimeService {
	
	@Autowired
	TimeRepositorio timeRepositorio;
	
	@Autowired
	JogadorRepositorio jogadorRepositorio;
	
	public Time salvarTime(String nome) {
		Time time = new Time();
		time.setNome(nome);
		timeRepositorio.save(time);
		
		return time;
	}
	
	public List<Time> getTodosTimes(){
		return timeRepositorio.findAll();
	}
	
	public boolean addJogadorAoTime(Integer idTime, Integer jogadorSemTimeId) {
		Time time = timeRepositorio.getOne(idTime);
		 
		if(time.getJogadores().size()==7){
		    return false;
		}else {
		    Jogador jogador = jogadorRepositorio.getOne(jogadorSemTimeId);
		    time.getJogadores().add(jogador);
		    jogador.setTime(time);
		    timeRepositorio.save(time);
		    
		    jogadorRepositorio.save(jogador);
		    return true;
		}
	}
	
	public void delJogadorAoTime(Integer idTime, Integer idJogador) {
		Time time = getTime(idTime);
		JogadorService jogadorService = new JogadorService();
		Jogador jogador =  jogadorService.getJogador(idJogador); 
		
		time.getJogadores().remove(jogador);
		jogador.setTime(null);
		
		timeRepositorio.save(time);
		jogadorRepositorio.save(jogador);
	}

	public Time getTime(Integer id) {
		return timeRepositorio.getOne(id);
	}
}
