package br.edu.ifsp.manhani.massoterapia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Funcionario implements Serializable {

	private static final long serialVersionUID = -972500207450172743L;

	@Id
	@Column
	private String login;

	@Column
	private String nome;

	@Column
	private Boolean ativo = true;

}
