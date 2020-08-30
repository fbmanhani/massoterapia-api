package br.edu.ifsp.manhani.massoterapia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.manhani.massoterapia.model.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

}
