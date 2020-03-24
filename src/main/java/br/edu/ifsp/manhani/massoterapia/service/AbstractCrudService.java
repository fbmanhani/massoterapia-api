package br.edu.ifsp.manhani.massoterapia.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifsp.manhani.massoterapia.dto.ApiResponse;
import br.edu.ifsp.manhani.massoterapia.dto.BaseDTO;
import br.edu.ifsp.manhani.massoterapia.mapper.BaseMapper;
import br.edu.ifsp.manhani.massoterapia.messages.IMessageProperty;
import br.edu.ifsp.manhani.massoterapia.messages.MessageProperties;
import br.edu.ifsp.manhani.massoterapia.model.BaseEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link AbstractCrudService} - Classe responsável por expor métodos padrões de escrita, os métodos de consulta são herdados da classe
 * {@link AbstractService AbstractService}
 *
 * @param <E> - {@code <E extends BaseEntity> }, Entidade que será manipulada(somente leitura).
 * @param <I> - {@code <I extends Serializable> }, Tipo do identificador da entidade.
 * @param <D> - {@code <D extends BaseDTO> }, DTO utilizado como parâmetro de entrada e saída dos métodos.
 * @param <M> - {@code <M extends Maapper> }, Mapper utilizado para realizar conversão entre DTO e Entidade.
 * @param <R> - {@code <R extends JpaRepository> }, Repository utilizado para realizar as consultas da entidade.
 */
@Slf4j
@Transactional
public abstract class AbstractCrudService<E extends BaseEntity<I>, I extends Serializable, D extends BaseDTO, M extends BaseMapper<E, D>, R extends JpaRepository<E, I>>
        extends AbstractService<E, I, D, M, R> {

    /**
     * Construtor
     *
     * @param mapper
     * @param repository
     * @param entityClazz
     */
    AbstractCrudService(final M mapper, final R repository, final Class<E> entityClazz) {
        super(mapper, repository, entityClazz);
    }

    /**
     * Método responsável por salvar a entidade.
     *
     * @param dto Informações à serem convertidas e salvas.
     * @return {@code D}
     */
    public D save(final D dto) {
        E entity = getMapper().toEntity(dto);
        return getMapper().toDto(getRepository().save(entity));
    }

    /**
     * Método responsável por salvar a uma lista entidades.
     *
     * @param dtos Lista de informações à serem convertidas e salvas.
     * @return {@code D}
     */
    public List<D> save(final List<D> dtos) {
        Iterable<E> entities = getMapper().toEntity(ObjectUtils.defaultIfNull(dtos, new ArrayList<>()));
        return getMapper().toDto(getRepository().saveAll(entities));
    }

    /**
     * Método responsável por salvar a entidade.
     *
     * @param dto            Informações à serem convertidas e salvas.
     * @param messageSuccess Mensagem de sucesso que será retornada.
     * @return {@code D}
     */
    public ApiResponse save(final D dto, final IMessageProperty messageSuccess) {
        return new ApiResponse(messageSuccess.message(), this.save(dto));
    }

    /**
     * Método responsável por salvar a uma lista entidades.
     *
     * @param dtos           Lista de informações à serem convertidas e salvas.
     * @param messageSuccess Mensagem de sucesso que será retornada.
     * @return {@code D}
     */
    public ApiResponse save(final List<D> dtos, final IMessageProperty messageSuccess) {
        return new ApiResponse(messageSuccess.message(), this.save(dtos));
    }

    /**
     * Método responsável por remover um registro.
     *
     * @param id Identificador utilizado para verificar se o registro existe antes de ser excluido.
     * @throws {@code ResourceNotFoundException} Caso o identificador não retorne um registro para ser removido.
     */
    public void delete(final I id) {
        super.findById(id, ResourceNotFoundException.class, MessageProperties.MSGSI001);
        getRepository().deleteById(id);
    }

    /**
     * Método responsável por remover um registro.
     *
     * @param id             Identificador utilizado para verificar se o registro existe antes de ser excluido.
     * @param messageSuccess Mensagem de sucesso que será retornada.
     * @throws {@code ResourceNotFoundException} Caso o identificador não retorne um registro para ser removido.
     */
    public ApiResponse delete(final I id, final IMessageProperty messageSuccess) {
        this.delete(id);
        return new ApiResponse(messageSuccess.message(), Void.class);
    }
}