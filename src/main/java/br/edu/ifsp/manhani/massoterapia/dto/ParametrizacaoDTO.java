package br.edu.ifsp.manhani.massoterapia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametrizacaoDTO {

    private Long id;
    private UnidadeDTO unidade;
    private Integer quantidadePosicoes;
}
