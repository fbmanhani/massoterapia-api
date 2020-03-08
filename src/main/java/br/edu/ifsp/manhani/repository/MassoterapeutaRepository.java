package br.edu.ifsp.manhani.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.manhani.model.Massoterapeuta;

@Repository
public interface MassoterapeutaRepository extends CrudRepository<Massoterapeuta, Long> {

}
