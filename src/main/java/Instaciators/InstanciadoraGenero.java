package Instaciators;

import java.util.ArrayList;
import java.util.List;
import br.ufc.vev.bean.Genero;

public class InstanciadoraGenero {


	private static InstanciadoraGenero instanciaGenero;
		
	private InstanciadoraGenero() {
		
	}
	
	public static InstanciadoraGenero getInstance(){
		if(instanciaGenero == null){
			instanciaGenero = new InstanciadoraGenero();
		}
		return instanciaGenero;
	}
		
	public List<Genero> instanciaGenero(){
		List<Genero> listaDeGeneros = new ArrayList<Genero>();
		
		Genero genero1 = new Genero("Ação");
		
		Genero genero2 = new Genero("Romance");
		
		Genero genero3 = new Genero("Terror");
		
		Genero genero4 = new Genero("Drama");
		
		Genero genero5 = new Genero("Suspense");
	
		listaDeGeneros.add(genero1);
		listaDeGeneros.add(genero2);
		listaDeGeneros.add(genero3);
		listaDeGeneros.add(genero4);
		listaDeGeneros.add(genero5);
		
		return  listaDeGeneros;
	}
	
	
}

