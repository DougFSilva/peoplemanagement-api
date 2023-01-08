package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarOuEditarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.logger.AppLogger;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;

/**
 * A classe <b>EditaPessoa</b> define um usecase para editar uma <b>Pessoa</b>,
 * passando como parâmetros ao método editar o id da pessoa e um objeto do tipo
 * <b>DadosEditarPessoa</b>
 * 
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class EditaPessoa {

	private final AppLogger logger;

	private final PessoaRepository repository;

	public EditaPessoa(PessoaRepository repository, AppLogger logger) {
		this.repository = repository;
		this.logger = logger;
	}

	@Transactional
	public DadosDePessoa editar(Long id, DadosCriarOuEditarPessoa dados) {
		BuscaPessoaPorId buscarPessoaPorId = new BuscaPessoaPorId(repository);
		Pessoa pessoa = buscarPessoaPorId.buscar(id);
		pessoa.setNome(dados.nome());
		pessoa.setDataNascimento(dados.dataNascimento());
		Pessoa pessoaEditada = repository.editar(pessoa);
		logger.info("Pessoa de id " + id + " editada para nome " + pessoaEditada.getNome()
				+ " e data de nascimento " + pessoaEditada.getDataNascimento(), EditaPessoa.class);
		return new DadosDePessoa(pessoaEditada);
	}
}
