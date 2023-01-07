package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;
/**
 * A classe <b>CriaPessoa</b> define um usecase para realizar o cadastro de uma <b>Pessoa</b>, passando como parâmetros
 * ao método criar um objeto do tipo <b>DadosCriarPessoa</b>
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class CriaPessoa {

	private final PessoaRepository repository;

	public CriaPessoa(PessoaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public DadosDePessoa criar(DadosCriarPessoa dados) {
		Pessoa pessoa = new Pessoa(dados.nome(), dados.dataNascimento());
		return new DadosDePessoa(repository.criar(pessoa));
	}

}
