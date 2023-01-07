package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;
/**
 * A classe <b>DeletaPessoa</b> define um usecase para deletar uma <b>Pessoa</b>, passando como parâmetro ao método deletar o atributo id.
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class DeletaPessoa {

	private final PessoaRepository repository;
	
	public DeletaPessoa(PessoaRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public void deletar(Long id) {
		BuscaPessoaPorId buscarPessoaPorId = new BuscaPessoaPorId(repository);
		Pessoa pessoa = buscarPessoaPorId.buscar(id);
		repository.deletar(pessoa);
	}
}
