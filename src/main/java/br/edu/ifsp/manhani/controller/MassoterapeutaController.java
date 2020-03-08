package br.edu.ifsp.manhani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.manhani.dto.MassoterapeutaDTO;
import br.edu.ifsp.manhani.service.MassoterapeutaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Massoterapeuta")
@RestController
@RequestMapping("/massoterapeuta")
public class MassoterapeutaController {

	@Autowired
	private MassoterapeutaService service;

	@ApiOperation(value = "Recupera o massoterapeuta através do ID.")
	@GetMapping("/{id}")
	public ResponseEntity<MassoterapeutaDTO> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@ApiOperation(value = "Insere ou atualiza as informações do massoterapeuta.")
	@PostMapping
	public ResponseEntity<MassoterapeutaDTO> save(MassoterapeutaDTO dto) {
		return ResponseEntity.ok(service.save(dto));
	}

}
