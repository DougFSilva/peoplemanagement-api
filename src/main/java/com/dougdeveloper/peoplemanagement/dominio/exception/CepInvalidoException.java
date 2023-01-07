package com.dougdeveloper.peoplemanagement.dominio.exception;

/**
 * A classe <b>CepInvalidoException</b> define uma exception para ser utilizada quando um CEP for inválido
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class CepInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CepInvalidoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	public CepInvalidoException(String mensagem) {
		super(mensagem);
	}
}
