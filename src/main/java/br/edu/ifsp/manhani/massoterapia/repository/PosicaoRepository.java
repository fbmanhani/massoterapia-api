package br.edu.ifsp.manhani.massoterapia.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.manhani.massoterapia.model.Posicao;

@Repository
public interface PosicaoRepository extends JpaRepository<Posicao, UUID> {

	List<Posicao> findAllByUnidadeIdOrderByNumero(UUID id);

	void deleteByUnidadeId(UUID idUnidade);

}
