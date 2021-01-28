package br.edu.ifsp.manhani.massoterapia.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametrizacaoDTO {

    private UUID id;
    private UnidadeDTO unidade;
    private Integer quantidadePosicoes;
}
