package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class BuscarDadosDeTodasPessoas {

	private final PessoaRepository repository;

	public BuscarDadosDeTodasPessoas(PessoaRepository repository) {
		this.repository = repository;
	}

	public Page<DadosDePessoa> executar(Pageable paginacao) {
		return repository.listar(paginacao).map(pessoa -> new DadosDePessoa(pessoa));
	}
}
