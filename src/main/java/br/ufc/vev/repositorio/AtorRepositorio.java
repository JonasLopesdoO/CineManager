package br.ufc.vev.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Ator;

@Repository
public interface AtorRepositorio extends JpaRepository<Ator, Integer> {
	
//	@Modifying
//	@Query("delete from filme_atores where ator_id = ?1 and filme_id = ?2")
//	public void deleteFilmeAtores(int idAtor, int idFilme);

}
