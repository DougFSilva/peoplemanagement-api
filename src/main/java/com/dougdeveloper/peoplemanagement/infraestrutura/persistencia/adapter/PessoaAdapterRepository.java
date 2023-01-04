package com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.dao.PessoaRepositoryDao;

@Repository
public class PessoaAdapterRepository implements PessoaRepository {

	@Autowired
	private PessoaRepositoryDao repository;
	
	@Override
	public Pessoa criar(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa deletar(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa editar(Pessoa pessoaEditada) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Pessoa> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pessoa> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Pessoa> buscarPorCep(Cep cep, Pageable paginacao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Pessoa> buscarPorCidade(String cidade, Pageable paginacao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Pessoa> listar(Pageable paginacao) {
		// TODO Auto-generated method stub
		return null;
	}

}
