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
@Table(name = "tb_massoterapeuta")
@GenericGenerator(name = "generatorMassoterapeuta", strategy = "org.hibernate.id.UUIDGenerator")
public class Massoterapeuta extends BaseEntity<UUID> {

    private static final long serialVersionUID = -6509305707391691043L;

    @Id
    @Column(name = "id_massoterapeuta")
    @GeneratedValue(generator = "generatorMassoterapeuta")
    private UUID id;

    @Column(name = "co_massoterapeuta", length = 50, nullable = false)
    private String login;

    @Column(name = "no_massoterapeuta", length = 150, nullable = false)
    private String nome;

    @Column(name = "st_ativo")
    private Boolean ativo = true;

}
