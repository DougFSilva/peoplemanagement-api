package com.dougdeveloper.peoplemanagement.infraestrutura.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A classe <b>ExceptionPadrao</b> define um modelo customizado para representar uma Exception lançada pela API.
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExceptionPadrao {

	private long timestamp;
	
	private Integer status;
	
	private String erro;

}
