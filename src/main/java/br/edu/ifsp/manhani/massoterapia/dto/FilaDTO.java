package br.edu.ifsp.manhani.massoterapia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilaDTO {

	private String nome;
	private String login;
	private Integer posicao;

}
