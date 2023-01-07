package com.dougdeveloper.peoplemanagement.infraestrutura.config.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscaDadosDePessoas;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.CriaPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.DeletaPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.EditaPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.AdicionaEnderecoAPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.EditaEnderecoDaPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.RemoveEnderecoDaPessoa;
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.adapter.PessoaRepositoryAdapter;

/**
 * A classe <b>PessoaBeanConfiguration</b> contém os Beans de configuração para o Spring criar os usecases refentes a <b>Pessoa</b>
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
@Configuration
public class PessoaBeanConfiguration {

	@Autowired
	private PessoaRepositoryAdapter pessoaAdapterRepository;

	@Bean
	public AdicionaEnderecoAPessoa adicionaEnderecoAPessoa() {
		return new AdicionaEnderecoAPessoa(pessoaAdapterRepository);
	}

	@Bean
	public BuscaDadosDePessoas buscaDadosDePessoa() {
		return new BuscaDadosDePessoas(pessoaAdapterRepository);
	}

	@Bean
	public CriaPessoa criaPessoa() {
		return new CriaPessoa(pessoaAdapterRepository);
	}

	@Bean
	public DeletaPessoa deletaPessoa() {
		return new DeletaPessoa(pessoaAdapterRepository);
	}

	@Bean
	public EditaEnderecoDaPessoa editaEnderecoDaPessoa() {
		return new EditaEnderecoDaPessoa(pessoaAdapterRepository);
	}

	@Bean
	public EditaPessoa editaPessoa() {
		return new EditaPessoa(pessoaAdapterRepository);
	}

	@Bean
	public RemoveEnderecoDaPessoa removeEnderecoDaPessoa() {
		return new RemoveEnderecoDaPessoa(pessoaAdapterRepository);
	}

}
