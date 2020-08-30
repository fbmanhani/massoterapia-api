package br.edu.ifsp.manhani.massoterapia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_funcionario")
public class Funcionario extends BaseEntity<String> {

    private static final long serialVersionUID = -972500207450172743L;

    @Id
    @Column(name = "id_funcionario")
    private String id;

    @Column(name = "no_funcionario")
    private String nome;

    @Column(name = "st_ativo")
    private Boolean ativo = true;

}
