package br.edu.ifsp.manhani.massoterapia.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.manhani.massoterapia.dto.MassoterapeutaDTO;
import br.edu.ifsp.manhani.massoterapia.mapper.MassoterapeutaMapper;
import br.edu.ifsp.manhani.massoterapia.model.Massoterapeuta;
import br.edu.ifsp.manhani.massoterapia.repository.MassoterapeutaRepository;

@Service
@Transactional
public class MassoterapeutaService {

    @Autowired
    private MassoterapeutaRepository repository;

    @Autowired
    private MassoterapeutaMapper mapper;

    public MassoterapeutaDTO findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Transactional(value = TxType.REQUIRED)
    public MassoterapeutaDTO save(MassoterapeutaDTO dto) {
        Massoterapeuta entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(value = TxType.REQUIRED)
    public MassoterapeutaDTO delete(MassoterapeutaDTO dto) {
        Massoterapeuta entity = mapper.toEntity(dto);
        entity.setAtivo(Boolean.FALSE);
        return mapper.toDto(repository.save(entity));
    }
}
