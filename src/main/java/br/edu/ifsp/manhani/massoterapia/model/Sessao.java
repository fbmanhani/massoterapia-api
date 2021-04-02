package br.edu.ifsp.manhani.massoterapia.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
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
@Table(name = "tb_sessao")
@GenericGenerator(name = "generatorSessao", strategy = "org.hibernate.id.UUIDGenerator")
public class Sessao extends BaseEntity<UUID> {

	private static final long serialVersionUID = -1650381883234794185L;

	@Id
	@Column(name = "id_sessao")
	@GeneratedValue(generator = "generatorSessao")
	private UUID id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "id_massoterapeuta")
	private Funcionario massoterapeuta;

	@ManyToOne
	@JoinColumn(name = "id_unidade", nullable = false)
	private Unidade unidade;

	@Column(name = "dt_sessao", nullable = false)
	private LocalDateTime dataHora = LocalDateTime.now();

	public void setDataHora(LocalDateTime data) {
		if (data != null) {
			this.dataHora = data;
		}
	}

}
