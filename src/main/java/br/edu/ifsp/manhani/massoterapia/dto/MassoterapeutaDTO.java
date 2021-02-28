package br.edu.ifsp.manhani.massoterapia.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MassoterapeutaDTO {

	private UUID id;

	@JsonProperty("name")
	private String nome;

	private String login;

	@JsonProperty("active")
	private Boolean ativo;

}
