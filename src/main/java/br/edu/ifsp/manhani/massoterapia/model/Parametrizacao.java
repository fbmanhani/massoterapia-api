package br.edu.ifsp.manhani.massoterapia.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_parametrizacao")
@GenericGenerator(name = "generatorParametrizacao", strategy = "org.hibernate.id.UUIDGenerator")
public class Parametrizacao extends BaseEntity<UUID> {

    private static final long serialVersionUID = -1853066349639288105L;

    @Id
    @Column(name = "id_parametrizacao")
    @GeneratedValue(generator = "generatorParametrizacao")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_unidade", nullable = false)
    private Unidade unidade;

    @Column(name = "nu_posicoes", nullable = false)
    private Integer posicoes;

}
