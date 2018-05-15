package br.ufc.vev.repositorio;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Sessao;

@Repository
@Transactional
public interface SessaoRepositorio extends JpaRepository<Sessao, Integer>{

	@Query("select * from Sessao s where s.startDate between ?1 and ?2")
	List<Sessao> findByStartDateBetween(LocalDate dataInicial, LocalDate dataFinal);
}
