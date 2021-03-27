package br.edu.ifsp.manhani.massoterapia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.manhani.massoterapia.dto.UsuarioLdapDTO;
import br.edu.ifsp.manhani.massoterapia.service.LdapService;

@RestController
@RequestMapping(path = "/ldap", produces = MediaType.APPLICATION_JSON_VALUE)
public class LdapController {

	@Autowired
	private LdapService service;

	@GetMapping("/users/{unidade}")
	public List<UsuarioLdapDTO> findAll(@PathVariable("unidade") String unidade) {
		return service.getAllPeople(unidade);
	}

}
