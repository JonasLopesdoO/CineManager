package br.ufc.vev.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.vev.bean.Cinema;


public interface CinemaRepositorio extends JpaRepository<Cinema, Integer>{
	
	Cinema findOne(Integer id);

}
