package br.edu.ifsp.manhani.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MassagemDTO {

	private Long id;
	private FuncionarioDTO funcionario;
	private MassoterapeutaDTO massoterapeuta;
	private LocalDateTime dataHora;

}
