package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco;

import java.util.Optional;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscaPessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosEditarEndereco;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.logger.AppLogger;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;

/**
 * A classe <b>EditaEnderecoDaPessoa</b> define um usecase para editar um
 * endereço de uma <b>Pessoa</b>, passando como parâmetros ao método editar o id
 * da pessoa e um objeto do tipo <b>DadosEditarEndereco</b>
 * 
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class EditaEnderecoDaPessoa {

	private final AppLogger logger;

	private final PessoaRepository repository;

	public EditaEnderecoDaPessoa(PessoaRepository repository, AppLogger logger) {
		this.repository = repository;
		this.logger = logger;
	}

	@Transactional
	public DadosDePessoa editar(Long id, DadosEditarEndereco dados) {
		BuscaPessoaPorId buscarPessoaPorId = new BuscaPessoaPorId(repository);
		Pessoa pessoa = buscarPessoaPorId.buscar(id);
		Optional<Endereco> enderecoAEditar = pessoa.getEnderecos().stream()
				.filter(endereco -> endereco.getId() == dados.id()).findFirst();
		if (enderecoAEditar.isEmpty()) {
			throw new ObjetoNaoEncontradoException("Endereco com id " + dados.id() + " não encontrado!");
		}
		if (dados.principal()) {
			pessoa.getEnderecos().forEach(endereco -> endereco.setPrincipal(false));
		}
		enderecoAEditar.get().setLogradouro(dados.logradouro());
		enderecoAEditar.get().setCep(new Cep(dados.cep()));
		enderecoAEditar.get().setNumero(dados.numero());
		enderecoAEditar.get().setCidade(dados.cidade());
		enderecoAEditar.get().setPrincipal(dados.principal());
		Pessoa pessoaEditada = repository.editar(pessoa);
		logger.info("Editado endereço da pessoa de nome " + pessoa.getNome()
				+ " e id " + pessoa.getId() + " para " + enderecoAEditar.get(), EditaEnderecoDaPessoa.class);
		return new DadosDePessoa(pessoaEditada);
	}
}
