package br.edu.ifsp.manhani.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO {

	private String login;
	private String nome;
	private Boolean ativo = true;

}
