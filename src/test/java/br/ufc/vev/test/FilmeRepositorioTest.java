package br.ufc.vev.test;

import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import Instaciators.InstanciadoraAtor;
import Instaciators.InstanciadoraDiretor;
import Instaciators.InstanciadoraGenero;
import br.ufc.vev.bean.Ator;
import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.repositorio.FilmeRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmeRepositorioTest {
	
	@Autowired
	FilmeRepositorio filmeRepositorio;
	
	@Test
	public void salvarFilmeCorretamente() {
		String nome = "Interestelar";
		String sinopse = "As reservas naturais da Terra estão chegando ao fim e um grupo de astronautas"
						+ " recebe a missão de verificar possíveis planetas para receberem a população mundial, "
						+ "possibilitando a continuação da espécie.";
		LocalTime duracao = LocalTime.of(02, 39);
		List<Ator> atores = InstanciadoraAtor.getInstance().instanciaAtores();
		List<Diretor> diretores = InstanciadoraDiretor.getInstance().instanciaDiretores();
		List<Genero> generos = InstanciadoraGenero.getInstance().instanciaGenero();
		
		Filme filme = new Filme(nome, sinopse, duracao, atores, diretores, generos);
		
		Filme filmeRecebido = filmeRepositorio.save(filme);
		
		System.out.println(filmeRecebido);
		
		assertNotNull(filmeRecebido);
	}

}
