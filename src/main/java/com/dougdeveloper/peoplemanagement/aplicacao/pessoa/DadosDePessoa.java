package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;

import lombok.Getter;
import lombok.ToString;

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
