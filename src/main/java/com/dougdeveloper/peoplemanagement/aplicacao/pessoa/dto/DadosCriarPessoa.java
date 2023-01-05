package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriarPessoa(

		@NotBlank String nome,

		@NotNull LocalDate dataNascimento) {

}
