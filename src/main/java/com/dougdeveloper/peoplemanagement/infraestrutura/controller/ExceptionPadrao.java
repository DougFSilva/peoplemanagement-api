package com.dougdeveloper.peoplemanagement.infraestrutura.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExceptionPadrao {

	private long timestamp;
	
	private Integer status;
	
	private String erro;

}
