package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;


import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.logger.AppLogger;
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
	
	private final AppLogger logger;
	
	private final PessoaRepository repository;

	public CriaPessoa(PessoaRepository repository, AppLogger logger) {
		this.repository = repository;
		this.logger = logger;
	}

	@Transactional
	public DadosDePessoa criar(DadosCriarPessoa dados) {
		Pessoa pessoa = new Pessoa(dados.nome(), dados.dataNascimento());
		Pessoa pessoaCriada = repository.criar(pessoa);
		logger.info("Pessoa de nome " + pessoaCriada.getNome() + " e id " + pessoaCriada.getId() + " criada!", CriaPessoa.class);
		return new DadosDePessoa(pessoaCriada);
	}

}
