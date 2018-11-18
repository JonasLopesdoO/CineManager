package br.ufc.vev.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;
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
	
	private static final Logger logger = Logger.getLogger(String.valueOf(FilmeService.class));

	
	public Filme bucarPorNome(String nome) {
		return filmeRepositorio.findByNome(nome);
	}
	
	public Filme salvarFilme(Filme filme) {
		return filmeRepositorio.save(filme);
	}

	public Filme buscarFilme(Integer id) {
		try {
			return filmeRepositorio.getOne(id);
		} catch (EntityNotFoundException  e) {
			logger.warning("Filme não encontrado");
		}
		return null;
	}

	public void excluirFilme(Filme filme) {
		try {
			filmeRepositorio.delete(filme);
		} catch (IllegalArgumentException e) {
			logger.warning("Parametros incorretos");
		}
	}

	public Filme atualizaFilme(Filme filme) {
		return filmeRepositorio.save(filme);
		
	}

	public List<Filme> getAllFilme() {
		return filmeRepositorio.findAll();
	}
	
	public void vinculaAtorAoFilme(int idFilme, int idAtor) {
		Filme filme = buscarFilme(idFilme);
		if (!filme.equals(null)) {
			Ator ator = atorService.buscarAtor(idAtor);
			if (!ator.equals(null)) {
				if (!filme.getAtores().contains(ator)) {
					filme.getAtores().add(ator);
					ator.getFilmes().add(filme);
					
					filmeRepositorio.save(filme);
					atorService.salvarAtor(ator);
				}
			}
		}		
	}
	
	public void desvinculaAtorDoFilme(int idFilme, int idAtor) {
		Filme filme = buscarFilme(idFilme);
		if (!filme.equals(null)) {
			Ator ator = atorService.buscarAtor(idAtor);
			if (!ator.equals(null)) {
				if (filme.getAtores().contains(ator)) {
					filme.getAtores().remove(ator);
					ator.getFilmes().remove(filme);
					
					filmeRepositorio.save(filme);
					atorService.salvarAtor(ator);
				}
			}
		}
	}
	
	public void vinculaDiretorAoFilme(int idFilme, int idDiretor) {
		Filme filme = buscarFilme(idFilme);
		if (!filme.equals(null)) {
			Diretor diretor = diretorService.buscarDiretor(idDiretor);
			if (!diretor.equals(null)) {
				if (!filme.getDiretores().contains(diretor)) {
					filme.getDiretores().add(diretor);
					diretor.getFilmes().add(filme);
					
					filmeRepositorio.save(filme);
					diretorService.salvarDiretor(diretor);
				}
			}
		}
	}
	
	public void desvinculaDiretorDoFilme(int idFilme, int idDiretor) {
		Filme filme = buscarFilme(idFilme);
		if (!filme.equals(null)) {
			Diretor diretor = diretorService.buscarDiretor(idDiretor);
			if (!diretor.equals(null)) {
				if (filme.getDiretores().contains(diretor)) {
					filme.getDiretores().remove(diretor);
					diretor.getFilmes().remove(filme);
					
					filmeRepositorio.save(filme);
					diretorService.salvarDiretor(diretor);
				}
			}
		}
	}
	
	public void vinculaGeneroAoFilme(int idFilme, int idGenero) {
		Filme filme = buscarFilme(idFilme);
		if (!filme.equals(null)) {
			Genero genero = generoService.buscarGenero(idGenero);
			if (!genero.equals(null)) {
				if (!filme.getGeneros().contains(genero)) {
					filme.getGeneros().add(genero);
					genero.getFilmes().add(filme);
					
					
					filmeRepositorio.save(filme);
					generoService.salvarGenero(genero);	
				}
			}
		}
	}
	
	public void desvinculaGeneroDoFilme(int idFilme, int idGenero) {
		Filme filme = buscarFilme(idFilme);
		if (!filme.equals(null)) {
			Genero genero = generoService.buscarGenero(idGenero);
			if (!genero.equals(null)) {
				if (filme.getGeneros().contains(genero)) {
					filme.getGeneros().remove(genero);
					genero.getFilmes().remove(filme);
					
					
					filmeRepositorio.save(filme);
					generoService.salvarGenero(genero);	
				}
			}
		}
	}
}
