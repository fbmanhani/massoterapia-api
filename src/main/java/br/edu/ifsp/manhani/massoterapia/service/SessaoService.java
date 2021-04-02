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
import br.edu.ifsp.manhani.massoterapia.model.Sessao;
import br.edu.ifsp.manhani.massoterapia.model.TipoFuncionarioEnum;
import br.edu.ifsp.manhani.massoterapia.repository.FuncionarioRepository;
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

	public List<SessaoDTO> findAll() {
		return mapper.toDto(repository.findAll());
	}

	public SessaoDTO save(SessaoDTO dto) {
		Sessao entity = mapper.toEntity(dto);
		Funcionario f = funcionarioRepository.findByLogin(entity.getFuncionario().getLogin());
		entity.setFuncionario(f);

		Funcionario masso = funcionarioRepository.findByLogin(entity.getMassoterapeuta().getLogin());
		if (masso == null) {
			masso = new Funcionario();
			masso.setLogin(entity.getMassoterapeuta().getLogin().trim());
			masso.setNome(entity.getMassoterapeuta().getNome().trim());
			masso.setFoto(entity.getMassoterapeuta().getFoto());
			masso.setDataNascimento(entity.getMassoterapeuta().getDataNascimento());
			masso.setTipo(TipoFuncionarioEnum.MASSOTERAPEUTA);
		}
		entity.setMassoterapeuta(masso);

		return mapper.toDto(repository.save(entity));
	}

	public List<RelatorioDTO> countAllGroupedByMassoterapeuta(UUID idUnidade, LocalDate date) {
		List<RelatorioDTO> lista = repository.countAllGroupedByMassoterapeuta(idUnidade, date.getYear(),
				date.getMonthValue());
		if (lista.isEmpty()) {
			throw new BusinessException(MessageProperties.MSG0004);
		}
		return lista;
	}

	public RelatorioDTO countByMassoterapeuta(String login, LocalDate date) {
		Long qtd = repository.countByMassoterapeuta(login, date.getYear(), date.getMonthValue());
		qtd = qtd == null ? 0l : qtd;
		return new RelatorioDTO(login, qtd);
	}

}
