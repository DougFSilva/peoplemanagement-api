package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class BuscarPessoasPorCep {

	private final PessoaRepository repository;
	
	public BuscarPessoasPorCep(PessoaRepository repository) {
		this.repository = repository;
	}
	
	public Page<Pessoa> executar(String digitosCep, Pageable paginacao){
		return repository.buscarPorCep(new Cep(digitosCep), paginacao);
	}
}
