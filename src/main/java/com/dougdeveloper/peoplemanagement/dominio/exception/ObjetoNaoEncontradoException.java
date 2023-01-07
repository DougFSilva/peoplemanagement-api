package com.dougdeveloper.peoplemanagement.dominio.exception;

/**
 * A classe <b>ObjetoNaoEncontradoException</b> define uma exception para ser utilizada quando um objeto não for encontrado
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class ObjetoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjetoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	public ObjetoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
