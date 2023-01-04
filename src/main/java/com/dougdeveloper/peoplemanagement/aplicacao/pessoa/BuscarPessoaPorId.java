package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import java.util.Optional;

import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

public class BuscarPessoaPorId {

	private final PessoaRepository repository;
	
	public BuscarPessoaPorId(PessoaRepository repository) {
		this.repository = repository;
	}
	
	public Pessoa executar(Long id) {
		Optional<Pessoa> pessoa = repository.buscarPorId(id);
		return pessoa.orElseThrow(() -> new ObjetoNaoEncontradoException("Pessoa com id " + id + " não encontrada!"));
	}
}
