package br.ufc.vev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.repositorio.FilmeRepositorio;

@Service
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
	
	public boolean vinculaAtorAoFilme(int idFilme, int idAtor) {
		Filme filme = repositorio.getOne(idFilme);
		Ator ator = atorService.buscarAtor(idAtor);
		
		if (filme.equals(null) || ator.equals(null)) {
			return false;
		} else {
			filme.getAtores().add(ator);
			ator.getFilmes().add(filme);
			repositorio.save(filme);
			atorService.salvarAtor(ator);
			return true;
		}
	}
	
	public void desvinculaAtorDoFilme(int idFilme, int idAtor) {
		Filme filme = buscarFilme(idFilme);
		Ator ator = atorService.buscarAtor(idAtor);
		
		if (!filme.equals(null) || !ator.equals(null)) {
			
			filme.getAtores().remove(ator);
			ator.getFilmes().remove(filme);
			
			repositorio.save(filme);
			atorService.salvarAtor(ator);
		} 
	}
	
	public boolean vinculaDiretorAoFilme(int idFilme, int idDir) {
		Filme filme = repositorio.getOne(idFilme);
		Diretor diretor = diretorService.buscarDiretor(idDir);
		
		if (filme.equals(null) || diretor.equals(null)) {
			return false;
		} else {
			filme.getDiretores().add(diretor);
			diretor.getFilmes().add(filme);
			repositorio.save(filme);
			diretorService.salvarDiretor(diretor);
			return true;
		}
	}
	
	public void desvinculaDiretorDoFilme(int idFilme, int idDir) {
		Filme filme = buscarFilme(idFilme);
		Diretor diretor = diretorService.buscarDiretor(idDir);
		
		if (!filme.equals(null) || !diretor.equals(null)) {
			
			filme.getDiretores().remove(diretor);
			diretor.getFilmes().remove(filme);
			
			repositorio.save(filme);
			diretorService.salvarDiretor(diretor);
		} 
	}
	
	public boolean vinculaGeneroAoFilme(int idFilme, int idGenero) {
		Filme filme = repositorio.getOne(idFilme);
		Genero genero = generoService.buscarGenero(idGenero);
		
		if (filme.equals(null) || genero.equals(null)) {
			return false;
		} else {
			filme.getGeneros().add(genero);
			genero.getFilmes().add(filme);
			repositorio.save(filme);
			generoService.salvarGenero(genero);
			return true;
		}
	}
	
	public void desvinculaGeneroDoFilme(int idFilme, int idGen) {
		Filme filme = buscarFilme(idFilme);
		Genero genero = generoService.buscarGenero(idGen);
		
		if (!filme.equals(null) || !genero.equals(null)) {
			
			filme.getGeneros().remove(genero);
			genero.getFilmes().remove(filme);
			
			repositorio.save(filme);
			generoService.salvarGenero(genero);
		} 
	}
	
	
}
