package br.ufc.vev.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Sala;

@Repository
@Transactional
public interface SalaRepositorio extends JpaRepository<Sala, Integer>{
	
//	@Query("from sala s where s.cinema_id is null")
//	List<Sala> buscarSalaVazia();
}
