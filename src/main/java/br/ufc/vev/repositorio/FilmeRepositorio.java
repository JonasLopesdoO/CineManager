package br.ufc.vev.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.vev.bean.Filme;

public interface FilmeRepositorio extends JpaRepository<Filme, Integer>{
	Filme findOne(Integer id);
}
