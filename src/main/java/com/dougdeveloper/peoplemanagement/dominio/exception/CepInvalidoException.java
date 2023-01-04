package com.dougdeveloper.peoplemanagement.dominio.exception;

public class CepInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CepInvalidoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	public CepInvalidoException(String mensagem) {
		super(mensagem);
	}
}
