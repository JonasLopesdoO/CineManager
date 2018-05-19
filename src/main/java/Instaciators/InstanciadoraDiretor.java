package Instaciators;

import java.util.ArrayList;
import java.util.List;

import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Diretor;

public class InstanciadoraDiretor {


	private static InstanciadoraDiretor pessoaDiretor;
		
	private InstanciadoraDiretor() {
		
	}
	
	public static InstanciadoraDiretor getInstance(){
		if(pessoaDiretor == null){
			pessoaDiretor = new InstanciadoraDiretor();
		}
		return pessoaDiretor;
	}
		
	
	public List<Diretor> instanciaDiretores(){
		ArrayList<Diretor> listaDeDiretores = new ArrayList<Diretor>();
		
		Diretor diretor1 = new Diretor("Christopher Nolan","Atividades Diretor, Roteirista, Produtor mais\r\n" + 
														"Nome de nascimento Christopher Edward Nolan\r\n" + 
														"Nacionalidade Britânico\r\n" + 
														"Nascimento 30 de julho de 1970 (Londres, Inglaterra)\r\n" + 
														"Idade 47 anos");
		
		Diretor diretor2 = new Diretor("Zack Snyder","Atividades Diretor, Produtor, Roteirista mais\r\n" + 
												"Nome de nascimento Zack Edward Snyder\r\n" + 
												"Nacionalidade Americano\r\n" + 
												"Nascimento 1 de março de 1966 (Green Bay, Wisconsin, EUA)\r\n" + 
												"Idade 52 anos");
		
		Diretor diretor3 = new Diretor("Julie Gayet","Atividades Ator, Produtor, Intérprete (músicas do filme) mais\r\n" + 
												"Nome de nascimento Maurice Joseph Micklewhite\r\n" + 
												"Nacionalidade Britânico\r\n" + 
												"Nascimento 14 de março de 1933 (Bermondsey, Londres, Inglaterra)\r\n" + 
												"Idade 85 anos");
		
		Diretor diretor4 = new Diretor("Wally Pfister","Atividades Diretor de fotografia, Diretor\r\n" + 
													"Nacionalidade Americano");
		
		Diretor diretor5 = new Diretor("David Leitch","Atividades Dublê, Coordenador de dublês, Ator mais\r\n" + 
									"Nacionalidade Americano");
	
		listaDeDiretores.add(diretor1);
		listaDeDiretores.add(diretor2);
		listaDeDiretores.add(diretor3);
		listaDeDiretores.add(diretor4);
		listaDeDiretores.add(diretor5);
		
		return listaDeDiretores;
	}
}

