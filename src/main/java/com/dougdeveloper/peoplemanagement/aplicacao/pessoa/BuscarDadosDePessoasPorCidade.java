package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class BuscarDadosDePessoasPorCidade {

	private final PessoaRepository repository;

	public BuscarDadosDePessoasPorCidade(PessoaRepository repository) {
		this.repository = repository;
	}

	public Page<DadosDePessoa> executar(String cidade, Pageable paginacao) {
		return repository.buscarPorCidade(cidade, paginacao).map(pessoa -> new DadosDePessoa(pessoa));
	}

}
