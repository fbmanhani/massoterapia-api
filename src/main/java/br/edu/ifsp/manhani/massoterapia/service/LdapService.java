package br.edu.ifsp.manhani.massoterapia.service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import br.edu.ifsp.manhani.massoterapia.dto.UsuarioLdapDTO;

@Service
public class LdapService {

	@Autowired
	private LdapTemplate ldapTemplate;

	public List<UsuarioLdapDTO> getAllPeople() {
		List<UsuarioLdapDTO> lista = ldapTemplate.search(query().where("objectclass").is("person"),
				new AttributesMapper<UsuarioLdapDTO>() {
					public UsuarioLdapDTO mapFromAttributes(Attributes attrs) throws NamingException {

						return UsuarioLdapDTO.builder().nomeCompleto(attrs.get("cn").get().toString())
								.usuario(attrs.get("uid").get().toString()).build();
					}
				});

		lista.sort((o1, o2) -> o1.getNomeCompleto().compareTo(o2.getNomeCompleto()));
		return lista;

	}
}
