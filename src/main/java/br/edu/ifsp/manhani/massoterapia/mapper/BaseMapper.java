package br.edu.ifsp.manhani.massoterapia.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;

public interface BaseMapper<E, D> {

    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDto(List<E> entities);

    List<D> toDto(Iterable<E> entities);

    List<E> toEntity(List<D> dtos);

    @InheritInverseConfiguration(name = "toDto")
    void fromDto(D dto, @MappingTarget E entity);
}
