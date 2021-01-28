package br.edu.ifsp.manhani.massoterapia.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_unidade")
@GenericGenerator(name = "generatorUnidade", strategy = "org.hibernate.id.UUIDGenerator")
public class Unidade extends BaseEntity<UUID> {

    private static final long serialVersionUID = -6794195905797880053L;

    @Id
    @Column(name = "id_sessao")
    @GeneratedValue(generator = "generatorSessao")
    private UUID id;

    @Column(name = "ds_unidade", length = 100, nullable = false)
    private String descricao;

}
