package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEditarPessoa(
		
		@NotBlank
		String nome, 
		
		@NotNull
		LocalDate dataNascimento) {

}
