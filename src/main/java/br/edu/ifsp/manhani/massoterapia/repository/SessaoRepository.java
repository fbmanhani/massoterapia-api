package br.edu.ifsp.manhani.massoterapia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.manhani.massoterapia.model.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

}
