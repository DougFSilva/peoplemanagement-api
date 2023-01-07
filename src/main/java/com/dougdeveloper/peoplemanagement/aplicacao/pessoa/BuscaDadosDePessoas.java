package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;
/**
 * A classe <b>BuscaDadosDePessoas</b> define um usecase para realizar diversas buscas referente a <b>Pessoa</b>, retornando um DTO DadosDePessoa
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class BuscaDadosDePessoas {

	private final PessoaRepository repository;
	
	public BuscaDadosDePessoas(PessoaRepository repository) {
		this.repository = repository;
	}

	public DadosDePessoa buscarPorId(Long id) {
		Optional<Pessoa> pessoa = repository.buscarPorId(id);
		if (pessoa.isEmpty()) {
			throw new ObjetoNaoEncontradoException("Pessoa com id " + id + " não encontrada!");
		}
		return new DadosDePessoa(pessoa.get());
	}

	public Page<DadosDePessoa> buscarPorCep(String digitosCep, Pageable paginacao) {
		return repository.buscarPorCep(new Cep(digitosCep), paginacao).map(pessoa -> new DadosDePessoa(pessoa));
	}

	public Page<DadosDePessoa> buscarPorCidade(String cidade, Pageable paginacao) {
		return repository.buscarPorCidade(cidade, paginacao).map(pessoa -> new DadosDePessoa(pessoa));
	}

	public List<DadosDePessoa> buscarPorNome(String nome) {
		return repository.buscarPorNome(nome).stream().map(pessoa -> new DadosDePessoa(pessoa)).toList();
	}

	public Page<DadosDePessoa> buscarTodas(Pageable paginacao) {
		return repository.listar(paginacao).map(pessoa -> new DadosDePessoa(pessoa));
	}
}
