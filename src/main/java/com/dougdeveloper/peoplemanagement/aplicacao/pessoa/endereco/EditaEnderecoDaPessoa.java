package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco;

import java.util.Optional;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscaPessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosEditarEndereco;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;
/**
 * A classe <b>EditaEnderecoDaPessoa</b> define um usecase para editar um endereço de uma <b>Pessoa</b>, passando
 * como parâmetros ao método editar o id da pessoa e um objeto do tipo <b>DadosEditarEndereco</b>
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class EditaEnderecoDaPessoa {

	private final PessoaRepository repository;

	public EditaEnderecoDaPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public DadosDePessoa editar(Long id, DadosEditarEndereco dados) {
		BuscaPessoaPorId buscarPessoaPorId = new BuscaPessoaPorId(repository);
		Pessoa pessoa = buscarPessoaPorId.buscar(id);
		Optional<Endereco> enderecoAEditar = pessoa.getEnderecos()
				.stream()
				.filter(endereco -> endereco.getId() == dados.id())
				.findFirst();
		if(enderecoAEditar.isEmpty()) {
			throw new ObjetoNaoEncontradoException("Endereco com id " + dados.id() + " não encontrado!");
		}
		if(dados.principal()) {
			pessoa.getEnderecos().forEach(endereco -> endereco.setPrincipal(false));
		}
		enderecoAEditar.get().setLogradouro(dados.logradouro());
		enderecoAEditar.get().setCep(new Cep(dados.cep()));
		enderecoAEditar.get().setNumero(dados.numero());
		enderecoAEditar.get().setCidade(dados.cidade());
		enderecoAEditar.get().setPrincipal(dados.principal());
		return new DadosDePessoa(repository.editar(pessoa));
	}
}
