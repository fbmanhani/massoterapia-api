package br.edu.ifsp.manhani.massoterapia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.manhani.massoterapia.dto.UnidadeDTO;
import br.edu.ifsp.manhani.massoterapia.service.UnidadeService;
import io.swagger.annotations.Api;

@Api(tags = "Unidades")
@RestController
@RequestMapping(path = "/unidade", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnidadeController {

	@Autowired
	private UnidadeService service;

	@GetMapping
	public List<UnidadeDTO> findAll() {
		return service.findAll();
	}

	@PostMapping
	public UnidadeDTO save(@RequestBody UnidadeDTO dto) {
		return service.save(dto);
	}

}
