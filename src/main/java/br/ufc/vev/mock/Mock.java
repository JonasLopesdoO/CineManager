package br.ufc.vev.mock;

import org.mockito.Mockito;

import br.ufc.vev.bean.Cinema;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.controller.CinemaController;
import br.ufc.vev.controller.FilmeController;
import br.ufc.vev.controller.SalaController;

public class Mock {
	private static FilmeController filmeControlMock;
	private static SalaController salaControlMock;
	private CinemaController cinemaControlMock;
	
	public Mock() {
		
	}
	
	public static FilmeController mockFilme() {
		filmeControlMock = Mockito.mock(FilmeController.class);
		
		Filme filme1 = new Filme(1); //interestelar
		Filme filme2 = new Filme(2); //deadpool
		Filme filme3 = new Filme(3); //break
		
		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme1);
		Mockito.when(filmeControlMock.buscarFilmeNome("interestelar")).thenReturn(filme1);
		
		Mockito.when(filmeControlMock.buscarFilmeId(2)).thenReturn(filme2);
		Mockito.when(filmeControlMock.buscarFilmeNome("deadpool")).thenReturn(filme2);
		
		Mockito.when(filmeControlMock.buscarFilmeId(3)).thenReturn(filme3);
		Mockito.when(filmeControlMock.buscarFilmeNome("break")).thenReturn(filme3);
		
		return filmeControlMock;
	}
	
	public static SalaController mockSala() {
		salaControlMock= Mockito.mock(SalaController.class);
		
		Sala sala1 = new Sala(1); 
		Sala sala2 = new Sala(2); 
		Sala sala3 = new Sala(3); 
		
		Mockito.when(salaControlMock.buscarSalaId(1)).thenReturn(sala1);
		
		Mockito.when(salaControlMock.buscarSalaId(2)).thenReturn(sala2);
		
		Mockito.when(salaControlMock.buscarSalaId(3)).thenReturn(sala3);
		
		return salaControlMock;
	}
	
	public void mockCinema() {
		this.cinemaControlMock= Mockito.mock(CinemaController.class);
		
		Cinema cinema1 = new Cinema(1); 
		Cinema cinema2 = new Cinema(2); 
		Cinema cinema3 = new Cinema(3); 
		
		Mockito.when(cinemaControlMock.buscarCinemaPorId(1)).thenReturn(cinema1);
		
		Mockito.when(cinemaControlMock.buscarCinemaPorId(2)).thenReturn(cinema2);
		
		Mockito.when(cinemaControlMock.buscarCinemaPorId(3)).thenReturn(cinema3);
		
	}
}
