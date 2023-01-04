package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import java.util.List;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class BuscarPessoasPorNome {

	private final PessoaRepository repository;
	
	public BuscarPessoasPorNome(PessoaRepository repository) {
		this.repository = repository;
	}
	
	public List<Pessoa> executar(String nome){
		return repository.buscarPorNome(nome);
	}
}
