package br.edu.ifsp.manhani.massoterapia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.manhani.massoterapia.model.Massoterapeuta;

@Repository
public interface MassoterapeutaRepository extends CrudRepository<Massoterapeuta, Long> {

}
