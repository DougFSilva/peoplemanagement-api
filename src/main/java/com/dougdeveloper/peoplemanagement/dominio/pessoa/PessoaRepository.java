package com.dougdeveloper.peoplemanagement.dominio.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * A interface <b>PessoaRepository</b> define os métodos a serem implementados pela camada de infrastrutura referente a persistência de 
 * um objeto do tipo <b>Pessoa</b> no banco de dados.
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public interface PessoaRepository {

	Pessoa criar(Pessoa pessoa);
	
	void deletar(Pessoa pessoa);
	
	Pessoa editar(Pessoa pessoaEditada);
	
	Optional<Pessoa> buscarPorId(Long id);
	
	List<Pessoa> buscarPorNome(String nome);
	
	Page<Pessoa> buscarPorCep(Cep cep, Pageable paginacao);
	
	Page<Pessoa> buscarPorCidade(String cidade, Pageable paginacao);
	
	Page<Pessoa> listar(Pageable paginacao);
	
}
