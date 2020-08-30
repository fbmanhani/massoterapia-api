package br.edu.ifsp.manhani.massoterapia.mapper;

import org.mapstruct.Mapper;

import br.edu.ifsp.manhani.massoterapia.dto.UnidadeDTO;
import br.edu.ifsp.manhani.massoterapia.model.Unidade;

@Mapper(componentModel = "spring")
public interface UnidadeMapper extends BaseMapper<Unidade, UnidadeDTO> {

}
