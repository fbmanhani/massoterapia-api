package br.edu.ifsp.manhani.massoterapia.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.edu.ifsp.manhani.massoterapia.dto.ApiResponse;
import br.edu.ifsp.manhani.massoterapia.dto.BaseDTO;
import br.edu.ifsp.manhani.massoterapia.exception.InfraException;
import br.edu.ifsp.manhani.massoterapia.mapper.BaseMapper;
import br.edu.ifsp.manhani.massoterapia.messages.IMessageProperty;
import br.edu.ifsp.manhani.massoterapia.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link AbstractService} - Classe responsável por expor métodos padrões de consulta.
 *
 * @param <E> - {@code <E extends BaseEntity> }, Entidade que será manipulada(somente leitura).
 * @param <I> - {@code <I extends Serializable> }, Tipo do identificador da entidade.
 * @param <D> - {@code <D extends BaseDTO> }, DTO utilizado como parâmetro de entrada e saída dos métodos.
 * @param <M> - {@code <M extends Mapper> }, Mapper utilizado para realizar conversão entre DTO e Entidade.
 * @param <R> - {@code <R extends JpaRepository> }, Repository utilizado para realizar as consultas da entidade.
 * 
 */
@Slf4j
@Getter(value = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Transactional
public abstract class AbstractService<E extends BaseEntity<I>, I extends Serializable, D extends BaseDTO, M extends BaseMapper<E, D>, R extends JpaRepository<E, I>> {

    private final M mapper;
    private final R repository;
    private final Class<E> entityClazz;

    /**
     * Método responsável por recuperar, todos os registros da entidade.
     *
     * @return {@code List<D>}
     */
    @Transactional(readOnly = true)
    public List<D> findAll() {
        return mapper.toDto(repository.findAll());
    }

    /**
     * Método responsável por recuperar todos os registros de forma ordenada.
     *
     * @param sort Ordenação da consulta pelo atrituto da entidade e direção da ordenação definido pelo
     *             {@link org.springframework.data.domain.Sort.Direction}.
     * @return{@code List<D>}
     */
    @Transactional(readOnly = true)
    public List<D> findAll(final Sort sort) {
        return mapper.toDto(repository.findAll(sort));
    }

    /**
     * Método responsável por recuperar todos os registros de forma ordenada.
     *
     * @param sort            Ordenação da consulta pelo atrituto da entidade e direção da ordenação definido pelo
     *                        {@link org.springframework.data.domain.Sort.Direction}.
     * @param messageResult   Mensagem que será exibida caso encontre registro.
     * @param messageNoResult Mensagem que será exibida caso não encontre registro.
     * @return {@code ApiResponse}
     */
    @Transactional(readOnly = true)
    public ApiResponse findAll(final Sort sort, final String messageResult, final String messageNoResult) {
        List<D> dtos = this.findAll(sort);
        return new ApiResponse(CollectionUtils.isEmpty(dtos) ? messageNoResult : messageResult, dtos);
    }

    /**
     * Método responsável por recuperar, todos os registros da entidade.
     *
     * @param messageResult   Mensagem que será exibida caso encontre registro.
     * @param messageNoResult Mensagem que será exibida caso não encontre registro.
     * @return {@code ApiResponse}
     */
    @Transactional(readOnly = true)
    public ApiResponse findAll(final String messageResult, String messageNoResult) {
        List<D> dtos = this.findAll();
        return new ApiResponse(CollectionUtils.isEmpty(dtos) ? messageNoResult : messageResult, dtos);
    }

    /**
     * Método responsável por buscar a entidade pelo seu identificador.
     *
     * @param id Identificador que será utilizado.
     * @return {@code D}.
     */
    @Transactional(readOnly = true)
    public D findById(final I id) {
        D dto = null;
        Optional<E> opt = repository.findById(id);
        if (opt.isPresent()) {
            dto = mapper.toDto(opt.get());
        }
        return dto;
    }

    /**
     * Método responsável por buscar a entidade pelo seu identificador.
     *
     * @param id    Identificador da entidade.
     * @param clazz Classe de exception que pode ser propagada.
     * @param <T>   Tipo da exception que extende {@link RuntimeException}.
     * @return D {@code D}
     * @throws T Caso a busca não encontre uma entidade, é propagada a exception fornecida como parâmetro.
     */
    @Transactional(readOnly = true)
    public <T extends RuntimeException> D findById(final I id, final Class<T> clazz) {
        final D dto = this.findById(id);
        if (dto == null) {
            throw getThrowableInstance(clazz, null);
        }
        return dto;
    }

    /**
     * Método responsável por buscar a entidade pelo seu identificador.
     *
     * @param id            Identificador da entidade.
     * @param messageResult Mensagem que será exibida caso encontre registro.
     * @return {@code ApiResponse}
     */
    @Transactional(readOnly = true)
    public ApiResponse findById(final I id, final IMessageProperty messageResult) {
        return new ApiResponse(messageResult.message(), findById(id, ResourceNotFoundException.class));
    }

    /**
     * Método responsável por buscar a entidade pelo seu identificador.
     *
     * @param id              Identificador da entidade.
     * @param clazz           Classe de exception que pode ser propagada.
     * @param messageProperty Mensagem de erro.
     * @param <T>             Tipo da exception que extende {@link RuntimeException}.
     * @return {@code D}
     * @throws T T Caso a busca não encontre uma entidade, é propagada a exception fornecida como parâmetro.
     */
    @Transactional(readOnly = true)
    public <T extends RuntimeException> D findById(final I id, final Class<T> clazz, final IMessageProperty messageProperty) {
        final D dto = this.findById(id);
        if (dto == null) {
            throw getThrowableInstance(clazz, messageProperty);
        }
        return dto;
    }

    private <T extends RuntimeException> T getThrowableInstance(final Class<T> clazz, IMessageProperty messageProperty) {
        @SuppressWarnings("unchecked")
        T object = ((T) new InfraException());
        try {
            if (messageProperty == null) {
                object = clazz.getDeclaredConstructor().newInstance();
            }
            object = clazz.getConstructor(IMessageProperty.class).newInstance(messageProperty);
        } catch (ReflectiveOperationException e) {
            log.error(e.getMessage());
        }
        return object;
    }

}
