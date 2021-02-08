package br.edu.ifsp.manhani.massoterapia.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.manhani.massoterapia.model.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

	@Query("select count(u) > 0 from Unidade u where upper(u.descricao)=upper(:descricao) and u.id <> :id")
	boolean existsByDescricao(String descricao, UUID id);

}
