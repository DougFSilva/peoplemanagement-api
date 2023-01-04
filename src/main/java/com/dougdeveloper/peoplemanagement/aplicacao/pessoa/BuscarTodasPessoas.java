package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class BuscarTodasPessoas {

	private final PessoaRepository repository;
	
	public BuscarTodasPessoas(PessoaRepository repository) {
		this.repository = repository;
	}
	
	public Page<Pessoa> executar(Pageable paginacao){
		return repository.listar(paginacao);
	}
}
