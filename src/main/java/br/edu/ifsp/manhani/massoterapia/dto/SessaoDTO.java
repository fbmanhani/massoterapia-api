package br.edu.ifsp.manhani.massoterapia.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoDTO {

	private UUID id;

	@JsonProperty("employee")
	private FuncionarioDTO funcionario;

	@JsonProperty("massagist")

	private FuncionarioDTO massoterapeuta;
	@JsonProperty("unit")
	private UnidadeDTO unidade;

	@JsonProperty("dateTime")
	private LocalDateTime dataHora;

}
