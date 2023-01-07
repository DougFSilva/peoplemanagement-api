package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco;

import java.util.Optional;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscaPessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.logger.AppLogger;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;

/**
 * A classe <b>RemoveEnderecoDaPessoa</b> define um usecase para remover um
 * endereço de uma <b>Pessoa</b>, passando como parâmetros ao método editar o id
 * da pessoa e o id do endereço.
 * 
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class RemoveEnderecoDaPessoa {

	private final AppLogger logger;

	private final PessoaRepository repository;

	public RemoveEnderecoDaPessoa(PessoaRepository repository, AppLogger logger) {
		this.repository = repository;
		this.logger = logger;
	}

	@Transactional
	public DadosDePessoa remover(Long id, Long enderecoId) {
		BuscaPessoaPorId buscarPessoaPorId = new BuscaPessoaPorId(repository);
		Pessoa pessoa = buscarPessoaPorId.buscar(id);
		Optional<Endereco> enderecoARemover = pessoa.getEnderecos().stream()
				.filter(endereco -> endereco.getId() == enderecoId).findFirst();
		if (enderecoARemover.isEmpty()) {
			throw new ObjetoNaoEncontradoException(
					"Endereço com id " + enderecoId + " não encontrado para a pessoa " + pessoa.getNome() + "!");
		}
		pessoa.removerEndereco(enderecoARemover.get());
		Pessoa pessoaEditada = repository.editar(pessoa);
		logger.info("Removido endereço " + enderecoARemover.get() + " da pessoa de nome " + pessoaEditada.getNome()
				+ " e id " + pessoaEditada.getId(), RemoveEnderecoDaPessoa.class);
		return new DadosDePessoa(pessoaEditada);
	}
}
