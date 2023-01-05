package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco;

import java.util.Optional;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarPessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;

public class RemoverEnderecoDaPessoa {

	private final PessoaRepository repository;

	public RemoverEnderecoDaPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public DadosDePessoa executar(Long id, Long enderecoId) {
		BuscarPessoaPorId buscarPessoaPorId = new BuscarPessoaPorId(repository);
		Pessoa pessoa = buscarPessoaPorId.executar(id);
		Optional<Endereco> enderecoARemover = pessoa.getEnderecos()
				.stream()
				.filter(endereco -> endereco.getId() == enderecoId)
				.findFirst();
		if(enderecoARemover.isEmpty()) {
			throw new ObjetoNaoEncontradoException("Endereço com id " + enderecoId + " não encontrado para a pessoa " + pessoa.getNome() + "!");
		}
		pessoa.getEnderecos().remove(enderecoARemover.get());
		return new DadosDePessoa(repository.editar(pessoa));
	}
}
