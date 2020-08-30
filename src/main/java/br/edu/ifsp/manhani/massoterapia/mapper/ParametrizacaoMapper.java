package br.edu.ifsp.manhani.massoterapia.mapper;

import org.mapstruct.Mapper;

import br.edu.ifsp.manhani.massoterapia.dto.ParametrizacaoDTO;
import br.edu.ifsp.manhani.massoterapia.model.Parametrizacao;

@Mapper(componentModel = "spring")
public interface ParametrizacaoMapper extends BaseMapper<Parametrizacao, ParametrizacaoDTO> {

}
