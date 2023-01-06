package com.dougdeveloper.peoplemanagement.infraestrutura.config.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoas;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.CriarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.DeletarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.EditarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.AdicionarEnderecoAPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.EditarEnderecoDaPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.RemoverEnderecoDaPessoa;
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.adapter.PessoaAdapterRepository;

@Configuration
public class PessoaBeanConfiguration {

	@Autowired
	private PessoaAdapterRepository pessoaAdapterRepository;

	@Bean
	public AdicionarEnderecoAPessoa adicionarEnderecoAPessoa() {
		return new AdicionarEnderecoAPessoa(pessoaAdapterRepository);
	}

	@Bean
	public BuscarDadosDePessoas buscarDadosDePessoa() {
		return new BuscarDadosDePessoas(pessoaAdapterRepository);
	}

	@Bean
	public CriarPessoa criarPessoa() {
		return new CriarPessoa(pessoaAdapterRepository);
	}

	@Bean
	public DeletarPessoa deletarPessoa() {
		return new DeletarPessoa(pessoaAdapterRepository);
	}

	@Bean
	public EditarEnderecoDaPessoa editarEnderecoDaPessoa() {
		return new EditarEnderecoDaPessoa(pessoaAdapterRepository);
	}

	@Bean
	public EditarPessoa editarPessoa() {
		return new EditarPessoa(pessoaAdapterRepository);
	}

	@Bean
	public RemoverEnderecoDaPessoa removerEnderecoDaPessoa() {
		return new RemoverEnderecoDaPessoa(pessoaAdapterRepository);
	}

}
