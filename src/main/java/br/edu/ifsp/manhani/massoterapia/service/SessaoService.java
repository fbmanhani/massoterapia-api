package br.edu.ifsp.manhani.massoterapia.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.manhani.massoterapia.dto.SessaoDTO;
import br.edu.ifsp.manhani.massoterapia.mapper.SessaoMapper;
import br.edu.ifsp.manhani.massoterapia.repository.SessaoRepository;

@Service
@Transactional
public class SessaoService {

    @Autowired
    private SessaoRepository repository;

    @Autowired
    private SessaoMapper mapper;

    public List<SessaoDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    public SessaoDTO save(SessaoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

}
