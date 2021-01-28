package br.edu.ifsp.manhani.massoterapia.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoDTO {

    private UUID id;
    private FuncionarioDTO funcionario;
    private MassoterapeutaDTO massoterapeuta;
    private LocalDateTime dataHora;

}
