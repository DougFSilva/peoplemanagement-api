package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class EditarPessoa {

	private final PessoaRepository repository;

	public EditarPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	public Pessoa executar(Long id, Pessoa pessoaEditada, BuscarPessoaPorId buscarPessoaPorId) {
		Pessoa pessoa = buscarPessoaPorId.executar(id);
		pessoa.setNome(pessoaEditada.getNome());
		pessoa.setDataNascimento(pessoaEditada.getDataNascimento());
		return repository.editar(pessoa);
	}
}
