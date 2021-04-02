package br.edu.ifsp.manhani.massoterapia.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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

	@JsonProperty(value = "dateOfBirth")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataNascimento;

	@JsonProperty(value = "picture")
	private String foto;

}
