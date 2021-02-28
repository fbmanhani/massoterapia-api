package br.edu.ifsp.manhani.massoterapia.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.manhani.massoterapia.dto.RelatorioDTO;
import br.edu.ifsp.manhani.massoterapia.dto.SessaoDTO;
import br.edu.ifsp.manhani.massoterapia.exception.BusinessException;
import br.edu.ifsp.manhani.massoterapia.mapper.SessaoMapper;
import br.edu.ifsp.manhani.massoterapia.messages.MessageProperties;
import br.edu.ifsp.manhani.massoterapia.model.Funcionario;
import br.edu.ifsp.manhani.massoterapia.model.Massoterapeuta;
import br.edu.ifsp.manhani.massoterapia.model.Sessao;
import br.edu.ifsp.manhani.massoterapia.repository.FuncionarioRepository;
import br.edu.ifsp.manhani.massoterapia.repository.MassoterapeutaRepository;
import br.edu.ifsp.manhani.massoterapia.repository.SessaoRepository;

@Service
@Transactional
public class SessaoService {

	@Autowired
	private SessaoRepository repository;

	@Autowired
	private SessaoMapper mapper;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private MassoterapeutaRepository massoterapeutaRepository;

	public List<SessaoDTO> findAll() {
		return mapper.toDto(repository.findAll());
	}

	public SessaoDTO save(SessaoDTO dto) {
		Sessao entity = mapper.toEntity(dto);
		Funcionario f = funcionarioRepository.findByLogin(entity.getFuncionario().getLogin());
		entity.setFuncionario(f);

		Massoterapeuta m = massoterapeutaRepository.findByLogin(entity.getMassoterapeuta().getLogin());
		if (m == null) {
			m = new Massoterapeuta();
			m.setLogin(entity.getMassoterapeuta().getLogin().trim());
			m.setNome(entity.getMassoterapeuta().getNome().trim());
		}
		entity.setMassoterapeuta(m);

		return mapper.toDto(entity);
	}

	public List<RelatorioDTO> findAllGroupedByMassoterapeuta(UUID idUnidade, LocalDate date) {
		List<RelatorioDTO> lista = repository.findAllGroupedByMassoterapeuta(idUnidade, date.getYear(),
				date.getMonthValue());
		if (lista.isEmpty()) {
			throw new BusinessException(MessageProperties.MSG0004);
		}

		return lista;
	}

}
