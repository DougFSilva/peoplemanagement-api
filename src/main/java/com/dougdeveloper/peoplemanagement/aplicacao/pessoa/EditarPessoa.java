package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class EditarPessoa {

	private final PessoaRepository repository;

	private final BuscarPessoaPorId buscarPessoaPorId;

	public EditarPessoa(PessoaRepository repository, BuscarPessoaPorId buscarPessoaPorId) {
		this.repository = repository;
		this.buscarPessoaPorId = buscarPessoaPorId;
	}

	public Pessoa executar(Long id, Pessoa pessoaEditada) {
		Pessoa pessoa = buscarPessoaPorId.executar(id);
		pessoa.setNome(pessoaEditada.getNome());
		pessoa.setDataNascimento(pessoaEditada.getDataNascimento());
		return repository.editar(pessoa);
	}
}
