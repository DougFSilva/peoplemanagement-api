package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * O Record <b>DadosEditarEndereco</b> é um Data Transfer Object utilizado para edição de um objeto do tipo <b>Pessoa</b>
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public record DadosEditarPessoa(
		
		@NotBlank
		String nome, 
		
		@NotNull
		LocalDate dataNascimento) {

}
