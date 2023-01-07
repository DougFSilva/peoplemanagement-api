package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import java.util.Optional;

import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;
/**
 * A classe <b>BuscaPessoaPorId</b> define um usecase para realizar uma busca por uma <b>Pessoa</b>, passando como parâmetro ao método
 * buscar o atributo id.
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class BuscaPessoaPorId {

	private final PessoaRepository repository;
	
	public BuscaPessoaPorId(PessoaRepository repository) {
		this.repository = repository;
	}
	
	public Pessoa buscar(Long id) {
		Optional<Pessoa> pessoa = repository.buscarPorId(id);
		return pessoa.orElseThrow(() -> new ObjetoNaoEncontradoException("Pessoa com id " + id + " não encontrada!"));
	}
}
