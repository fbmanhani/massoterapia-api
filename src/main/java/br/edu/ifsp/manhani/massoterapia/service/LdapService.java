package br.edu.ifsp.manhani.massoterapia.service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<UsuarioLdapDTO> getAllPeople(String unidade) {
		List<List<String>> massoterapeutas = ldapTemplate.search(
				query().where("objectclass").is("groupOfUniqueNames").and("cn").is("massoterapeuta"),
				new AttributesMapper<List<String>>() {
					public List<String> mapFromAttributes(Attributes attrs) throws NamingException {

						Iterator<?> i = attrs.get("uniquemember").getAll().asIterator();
						List<String> lista = new ArrayList<>();
						while (i.hasNext()) {
							lista.add((String) i.next());
						}
						return lista;
					}
				});

		List<UsuarioLdapDTO> lista = ldapTemplate.search(query().where("objectclass").is("person").and("l").is(unidade),
				new AttributesMapper<UsuarioLdapDTO>() {
					public UsuarioLdapDTO mapFromAttributes(Attributes attrs) throws NamingException {

						return UsuarioLdapDTO.builder().nomeCompleto(attrs.get("cn").get().toString())
								.usuario(attrs.get("uid").get().toString()).build();
					}
				});

		lista.sort((o1, o2) -> o1.getNomeCompleto().compareTo(o2.getNomeCompleto()));

		if (!massoterapeutas.isEmpty()) {
			lista = lista.stream()
					.filter(i -> massoterapeutas.get(0).stream().noneMatch(item -> item.contains(i.getUsuario())))
					.collect(Collectors.toList());
		}
		return lista;

	}
}
