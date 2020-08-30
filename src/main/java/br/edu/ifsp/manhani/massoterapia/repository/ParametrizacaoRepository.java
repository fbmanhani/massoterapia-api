package br.edu.ifsp.manhani.massoterapia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.manhani.massoterapia.model.Parametrizacao;

@Repository
public interface ParametrizacaoRepository extends CrudRepository<Parametrizacao, Long> {

    Parametrizacao findByUnidadeId(Long idUnidade);

}
