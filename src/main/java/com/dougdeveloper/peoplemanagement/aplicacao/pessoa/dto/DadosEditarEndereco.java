package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * O Record <b>DadosEditarEndereco</b> é um Data Transfer Object utilizado para edição de um objeto do tipo <b>Endereço</b>
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
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
