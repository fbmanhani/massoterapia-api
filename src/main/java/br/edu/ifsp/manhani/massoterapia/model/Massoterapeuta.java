package br.edu.ifsp.manhani.massoterapia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Massoterapeuta implements Serializable {

	private static final long serialVersionUID = -6509305707391691043L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private Long id;

	@Column(length = 150)
	private String nome;

	@Column
	private Boolean ativo = true;

}
