package com.dougdeveloper.peoplemanagement.infraestrutura.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dougdeveloper.peoplemanagement.dominio.exception.CepInvalidoException;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;

/**
 * A classe <b>ExceptionHandlerController</b> é responsável por capturar os erros lançados pela API e responder as requisições de forma
 * customizada utilizando a classe <b>ExceptionPadrao</b>
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(CepInvalidoException.class)
	public ResponseEntity<ExceptionPadrao> cepInvalidoException(CepInvalidoException exception) {
		ExceptionPadrao exceptionPadrao = new ExceptionPadrao(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionPadrao);
	}

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<ExceptionPadrao> objetoNaoEncontradoException(ObjetoNaoEncontradoException exception) {
		ExceptionPadrao exceptionPadrao = new ExceptionPadrao(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionPadrao);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionPadrao> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<FieldError> erros = exception.getFieldErrors();
		HashMap<String, String> errosMap = new HashMap<>();
		erros.forEach(erro -> {
			errosMap.put(erro.getField(), erro.getDefaultMessage());
		});
		ExceptionPadrao exceptionPadrao = new ExceptionPadrao(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Erro na validação dos campos: " + errosMap.toString());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionPadrao);
	}

}
