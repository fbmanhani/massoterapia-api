package br.edu.ifsp.manhani.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Posicao implements Serializable {

	private static final long serialVersionUID = 4670328178020901856L;

	@Id
	@Column
	private Long id;

	@OneToOne
	private Funcionario funcionario;

}
