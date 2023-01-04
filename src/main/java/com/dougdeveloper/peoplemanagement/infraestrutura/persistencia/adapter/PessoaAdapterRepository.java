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
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.converter.PessoaEntityConverter;
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.dao.PessoaRepositoryDao;
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.entity.PessoaEntity;

@Repository
public class PessoaAdapterRepository implements PessoaRepository {

	@Autowired
	private PessoaRepositoryDao repository;

	@Autowired
	private PessoaEntityConverter pessoaEntityConverter;

	@Override
	public Pessoa criar(Pessoa pessoa) {
		PessoaEntity entity = pessoaEntityConverter.paraPessoaEntity(pessoa);
		return pessoaEntityConverter.paraPessoa(repository.save(entity));
	}

	@Override
	public void deletar(Pessoa pessoa) {
		PessoaEntity entity = pessoaEntityConverter.paraPessoaEntity(pessoa);
		repository.delete(entity);
	}

	@Override
	public Pessoa editar(Pessoa pessoaEditada) {
		PessoaEntity entity = pessoaEntityConverter.paraPessoaEntity(pessoaEditada);
		return pessoaEntityConverter.paraPessoa(repository.save(entity));
	}

	@Override
	public Optional<Pessoa> buscarPorId(Long id) {
		Optional<PessoaEntity> entity = repository.findById(id);
		if (entity.isPresent()) {
			return Optional.of(pessoaEntityConverter.paraPessoa(entity.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<Pessoa> buscarPorNome(String nome) {
		List<PessoaEntity> entities = repository.findAllByNomeContainingIgnoreCase(nome);
		return entities.stream().map(entity -> pessoaEntityConverter.paraPessoa(entity)).toList();
	}

	@Override
	public Page<Pessoa> buscarPorCep(Cep cep, Pageable paginacao) {
		Page<PessoaEntity> entities = repository.findAllByEnderecosCepDigitos(cep.getDigitos(), paginacao);
		return entities.map(entity -> pessoaEntityConverter.paraPessoa(entity));
	}

	@Override
	public Page<Pessoa> buscarPorCidade(String cidade, Pageable paginacao) {
		Page<PessoaEntity> entities = repository.findAllByEnderecosCidade(cidade, paginacao);
		return entities.map(entity -> pessoaEntityConverter.paraPessoa(entity));
	}

	@Override
	public Page<Pessoa> listar(Pageable paginacao) {
		Page<PessoaEntity> entities = repository.findAll(paginacao);
		return entities.map(entity -> pessoaEntityConverter.paraPessoa(entity));
	}

}
