package br.edu.ifsp.manhani.massoterapia.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tb_sessao")
@SequenceGenerator(name = "sessaoGenerator", sequenceName = "sq_sessao")
public class Sessao extends BaseEntity<Long> {

    private static final long serialVersionUID = -1650381883234794185L;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessaoGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_massoterapeuta")
    private Massoterapeuta massoterapeuta;

    @Column(name = "dt_operacao", nullable = false)
    private LocalDateTime dataHora = LocalDateTime.now();

}
