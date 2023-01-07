package com.dougdeveloper.peoplemanagement.dominio.pessoa;

import com.dougdeveloper.peoplemanagement.dominio.exception.CepInvalidoException;

/**
 * A classe <b>Cep</b> define um Value Object referente a um código de endereçamento postal (CEP), com validação na construção do objeto
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class Cep {

	private String digitos;
	
	public Cep(String digitos) {
		if (digitos == null || !digitos.matches("[0-9]{5}-[0-9]{3}")) {

			throw new CepInvalidoException("CEP " + digitos + " não inválido! O CEP deve ter a seguinte composição: xxxxx-xxx");
		}
		this.digitos = digitos;
	}
	
	public String getDigitos() {
		return digitos;
	}
	
}
