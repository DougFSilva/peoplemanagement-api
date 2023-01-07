package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscaPessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarEndereco;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.logger.AppLogger;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;

/**
 * A classe <b>AdicionaEnderecoAPessoa</b> define um usecase para adicionar um
 * endereço a uma <b>Pessoa</b>, passando como parâmetros ao método adicionar o
 * id da pessoa e um objeto do tipo <b>DadosCriarEndereco</b>
 * 
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public class AdicionaEnderecoAPessoa {

	private final AppLogger logger;

	private final PessoaRepository repository;

	public AdicionaEnderecoAPessoa(PessoaRepository repository, AppLogger logger) {
		this.repository = repository;
		this.logger = logger;
	}

	@Transactional
	public DadosDePessoa adicionar(Long id, DadosCriarEndereco dadosEndereco) {
		BuscaPessoaPorId buscarPessoaPorId = new BuscaPessoaPorId(repository);
		Pessoa pessoa = buscarPessoaPorId.buscar(id);
		Endereco novoEndereco = new Endereco(dadosEndereco.logradouro(), dadosEndereco.cep(), dadosEndereco.numero(),
				dadosEndereco.cidade(), dadosEndereco.principal());
		pessoa.adicionarEndereco(novoEndereco);
		Pessoa pessoaEditada = repository.editar(pessoa);
		logger.info("Adicionado endereço " + novoEndereco + " à pessoa de nome " + pessoaEditada.getNome() + " e id "
				+ pessoaEditada.getId(), AdicionaEnderecoAPessoa.class);
		return new DadosDePessoa(pessoaEditada);
	}

}
