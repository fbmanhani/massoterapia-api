package br.edu.ifsp.manhani.massoterapia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_unidade")
@SequenceGenerator(name = "unidadeGenerator", sequenceName = "SQ_UNIDADE")
public class Unidade extends BaseEntity<Long> {

    private static final long serialVersionUID = -6794195905797880053L;

    @Id
    @Column(name = "id_unidade")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidadeGenerator")
    private Long id;

    @Column(name = "ds_unidade")
    private String descricao;

}
