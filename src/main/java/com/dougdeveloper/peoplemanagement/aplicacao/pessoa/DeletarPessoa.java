package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class DeletarPessoa {

	private final PessoaRepository repository;
	
	public DeletarPessoa(PessoaRepository repository) {
		this.repository = repository;
	}
	
	public void executar(Long id) {
		BuscarPessoaPorId buscarPessoaPorId = new BuscarPessoaPorId(repository);
		Pessoa pessoa = buscarPessoaPorId.executar(id);
		repository.deletar(pessoa);
	}
}
