package br.ufc.vev.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.vev.bean.Genero;

public interface GeneroRepositorio extends JpaRepository<Genero, Integer>{
	Genero findByNome(String nome);
}
