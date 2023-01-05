package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEditarEndereco(
		
		@NotNull
		Long id, 
		
		@NotBlank
		String logradouro, 
		
		@NotBlank
		String cep, 
		
		@NotBlank
		String numero, 
		
		@NotBlank
		String cidade,
		
		@NotNull
		boolean principal) {

}
