package br.ufc.vev.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Sessao;

@Repository
public interface SessaoRepositorio extends JpaRepository<Sessao, Integer>{

}
