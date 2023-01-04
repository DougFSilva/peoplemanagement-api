package com.dougdeveloper.peoplemanagement.dominio.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepository {

	Pessoa criar(Pessoa pessoa);
	
	Pessoa deletar(Pessoa pessoa);
	
	Pessoa editar(Pessoa pessoaEditada);
	
	Pessoa adicionarEndereco(Long id, Endereco endereco);
	
	Pessoa removerEndereco(Long id, Long enderecoId);
	
	Optional<Pessoa> buscarPorId(Long id);
	
	List<Pessoa> buscarPorNome(String nome);
	
	Page<Pessoa> buscarPorCep(Cep cep, Pageable paginacao);
	
	Page<Pessoa> buscarPorCidade(String cidade, Pageable paginacao);
	
	Page<Pessoa> listar(Pageable paginacao);
	
}
