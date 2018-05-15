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
<<<<<<< HEAD
	Sessao save(Sessao sessao);
	
<<<<<<< HEAD
	@Query("select * from Sessao s where s.startDate between ?1 and ?2")
	List<Sessao> findByStartDateBetween(LocalDate dataInicial, LocalDate dataFinal);

	@Query("select * from Sessao s where s.ci between ?1 and ?2")
	List<Sessao> findByFirstname(String cidade);
=======
	Sessao findOne(Integer id);
=======
	
>>>>>>> cf74198a0802c1aa2ce14c7d7ff3e39cfdf4323e
>>>>>>> 648754c7d3e90bcfbd6e30cfaeb18cd8bbd6cff7
}
