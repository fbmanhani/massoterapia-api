package br.edu.ifsp.manhani.massoterapia.mapper;

import org.mapstruct.Mapper;

import br.edu.ifsp.manhani.massoterapia.dto.MassoterapeutaDTO;
import br.edu.ifsp.manhani.massoterapia.model.Massoterapeuta;

@Mapper(componentModel = "spring")
public interface MassoterapeutaMapper extends BaseMapper<Massoterapeuta, MassoterapeutaDTO> {

}
