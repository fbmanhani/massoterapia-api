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
@Table(name = "tb_massoterapeuta")
public class Massoterapeuta extends BaseEntity<String> {

    private static final long serialVersionUID = -6509305707391691043L;

    @Id
    @Column(name = "id_massoterapeuta", length = 20)
    private String id;

    @Column(name = "ds_nome", length = 150)
    private String nome;

    @Column(name = "st_ativo")
    private Boolean ativo = true;

}
