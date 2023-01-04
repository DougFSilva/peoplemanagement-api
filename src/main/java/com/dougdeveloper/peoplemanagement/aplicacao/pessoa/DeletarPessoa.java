package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class DeletarPessoa {

	private final PessoaRepository repository;
	
	private final BuscarPessoaPorId buscarPessoaPorId;
	
	public DeletarPessoa(PessoaRepository repository, BuscarPessoaPorId buscarPessoaPorId) {
		this.repository = repository;
		this.buscarPessoaPorId = buscarPessoaPorId;
	}
	
	public void executar(Long id) {
		Pessoa pessoa = buscarPessoaPorId.executar(id);
		repository.deletar(pessoa);
	}
}
