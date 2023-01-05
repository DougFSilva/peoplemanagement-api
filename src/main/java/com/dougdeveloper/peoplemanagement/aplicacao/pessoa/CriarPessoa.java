package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;

public class CriarPessoa {

	private final PessoaRepository repository;

	public CriarPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public DadosDePessoa executar(DadosCriarPessoa dados) {
		Pessoa pessoa = new Pessoa(dados.nome(), dados.dataNascimento());
		return new DadosDePessoa(repository.criar(pessoa));
	}

}
