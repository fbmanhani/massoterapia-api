package br.edu.ifsp.manhani.massoterapia.mapper;

import org.mapstruct.Mapper;

import br.edu.ifsp.manhani.massoterapia.dto.PosicaoDTO;
import br.edu.ifsp.manhani.massoterapia.model.Posicao;

@Mapper(componentModel = "spring")
public interface PosicaoMapper extends BaseMapper<Posicao, PosicaoDTO> {

}
