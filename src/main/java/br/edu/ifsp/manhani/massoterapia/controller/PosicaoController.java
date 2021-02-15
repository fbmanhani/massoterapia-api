package br.edu.ifsp.manhani.massoterapia.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.manhani.massoterapia.dto.PosicaoDTO;
import br.edu.ifsp.manhani.massoterapia.service.PosicaoService;
import io.swagger.annotations.Api;

@Api(tags = "Posições")
@RestController
@RequestMapping(path = "/posicao", produces = MediaType.APPLICATION_JSON_VALUE)
public class PosicaoController {

	@Autowired
	private PosicaoService service;

	@GetMapping("/{idUnidade}")
	public List<PosicaoDTO> getAllByUnidade(@PathVariable("idUnidade") UUID idUnidade) {
		return service.getAllByIdUnidade(idUnidade);

	}

	@PostMapping
	public void save(@RequestBody List<PosicaoDTO> lista) {
		service.save(lista);
	}

}
