package br.edu.ifsp.manhani.massoterapia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.manhani.massoterapia.dto.SessaoDTO;
import br.edu.ifsp.manhani.massoterapia.service.SessaoService;
import io.swagger.annotations.Api;

@Api(tags = "Sessões")
@RestController
@RequestMapping(path = "/sessao", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SessaoController {

    @Autowired
    private SessaoService service;

    @PostMapping
    public SessaoDTO save(SessaoDTO dto) {
        return service.save(dto);
    }

}
