package br.ufc.vev.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Diretor;

@Repository
public interface DiretorRepositorio extends JpaRepository<Diretor, Integer>{
	
}
