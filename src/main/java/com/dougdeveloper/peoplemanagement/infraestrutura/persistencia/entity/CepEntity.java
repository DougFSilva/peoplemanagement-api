package com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * A classe <b>CepEntity</b> representa uma entidade referente a classe <b>Cep</b>
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class CepEntity {

	@Column(name = "CEP")
	private String digitos;
}
