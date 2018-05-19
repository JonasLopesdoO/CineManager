package Instaciators;

import java.util.ArrayList;
import java.util.List;
import br.ufc.vev.bean.Ator;

public class InstanciadoraAtor {


	private static InstanciadoraAtor pessoaAtor;
		
	private InstanciadoraAtor() {
		
	}
	
	public static InstanciadoraAtor getInstance(){
		if(pessoaAtor == null){
			pessoaAtor = new InstanciadoraAtor();
		}
		return pessoaAtor;
	}
		
	public List<Ator> instanciaAtores(){
		List<Ator> listaDeAtores = new ArrayList<Ator>();
		
		Ator ator1 = new Ator("Matthew McConaughey","Atividades Ator, Produtor, Produtor de set\r\n" + 
													  "Nacionalidade Americano\r\n" + 
													  "Nascimento 4 de novembro de 1969 (Uvalde, Texas, EUA)\r\n" + 
													  "Idade 48 anos");
		
		Ator ator2 = new Ator("Anne Hathaway","Atividades Atriz, Produtora\r\n" + 
												"Nome de nascimento Anne Jacqueline Hathaway\r\n" + 
												"Nacionalidade Americana\r\n" + 
												"Nascimento 12 de novembro de 1982 (Brooklyn, Nova York, EUA)\r\n" + 
												"Idade 35 anos");
		
		Ator ator3 = new Ator("Michael Caine","Atividades Ator, Produtor, Intérprete (músicas do filme) mais\r\n" + 
												"Nome de nascimento Maurice Joseph Micklewhite\r\n" + 
												"Nacionalidade Britânico\r\n" + 
												"Nascimento 14 de março de 1933 (Bermondsey, Londres, Inglaterra)\r\n" + 
												"Idade 85 anos");
		
		Ator ator4 = new Ator("John Lithgow","Atividade Ator\r\n" + 
												"Nacionalidade Americano\r\n" + 
												"Nascimento 19 de outubro de 1945 (Rochester, Nova York, EUA)\r\n" + 
												"Idade 72 anos");
		
		Ator ator5 = new Ator("Jessica Chastain","Atividades Atriz, Produtora, Produtora de set\r\n" + 
													"Nome de nascimento Jessica Michelle Chastain\r\n" + 
													"Nacionalidade Americana\r\n" + 
													"Nascimento 24 de março de 1977 (Califórnia, Estados Unidos)\r\n" + 
													"Idade 41 anos");
	
		listaDeAtores.add(ator1);
		listaDeAtores.add(ator2);
		listaDeAtores.add(ator3);
		listaDeAtores.add(ator4);
		listaDeAtores.add(ator5);
		
		return  listaDeAtores;
	}
	
	
}

