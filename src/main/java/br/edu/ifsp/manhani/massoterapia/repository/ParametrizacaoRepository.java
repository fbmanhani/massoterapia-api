package br.edu.ifsp.manhani.massoterapia.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.manhani.massoterapia.model.Parametrizacao;

@Repository
public interface ParametrizacaoRepository extends CrudRepository<Parametrizacao, UUID> {

    Parametrizacao findByUnidadeId(UUID idUnidade);

}
