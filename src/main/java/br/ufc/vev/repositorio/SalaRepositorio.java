package br.ufc.vev.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.vev.bean.Sala;

public interface SalaRepositorio extends JpaRepository<Sala, Integer>{
	
}
