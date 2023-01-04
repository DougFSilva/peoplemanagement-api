package com.dougdeveloper.peoplemanagement.infraestrutura.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dougdeveloper.peoplemanagement.dominio.exception.CepInvalidoException;
import com.dougdeveloper.peoplemanagement.dominio.exception.ContagemDeEnderecosPrincipaisException;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;


@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(CepInvalidoException.class)
	public ResponseEntity<ExceptionPadrao> cepInvalidoException(CepInvalidoException exception) {
		ExceptionPadrao exceptionPadrao = new ExceptionPadrao(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionPadrao);
	}
	
	@ExceptionHandler(ContagemDeEnderecosPrincipaisException.class)
	public ResponseEntity<ExceptionPadrao> contagemDeEnderecosPrincipaisException(ContagemDeEnderecosPrincipaisException exception) {
		ExceptionPadrao exceptionPadrao = new ExceptionPadrao(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionPadrao);
	}
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<ExceptionPadrao> objetoNaoEncontradoException(ObjetoNaoEncontradoException exception) {
		ExceptionPadrao exceptionPadrao = new ExceptionPadrao(System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionPadrao);
	}

}
