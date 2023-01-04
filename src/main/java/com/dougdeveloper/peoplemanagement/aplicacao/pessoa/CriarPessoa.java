package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import java.util.List;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class CriarPessoa {

	private final PessoaRepository repository;

	public CriarPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	public Pessoa executar(DadosCriarPessoa dados) {
		List<Endereco> enderecos = dados.enderecos().stream().map(endereco -> new Endereco(endereco.logradouro(),
				endereco.cep(), endereco.numero(), endereco.cidade(), endereco.principal())).toList();
		ValidarEnderecos.validar(enderecos);
		Pessoa pessoa = new Pessoa(null, dados.nome(), dados.dataNascimento(), enderecos);
		return repository.criar(pessoa);
	}

}
