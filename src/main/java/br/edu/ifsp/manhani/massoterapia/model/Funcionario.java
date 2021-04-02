package br.edu.ifsp.manhani.massoterapia.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_funcionario")
@GenericGenerator(name = "generatorFuncionario", strategy = "org.hibernate.id.UUIDGenerator")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends BaseEntity<UUID> {

	private static final long serialVersionUID = -972500207450172743L;

	@Id
	@Column(name = "id_funcionario", nullable = false)
	@GeneratedValue(generator = "generatorFuncionario")
	private UUID id;

	@Column(name = "co_funcionario", length = 50, nullable = false)
	private String login;

	@Column(name = "no_funcionario", length = 150, nullable = false)
	private String nome;

	@Column(name = "dt_nascimento")
	private LocalDate dataNascimento;

	@Column(name = "foto")
	private String foto;

	@Column(name = "st_ativo")
	@Builder.Default
	private Boolean ativo = true;

	@Enumerated(EnumType.STRING)
	@Column(name = "tp_funcionario")
	private TipoFuncionarioEnum tipo;

}
