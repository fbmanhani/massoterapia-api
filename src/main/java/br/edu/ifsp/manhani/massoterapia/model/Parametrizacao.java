package br.edu.ifsp.manhani.massoterapia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_parametrizacao")
@SequenceGenerator(name = "parametrizacaoGenerator", sequenceName = "sq_parametrizacao")
public class Parametrizacao extends BaseEntity<Long> {

    private static final long serialVersionUID = -1853066349639288105L;

    @Id
    @Column(name = "id_parametrizacao")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_unidade", nullable = false)
    private Unidade unidade;

    @Column(name = "nu_posicoes", nullable = false)
    private Integer quantidadePosicoes;

}
