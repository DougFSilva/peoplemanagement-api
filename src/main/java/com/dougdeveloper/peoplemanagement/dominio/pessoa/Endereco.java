package com.dougdeveloper.peoplemanagement.dominio.pessoa;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * A classe <b>Endereco</b> define o modelo de um endereço de uma pessoa.
 * 
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Endereco {

	private Long id;

	private String logradouro;

	private Cep cep;

	private String numero;

	private String cidade;

	private boolean principal;

	public Endereco(String logradouro, String digitosCep, String numero, String cidade, boolean principal) {
		this.logradouro = logradouro;
		this.cep = new Cep(digitosCep);
		this.numero = numero;
		this.cidade = cidade;
		this.principal = principal;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", cep=" + cep.getDigitos() + ", numero=" + numero
				+ ", cidade=" + cidade + ", principal=" + principal + "]";
	}

}
