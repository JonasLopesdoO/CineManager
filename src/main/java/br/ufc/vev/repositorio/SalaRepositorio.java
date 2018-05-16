package br.ufc.vev.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Sala;

@Repository
public interface SalaRepositorio extends JpaRepository<Sala, Integer>{
	
}
