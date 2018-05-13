package br.ufc.vev.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Sessao;

@Repository
@Transactional
public interface SessaoRepositorio extends JpaRepository<Sessao, Integer>{
	Sessao findOne(Integer id);
}
