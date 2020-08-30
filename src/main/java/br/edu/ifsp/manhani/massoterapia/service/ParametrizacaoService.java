package br.edu.ifsp.manhani.massoterapia.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.manhani.massoterapia.dto.ParametrizacaoDTO;
import br.edu.ifsp.manhani.massoterapia.dto.UnidadeDTO;
import br.edu.ifsp.manhani.massoterapia.mapper.ParametrizacaoMapper;
import br.edu.ifsp.manhani.massoterapia.repository.ParametrizacaoRepository;

@Service
@Transactional
public class ParametrizacaoService {

    @Autowired
    private ParametrizacaoRepository repository;

    @Autowired
    private ParametrizacaoMapper mapper;

    public ParametrizacaoDTO findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public ParametrizacaoDTO save(ParametrizacaoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public ParametrizacaoDTO findByUnidade(UnidadeDTO unidadeDTO) {
        return mapper.toDto(repository.findByUnidadeId(unidadeDTO.getId()));
    }

}
