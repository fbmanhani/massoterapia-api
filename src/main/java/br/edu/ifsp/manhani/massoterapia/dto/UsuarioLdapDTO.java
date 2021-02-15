package br.edu.ifsp.manhani.massoterapia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder()
@Getter
@Setter
public class UsuarioLdapDTO {

	@JsonProperty(value = "username")
	private String usuario;

	@JsonProperty(value = "fullname")
	private String nomeCompleto;

}
