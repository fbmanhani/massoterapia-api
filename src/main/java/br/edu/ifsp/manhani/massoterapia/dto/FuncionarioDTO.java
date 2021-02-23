package br.edu.ifsp.manhani.massoterapia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO {

	@JsonProperty("username")
	private String login;

	@JsonProperty("fullname")
	private String nome;

	private Boolean ativo;

}
