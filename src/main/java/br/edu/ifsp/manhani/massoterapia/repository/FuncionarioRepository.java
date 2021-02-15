package br.edu.ifsp.manhani.massoterapia.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.manhani.massoterapia.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

	Funcionario findByLogin(String login);

}
