package br.edu.ifsp.manhani.massoterapia.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.manhani.massoterapia.dto.UnidadeDTO;
import br.edu.ifsp.manhani.massoterapia.mapper.UnidadeMapper;
import br.edu.ifsp.manhani.massoterapia.repository.UnidadeRepository;

@Service
@Transactional
public class UnidadeService {

    @Autowired
    private UnidadeRepository repository;

    @Autowired
    private UnidadeMapper mapper;

    public List<UnidadeDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    public UnidadeDTO findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

}