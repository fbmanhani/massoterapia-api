package br.edu.ifsp.manhani.massoterapia.mapper;

import org.mapstruct.Mapper;

import br.edu.ifsp.manhani.massoterapia.dto.SessaoDTO;
import br.edu.ifsp.manhani.massoterapia.model.Sessao;

@Mapper(componentModel = "spring")
public interface SessaoMapper extends BaseMapper<Sessao, SessaoDTO> {

}
