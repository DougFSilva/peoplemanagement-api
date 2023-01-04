package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class AdicionarEnderecoAPessoa {

	private final PessoaRepository repository;

	public AdicionarEnderecoAPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	public Pessoa executar(Long id, DadosCriarEndereco dadosEndereco, BuscarPessoaPorId buscarPessoaPorId) {
		Pessoa pessoa = buscarPessoaPorId.executar(id);
		Endereco novoEndereco = new Endereco(dadosEndereco.logradouro(), dadosEndereco.cep(), dadosEndereco.numero(),
				dadosEndereco.cidade(), dadosEndereco.principal());
		if(novoEndereco.isPrincipal()) {
			pessoa.getEnderecos().forEach(endereco -> endereco.setPrincipal(false));
		}
		pessoa.getEnderecos().add(novoEndereco);
		return repository.editar(pessoa);
	}

}
