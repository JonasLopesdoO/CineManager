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
	FilmeRepositorio repositorio;
	@Autowired
	AtorService atorService;
	@Autowired
	DiretorService diretorService;
	@Autowired
	GeneroService generoService;
	
	public Filme salvarFilme(Filme filme) {
		return repositorio.save(filme);
	}

	public Filme buscarFilme(Integer id) {
		return repositorio.getOne(id);
	}

	public void excluirFilme(Filme filme) {
		repositorio.delete(filme);
	}

	public Filme atualizaFilme(Filme filme) {
		return repositorio.save(filme);
		
	}

	public List<Filme> getAllFilme() {
		return repositorio.findAll();
	}
	
	public boolean existsById(int id) {
		return repositorio.existsById(id);
	}
	
	public void vinculaAtorAoFilme(int idFilme, int idAtor) {
		Filme filme = repositorio.getOne(idFilme);
		Ator ator = atorService.buscarAtor(idAtor);
		
		filme.getAtores().add(ator);
		ator.getFilmes().add(filme);
		repositorio.save(filme);
		atorService.salvarAtor(ator);		
	}
	
	public void desvinculaAtorDoFilme(int idFilme, int idAtor) {
		Filme filme = buscarFilme(idFilme);
		Ator ator = atorService.buscarAtor(idAtor);
		
		filme.getAtores().remove(ator);
		ator.getFilmes().remove(filme);
		repositorio.save(filme);
		atorService.salvarAtor(ator);
	}
	
	public void vinculaDiretorAoFilme(int idFilme, int idDir) {
		Filme filme = repositorio.getOne(idFilme);
		Diretor diretor = diretorService.buscarDiretor(idDir);
				
		filme.getDiretores().add(diretor);
		diretor.getFilmes().add(filme);
		repositorio.save(filme);
		diretorService.salvarDiretor(diretor);		
	}
	
	public void desvinculaDiretorDoFilme(int idFilme, int idDir) {
		Filme filme = buscarFilme(idFilme);
		Diretor diretor = diretorService.buscarDiretor(idDir);
					
		filme.getDiretores().remove(diretor);
		diretor.getFilmes().remove(filme);	
		repositorio.save(filme);
		diretorService.salvarDiretor(diretor);
	}
	
	public void vinculaGeneroAoFilme(int idFilme, int idGenero) {
		Filme filme = repositorio.getOne(idFilme);
		Genero genero = generoService.buscarGenero(idGenero);
		
		filme.getGeneros().add(genero);
		genero.getFilmes().add(filme);
		repositorio.save(filme);
		generoService.salvarGenero(genero);	
	}
	
	public void desvinculaGeneroDoFilme(int idFilme, int idGen) {
		Filme filme = buscarFilme(idFilme);
		Genero genero = generoService.buscarGenero(idGen);
					
		filme.getGeneros().remove(genero);
		genero.getFilmes().remove(filme);		
		repositorio.save(filme);
		generoService.salvarGenero(genero); 
	}
}
