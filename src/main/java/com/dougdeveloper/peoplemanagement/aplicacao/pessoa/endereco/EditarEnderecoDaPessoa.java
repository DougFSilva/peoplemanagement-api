package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco;

import java.util.Optional;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarPessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosEditarEndereco;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;

public class EditarEnderecoDaPessoa {

	private final PessoaRepository repository;

	public EditarEnderecoDaPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public DadosDePessoa executar(Long id, DadosEditarEndereco dados) {
		BuscarPessoaPorId buscarPessoaPorId = new BuscarPessoaPorId(repository);
		Pessoa pessoa = buscarPessoaPorId.executar(id);
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
