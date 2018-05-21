package br.ufc.vev.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Ator;

@Repository
public interface AtorRepositorio extends JpaRepository<Ator, Integer> {

}
