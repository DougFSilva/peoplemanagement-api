package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class BuscarPessoasPorCidade {

	private final PessoaRepository repository;

	public BuscarPessoasPorCidade(PessoaRepository repository) {
		this.repository = repository;
	}
	
	public Page<Pessoa> executar(String cidade, Pageable paginacao){
		return repository.buscarPorCidade(cidade, paginacao);
	}

}
