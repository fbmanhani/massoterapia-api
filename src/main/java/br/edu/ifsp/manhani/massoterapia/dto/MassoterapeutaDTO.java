package br.edu.ifsp.manhani.massoterapia.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MassoterapeutaDTO {

    private UUID id;
    private String nome;
    private Boolean ativo;

}
