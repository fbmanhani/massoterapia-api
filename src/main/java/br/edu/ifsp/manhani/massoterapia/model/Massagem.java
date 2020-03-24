package br.edu.ifsp.manhani.massoterapia.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Massagem implements Serializable {
	private static final long serialVersionUID = -1650381883234794185L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;

	@ManyToOne(optional = false)
	private Funcionario funcionario;

	@ManyToOne(optional = false)
	private Massoterapeuta massoterapeuta;

	@Column(nullable = false)
	private LocalDateTime dataHora = LocalDateTime.now();

}
