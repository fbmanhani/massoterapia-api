package br.edu.ifsp.manhani.massoterapia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RelatorioDTO {

	@JsonProperty("massagist")
	private String massoterapeuta;
	
	@JsonProperty("sessions")
	private Long sessoes;

}
