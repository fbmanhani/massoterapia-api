package br.edu.ifsp.manhani.massoterapia.model;

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
@Table(name = "tb_posicao")
@GenericGenerator(name = "generatorPosicao", strategy = "org.hibernate.id.UUIDGenerator")
public class Posicao extends BaseEntity<UUID> {

	private static final long serialVersionUID = 4670328178020901856L;

	@Id
	@Column(name = "id_posicao")
	@GeneratedValue(generator = "generatorPosicao")
	private UUID id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	@Column(name = "nu_posicao")
	private Integer numero;

	@ManyToOne
	@JoinColumn(name = "id_unidade", nullable = false)
	private Unidade unidade;

}
