package br.ufc.vev.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Sala;

@Repository
@Transactional
public interface SalaRepositorio extends JpaRepository<Sala, Integer>{

}
