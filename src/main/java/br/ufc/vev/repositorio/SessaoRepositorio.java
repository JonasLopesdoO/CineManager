package br.ufc.vev.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Sessao;

@Repository
@Transactional
public interface SessaoRepositorio extends JpaRepository<Sessao, Integer>{
<<<<<<< HEAD
	Sessao save(Sessao sessao);
	
	Sessao findOne(Integer id);
=======
	
>>>>>>> cf74198a0802c1aa2ce14c7d7ff3e39cfdf4323e
}
