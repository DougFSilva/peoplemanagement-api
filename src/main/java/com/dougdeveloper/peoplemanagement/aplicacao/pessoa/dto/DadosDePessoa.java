package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;

import lombok.Getter;
import lombok.ToString;
/**
 * A classe <b>DadosDePessoa</b> é um Data Transfer Object utlizado para representar os atributos de um objeto do tipo <b>Pessoa</b>
 * de forma customizada
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
@Getter
@ToString
public class DadosDePessoa {

	private Long id;
	
	private String nome;
	
	private String dataNascimento;
	
	private List<DadosDeEndereco> enderecos;
	
	public DadosDePessoa(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.dataNascimento = pessoa.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.enderecos = pessoa.getEnderecos().stream().map(endereco -> new DadosDeEndereco(endereco)).toList();
	}
}
