package br.ufc.vev.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Cinema;

@Repository
public interface CinemaRepositorio extends JpaRepository<Cinema, Integer>{
	
}
