package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class BuscarDadosDePessoasPorCep {

	private final PessoaRepository repository;
	
	public BuscarDadosDePessoasPorCep(PessoaRepository repository) {
		this.repository = repository;
	}
	
	public Page<DadosDePessoa> executar(String digitosCep, Pageable paginacao){
		return repository.buscarPorCep(new Cep(digitosCep), paginacao).map(pessoa -> new DadosDePessoa(pessoa));
	}
}
