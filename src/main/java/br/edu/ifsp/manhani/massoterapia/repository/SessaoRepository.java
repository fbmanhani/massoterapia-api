package br.edu.ifsp.manhani.massoterapia.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.manhani.massoterapia.dto.RelatorioDTO;
import br.edu.ifsp.manhani.massoterapia.model.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

	@Query("select new br.edu.ifsp.manhani.massoterapia.dto.RelatorioDTO(s.massoterapeuta.nome, count(*)) from Sessao s "
			+ " where s.unidade.id = :idUnidade and year(s.dataHora) = :ano and month(s.dataHora) = :mes"
			+ " group by s.massoterapeuta.nome order by 1")
	List<RelatorioDTO> findAllGroupedByMassoterapeuta(UUID idUnidade, Integer ano, Integer mes);

}
