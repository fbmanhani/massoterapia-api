package br.edu.ifsp.manhani.massoterapia.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PosicaoDTO {

	private UUID id;

	@JsonProperty("employee")
	private FuncionarioDTO funcionario;

	@JsonProperty("unit")
	private UnidadeDTO unidade;

	@JsonProperty("number")
	private Integer numero;

}
