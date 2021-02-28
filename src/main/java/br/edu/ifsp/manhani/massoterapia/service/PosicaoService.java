package br.edu.ifsp.manhani.massoterapia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.alperkurtul.firebaserealtimedatabase.exception.HttpNotFoundException;
import com.github.alperkurtul.firebaseuserauthentication.bean.FirebaseSignInSignUpResponseBean;
import com.github.alperkurtul.firebaseuserauthentication.service.UserAuthenticationServiceImpl;

import br.edu.ifsp.manhani.massoterapia.dto.FilaDTO;
import br.edu.ifsp.manhani.massoterapia.dto.PosicaoDTO;
import br.edu.ifsp.manhani.massoterapia.mapper.PosicaoMapper;
import br.edu.ifsp.manhani.massoterapia.model.Funcionario;
import br.edu.ifsp.manhani.massoterapia.model.Posicao;
import br.edu.ifsp.manhani.massoterapia.model.firebase.Fila;
import br.edu.ifsp.manhani.massoterapia.repository.FilaRepository;
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

	@Autowired
	private FilaRepository filaRepository;

	@Autowired
	private UserAuthenticationServiceImpl userAuthenticationServiceImpl;

	@Value("${firebase-username}")
	private String firebaseUsername;

	@Value("${firebase-password}")
	private String firebasePassword;

	public List<PosicaoDTO> getAllByIdUnidade(UUID idUnidade) {
		return mapper.toDto(repository.findAllByUnidadeIdOrderByNumero(idUnidade));
	}

	@Transactional
	public void save(List<PosicaoDTO> lista) {
		UUID idUnidade = lista.get(0).getUnidade().getId();
		repository.deleteByUnidadeId(idUnidade);
		List<Posicao> posicoes = mapper.toEntity(lista);
		FirebaseSignInSignUpResponseBean res = userAuthenticationServiceImpl
				.signInWithEmailAndPassword(firebaseUsername, firebasePassword);

		Fila fila = recuperarFila(idUnidade, res.getIdToken());

		for (Posicao posicao : posicoes) {
			if (posicao.getFuncionario() != null && posicao.getFuncionario().getId() == null) {
				Funcionario f = funcionarioRepository.findByLogin(posicao.getFuncionario().getLogin());
				if (f == null) {
					f = Funcionario.builder().login(posicao.getFuncionario().getLogin())
							.nome(posicao.getFuncionario().getNome()).build();
				}

				posicao.setFuncionario(f);

				inserirFilaFirebase(fila, posicao);
			} else {
				removerPosicaoFirebase(fila, posicao);
			}
		}

		repository.saveAll(posicoes);
	}

	private void removerPosicaoFirebase(Fila fila, Posicao posicao) {
		List<FilaDTO> pos = fila.getPosicoes().stream().filter(p -> p.getPosicao().equals(posicao.getNumero()))
				.collect(Collectors.toList());

		fila.getPosicoes().removeAll(pos);

		try {
			filaRepository.update(fila);
		} catch (HttpNotFoundException ex) {
			filaRepository.saveWithSpecificId(fila);
		}

	}

	private Fila recuperarFila(UUID idUnidade, String key) {
		Fila f = new Fila(key, idUnidade.toString(), null);

		try {
			f = filaRepository.read(f);
			f.setAuthKey(key);
			f.setId(idUnidade.toString());
			if (f.getPosicoes() == null) {
				f.setPosicoes(new ArrayList<>());
			}
		} catch (HttpNotFoundException ex) {
			f.setPosicoes(new ArrayList<>());
		}
		return f;
	}

	private void inserirFilaFirebase(Fila fila, Posicao posicao) {
		boolean found = false;

		for (FilaDTO p : fila.getPosicoes()) {
			if (p.getLogin().equals(posicao.getFuncionario().getLogin())) {
				p.setPosicao(posicao.getNumero());
				found = true;
				break;
			}
		}

		if (!found) {
			FilaDTO pos = new FilaDTO(posicao.getFuncionario().getNome(), posicao.getFuncionario().getLogin(),
					posicao.getNumero());
			fila.getPosicoes().add(pos);
		}

		try {
			filaRepository.update(fila);
		} catch (HttpNotFoundException ex) {
			filaRepository.saveWithSpecificId(fila);
		}
	}

}
