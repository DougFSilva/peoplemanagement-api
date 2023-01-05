package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosEditarPessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class EditarPessoa {

	private final PessoaRepository repository;

	public EditarPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	public Pessoa executar(Long id, DadosEditarPessoa dados) {
		BuscarPessoaPorId buscarPessoaPorId = new BuscarPessoaPorId(repository);
		Pessoa pessoa = buscarPessoaPorId.executar(id);
		pessoa.setNome(dados.nome());
		pessoa.setDataNascimento(dados.dataNascimento());
		return repository.editar(pessoa);
	}
}
