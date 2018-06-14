package br.ufc.vev.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.repositorio.FilmeRepositorio;

@Service
@Transactional
@Rollback(false)
public class FilmeService {
	@Autowired
	FilmeRepositorio filmeRepositorio;
	@Autowired
	AtorService atorService;
	@Autowired
	DiretorService diretorService;
	@Autowired
	GeneroService generoService;
	
	public Filme bucarPorNome(String nome) {
		return filmeRepositorio.findByNome(nome);
	}
	
	public Filme salvarFilme(Filme filme) {
		return filmeRepositorio.save(filme);
	}

	public Filme buscarFilme(Integer id) {
		return filmeRepositorio.getOne(id);
	}

	public void excluirFilme(Filme filme) {
		filmeRepositorio.delete(filme);
	}

	public Filme atualizaFilme(Filme filme) {
		return filmeRepositorio.save(filme);
		
	}

	public List<Filme> getAllFilme() {
		return filmeRepositorio.findAll();
	}
	
	public boolean existsById(int id) {
		return filmeRepositorio.existsById(id);
	}
	
	public void vinculaAtorAoFilme(int idFilme, int idAtor) {
		Filme filme = filmeRepositorio.getOne(idFilme);
		Ator ator = atorService.buscarAtor(idAtor);
		
		ator.addFilme(filme);
		
		filmeRepositorio.save(filme);
		atorService.salvarAtor(ator);
				
	}
	
	public void desvinculaAtorDoFilme(int idFilme, int idAtor) {
		Filme filme = buscarFilme(idFilme);
		Ator ator = atorService.buscarAtor(idAtor);
		
		ator.removerFilme(filme);
		
		filmeRepositorio.save(filme);
		atorService.salvarAtor(ator);
	}
	
	public void vinculaDiretorAoFilme(int idFilme, int idDiretor) {
		Filme filme = filmeRepositorio.getOne(idFilme);
		Diretor diretor = diretorService.buscarDiretor(idDiretor);
				
		diretor.addFilme(filme);
		
		filmeRepositorio.save(filme);
		diretorService.salvarDiretor(diretor);		
	}
	
	public void desvinculaDiretorDoFilme(int idFilme, int idDiretor) {
		Filme filme = buscarFilme(idFilme);
		Diretor diretor = diretorService.buscarDiretor(idDiretor);
					
		diretor.removerFilme(filme);
		
		filmeRepositorio.save(filme);
		diretorService.salvarDiretor(diretor);
	}
	
	public void vinculaGeneroAoFilme(int idFilme, int idGenero) {
		Filme filme = filmeRepositorio.getOne(idFilme);
		Genero genero = generoService.buscarGenero(idGenero);
		
		genero.addFilme(filme);
		
		filmeRepositorio.save(filme);
		generoService.salvarGenero(genero);	
	}
	
	public void desvinculaGeneroDoFilme(int idFilme, int idGenero) {
		Filme filme = buscarFilme(idFilme);
		Genero genero = generoService.buscarGenero(idGenero);
					
		genero.removerFilme(filme);
		
		filmeRepositorio.save(filme);
		generoService.salvarGenero(genero);
	}
}
