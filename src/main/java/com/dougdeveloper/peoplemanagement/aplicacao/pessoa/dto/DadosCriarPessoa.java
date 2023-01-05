package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriarPessoa(
		
		@NotBlank
		String nome, 
		
		@NotNull
		LocalDate dataNascimento, 
		
		@NotNull
		List<DadosCriarEndereco> enderecos) {

}
