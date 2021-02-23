package br.edu.ifsp.manhani.massoterapia.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.manhani.massoterapia.dto.RelatorioDTO;
import br.edu.ifsp.manhani.massoterapia.dto.SessaoDTO;
import br.edu.ifsp.manhani.massoterapia.service.SessaoService;
import io.swagger.annotations.Api;

@Api(tags = "Sessões")
@RestController
@RequestMapping(path = "/sessao", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessaoController {

	@Autowired
	private SessaoService service;

	@PostMapping
	public SessaoDTO save(SessaoDTO dto) {
		return service.save(dto);
	}

	@GetMapping("/{idUnidade}/{date}")
	public List<RelatorioDTO> findAllGroupedByMassoterapeuta(@PathVariable("idUnidade") UUID idUnidade,
			@PathVariable("date") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate date) {
		return service.findAllGroupedByMassoterapeuta(idUnidade, date);
	}

}
