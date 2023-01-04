package com.dougdeveloper.peoplemanagement.dominio.exception;

public class ObjetoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjetoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	public ObjetoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
