package br.edu.ifsp.manhani.massoterapia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Parametrizacao implements Serializable {

	private static final long serialVersionUID = -1853066349639288105L;

	@Id
	@Column
	private final Integer id = 1;

	@Column
	private Integer quantidadePosicoes;

}
