package com.dougdeveloper.peoplemanagement.infraestrutura.config.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.AdicionarEnderecoAPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarPessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarPessoasPorCep;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarPessoasPorCidade;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarPessoasPorNome;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarTodasPessoas;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.CriarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.DeletarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.EditarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.RemoverEnderecoDaPessoa;
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
	public BuscarPessoaPorId buscarPessoaPorId() {
		return new BuscarPessoaPorId(pessoaAdapterRepository);
	}
	
	@Bean
	public BuscarPessoasPorCep buscarPessoasPorCep() {
		return new BuscarPessoasPorCep(pessoaAdapterRepository);
	}
	
	@Bean
	public BuscarPessoasPorCidade buscarPessoasPorCidade() {
		return new BuscarPessoasPorCidade(pessoaAdapterRepository);
	}
	
	@Bean
	public BuscarPessoasPorNome buscarPessoasPorNome() {
		return new BuscarPessoasPorNome(pessoaAdapterRepository);
	}
	
	@Bean
	public BuscarTodasPessoas buscarTodasPessoas() {
		return new BuscarTodasPessoas(pessoaAdapterRepository);
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
	public EditarPessoa editarPessoa() {
		return new EditarPessoa(pessoaAdapterRepository);
	}
	
	@Bean
	public RemoverEnderecoDaPessoa removerEnderecoDaPessoa() {
		return new RemoverEnderecoDaPessoa(pessoaAdapterRepository);
	}
	
}
