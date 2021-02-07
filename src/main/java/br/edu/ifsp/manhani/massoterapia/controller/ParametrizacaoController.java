package br.edu.ifsp.manhani.massoterapia.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.manhani.massoterapia.dto.ParametrizacaoDTO;
import br.edu.ifsp.manhani.massoterapia.service.ParametrizacaoService;
import io.swagger.annotations.Api;

@Api(tags = "Parametrização")
@RestController
@RequestMapping(path = "/parametrizacao", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ParametrizacaoController {

    @Autowired
    private ParametrizacaoService service;

    @GetMapping("/unidade/{id}")
    public ParametrizacaoDTO findByUnidade(@PathVariable("id") UUID id) {
        return service.findByUnidade(id);
    }

    @PostMapping
    public ParametrizacaoDTO save(ParametrizacaoDTO dto) {
        return service.save(dto);
    }

}
