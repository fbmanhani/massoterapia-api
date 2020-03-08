package br.edu.ifsp.manhani.mapper;

import org.mapstruct.Mapper;

import br.edu.ifsp.manhani.dto.MassoterapeutaDTO;
import br.edu.ifsp.manhani.model.Massoterapeuta;

@Mapper(componentModel = "spring")
public interface MassoterapeutaMapper {

	MassoterapeutaDTO toDTO(Massoterapeuta entity);

	Massoterapeuta toEntity(MassoterapeutaDTO dto);

}
