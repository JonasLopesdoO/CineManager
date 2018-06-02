package br.ufc.vev.bean;

public enum TipoSala {
	D2("2D"), 
	D3("3D");
	
	private String tipo;
	
	private TipoSala(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
}
