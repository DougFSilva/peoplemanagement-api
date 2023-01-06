package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarPessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarEndereco;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;

public class AdicionarEnderecoAPessoa {

	private final PessoaRepository repository;

	public AdicionarEnderecoAPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public DadosDePessoa executar(Long id, DadosCriarEndereco dadosEndereco) {
		BuscarPessoaPorId buscarPessoaPorId = new BuscarPessoaPorId(repository);
		Pessoa pessoa = buscarPessoaPorId.executar(id);
		Endereco novoEndereco = new Endereco(dadosEndereco.logradouro(), dadosEndereco.cep(), dadosEndereco.numero(),
				dadosEndereco.cidade(), dadosEndereco.principal());
		pessoa.adicionarEndereco(novoEndereco);
		return new DadosDePessoa(repository.editar(pessoa));
	}

}
