package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import java.util.Optional;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class BuscarDadosDePessoaPorId {

	private final PessoaRepository repository;
	
	public BuscarDadosDePessoaPorId(PessoaRepository repository) {
		this.repository = repository;
	}
	
	public DadosDePessoa executar(Long id) {
		Optional<Pessoa> pessoa = repository.buscarPorId(id);
		if(pessoa.isEmpty()) {
			throw new ObjetoNaoEncontradoException("Pessoa com id " + id + " não encontrada!");
		}
		return new DadosDePessoa(pessoa.get());
	}
}
