package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import java.util.List;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class BuscarDadosDePessoasPorNome {

	private final PessoaRepository repository;

	public BuscarDadosDePessoasPorNome(PessoaRepository repository) {
		this.repository = repository;
	}

	public List<DadosDePessoa> executar(String nome) {
		return repository.buscarPorNome(nome).stream().map(pessoa -> new DadosDePessoa(pessoa)).toList();
	}
}
