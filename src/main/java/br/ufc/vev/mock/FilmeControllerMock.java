package br.ufc.vev.mock;

import org.mockito.Mockito;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.controller.FilmeController;

public class FilmeControllerMock {
	private FilmeController filmeControlMock;
	
	public FilmeControllerMock() {
		this.filmeControlMock = Mockito.mock(FilmeController.class);
		
		Filme filme = new Filme();
		
		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme);
		Mockito.when(filmeControlMock.buscarFilmeNome("Lagoa Azul")).thenReturn(filme);
		
	}
}
