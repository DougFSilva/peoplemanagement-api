package com.dougdeveloper.peoplemanagement.infraestrutura.config.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.AdicionarEnderecoAPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarPessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoasPorCep;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoasPorCidade;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoasPorNome;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDeTodasPessoas;
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
	public BuscarDadosDePessoaPorId buscarDadosDePessoaPorId() {
		return new BuscarDadosDePessoaPorId(pessoaAdapterRepository);
	}
	
	@Bean
	public BuscarDadosDePessoasPorCep buscarPessoasPorCep() {
		return new BuscarDadosDePessoasPorCep(pessoaAdapterRepository);
	}
	
	@Bean
	public BuscarDadosDePessoasPorCidade buscarPessoasPorCidade() {
		return new BuscarDadosDePessoasPorCidade(pessoaAdapterRepository);
	}
	
	@Bean
	public BuscarDadosDePessoasPorNome buscarPessoasPorNome() {
		return new BuscarDadosDePessoasPorNome(pessoaAdapterRepository);
	}
	
	@Bean
	public BuscarDadosDeTodasPessoas buscarTodasPessoas() {
		return new BuscarDadosDeTodasPessoas(pessoaAdapterRepository);
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
