package br.edu.ifsp.manhani.massoterapia.model;

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
@Table(name = "tb_posicao")
@SequenceGenerator(name = "posicaoGenerator", sequenceName = "sq_posicao")
public class Posicao extends BaseEntity<Long> {

	private static final long serialVersionUID = 4670328178020901856L;

	@Id
	@Column(name = "id_posicao")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posicaoGenerator")
	private Long id;

	@Column(name = "ds_login_posicao")
	private String loginUsuario;

	@Column(name = "nu_posicao")
	private Integer numero;

	@ManyToOne
	@JoinColumn(name = "id_unidade", nullable = false)
	private Unidade unidade;

}
