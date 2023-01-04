package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;

import lombok.Getter;
import lombok.ToString;

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
