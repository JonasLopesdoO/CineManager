package br.ufc.vev.test.pessoa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.vev.bean.Diretor;
import br.ufc.vev.repositorio.DiretorRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiretorRepositorioTest {
	
	@Autowired
	DiretorRepositorio diretorRepositorio;
	
	@Test
	public void salvarDiretorCorretamente() {
		String nome = "Christopher Nolan";
		String sobre = "Atividades Diretor, Roteirista, Produtor mais\\r\\n\" + \r\n" + 
						"\"Nome de nascimento Christopher Edward Nolan\\r\\n\" + \r\n" + 
						"\"Nacionalidade Britânico\\r\\n\" + \r\n" + 
						"\"Nascimento 30 de julho de 1970 (Londres, Inglaterra)\\r\\n\" + \r\n" + 
						"\"Idade 47 anos";
		
		
		Diretor diretor = new Diretor(nome, sobre);
		
		Diretor diretorRecebido = new Diretor();
		diretorRecebido = diretorRepositorio.save(diretor);
		
		assertNotNull(diretorRecebido);
	}
	
//	@Test
//	public void removerDiretorCorretamente() {
//		String nome = "Christopher Nolan";
//		String sobre = "Atividades Diretor, Roteirista, Produtor mais\\r\\n\"" + 
//						"\"Nome de nascimento Christopher Edward Nolan\\r\\n\"" + 
//						"\"Nacionalidade Britânico\\r\\n\"" + 
//						"\"Nascimento 30 de julho de 1970 (Londres, Inglaterra)\\r\\n\"" + 
//						"\"Idade 47 anos";
//		
//		Diretor diretor = new Diretor(nome, sobre);
//		
//		Diretor diretorRecebido = new Diretor();
//		
//		diretorRecebido = diretorRepositorio.save(diretor);
//		
//		diretorRepositorio.delete(diretorRecebido);
//		
//		assertFalse(diretorRepositorio.existsById(diretorRecebido.getId()));
//	}

}
