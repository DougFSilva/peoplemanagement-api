package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * O Record <b>DadosCriarPessoa</b> é um Data Transfer Object utilizado para criação de um objeto do tipo <b>Pessoa</b>
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public record DadosCriarOuEditarPessoa(

		@NotBlank String nome,

		@NotNull LocalDate dataNascimento) {

}
