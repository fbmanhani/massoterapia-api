package br.edu.ifsp.manhani.massoterapia.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifsp.manhani.massoterapia.dto.PosicaoDTO;
import br.edu.ifsp.manhani.massoterapia.mapper.PosicaoMapper;
import br.edu.ifsp.manhani.massoterapia.model.Funcionario;
import br.edu.ifsp.manhani.massoterapia.model.Posicao;
import br.edu.ifsp.manhani.massoterapia.repository.FuncionarioRepository;
import br.edu.ifsp.manhani.massoterapia.repository.PosicaoRepository;

@Service
@Transactional
public class PosicaoService {

	@Autowired
	private PosicaoRepository repository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private PosicaoMapper mapper;

	public List<PosicaoDTO> getAllByIdUnidade(UUID idUnidade) {
		return mapper.toDto(repository.findAllByUnidadeIdOrderByNumero(idUnidade));
	}

	public void save(List<PosicaoDTO> lista) {
		UUID idUnidade = lista.get(0).getUnidade().getId();
		repository.deleteByUnidadeId(idUnidade);
		List<Posicao> posicoes = mapper.toEntity(lista);
		for (Posicao posicao : posicoes) {
			if (posicao.getFuncionario() != null && posicao.getFuncionario().getId() == null) {
				Funcionario f = funcionarioRepository.findByLogin(posicao.getFuncionario().getLogin());
				if (f == null) {
					f = Funcionario.builder().login(posicao.getFuncionario().getLogin())
							.nome(posicao.getFuncionario().getNome()).build();
				}

				posicao.setFuncionario(f);
			}
		}

		repository.saveAll(posicoes);
	}

}
