package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import java.util.Optional;

import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class RemoverEnderecoDaPessoa {

	private final PessoaRepository repository;

	public RemoverEnderecoDaPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	public Pessoa executar(Long id, Long enderecoId, BuscarPessoaPorId buscarPessoaPorId) {
		Pessoa pessoa = buscarPessoaPorId.executar(id);
		Optional<Endereco> enderecoARemover = pessoa.getEnderecos()
				.stream()
				.filter(endereco -> endereco.getId() == enderecoId)
				.findFirst();
		if(enderecoARemover.isEmpty()) {
			throw new ObjetoNaoEncontradoException("Endereço com id " + enderecoId + " não encontrado!");
		}
		pessoa.getEnderecos().remove(enderecoARemover.get());
		return repository.editar(pessoa);
	}
}
