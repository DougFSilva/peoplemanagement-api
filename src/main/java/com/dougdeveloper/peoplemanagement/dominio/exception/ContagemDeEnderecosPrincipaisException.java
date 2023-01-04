package com.dougdeveloper.peoplemanagement.dominio.exception;

public class ContagemDeEnderecosPrincipaisException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContagemDeEnderecosPrincipaisException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	public ContagemDeEnderecosPrincipaisException(String mensagem) {
		super(mensagem);
	}
	
}