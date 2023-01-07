package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;

import lombok.Getter;
import lombok.ToString;
/**
 * A classe <b>DadosDeEndereco</b> é um Data Transfer Object utlizado para representar os atributos de um objeto do tipo <b>Endereco</b>
 * de forma customizada
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
@Getter
@ToString
public class DadosDeEndereco {

	private Long id;

	private String logradouro;

	private String cep;

	private String numero;

	private String cidade;

	private boolean principal;
	
	public DadosDeEndereco(Endereco endereco) {
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.cep = endereco.getCep().getDigitos();
		this.numero = endereco.getNumero();
		this.cidade = endereco.getCidade();
		this.principal = endereco.isPrincipal();
	}

}
