package br.edu.ifsp.manhani.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Fila {

	@Id
	@Column
	private Long id;

	@ManyToOne
	private Posicao posicao;

}
