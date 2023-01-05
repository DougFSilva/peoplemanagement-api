package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarPessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class CriarPessoa {

	private final PessoaRepository repository;

	public CriarPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	public Pessoa executar(DadosCriarPessoa dados) {
		Pessoa pessoa = new Pessoa(dados.nome(), dados.dataNascimento());
		return repository.criar(pessoa);
	}

}
